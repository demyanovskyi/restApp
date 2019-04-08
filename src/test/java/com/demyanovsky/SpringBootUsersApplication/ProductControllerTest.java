package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.ProductController;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.ProductService;
import com.demyanovsky.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.demyanovsky.services.mappingConstants.ProductCRUDConstants.GET_ALL_PRODUCTS;
import static com.demyanovsky.services.mappingConstants.UserCRUDConstants.GET_ALL_USERS;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private Product product1 = new Product("MacBook Pro", 2332.44);
    private Product product2 = new Product("iPhone X", 542.43);

    @Before
    public void setup() {
        UserDTO userDTO1 = new UserDTO("Jon", "ff@ffgfdg.cgd", "123526*Jtgf");
        User testUser1 = userService.save(userDTO1, Role.USER_ROLE);
        UserDTO userDTO2 = new UserDTO("Admin", "admin@gfd.cgm", "admin*I1");
        User testUser2 = userService.save(userDTO2, Role.ADMIN_ROLE);
    }

    @After
    public void destroy() {
        userService.deleteById(userRepository.findByEmail("ff@ffgfdg.cgd").getId());
        userService.deleteById(userRepository.findByEmail("admin@gfd.cgm").getId());
    }

    @Test
    public void productById() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(get("/product/{id}", tmp.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(tmp.getId().toString())))
                .andExpect(jsonPath("$.productName", is(tmp.getProductName())))
                .andExpect(jsonPath("$.price", is(tmp.getPrice())))
                .andReturn();
        productService.deleteById(tmp.getId());

    }

    @Test
    public void getProductList() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get(GET_ALL_PRODUCTS).param("page", "0").param("limit", "10"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductListNegativePageParam() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get(GET_ALL_PRODUCTS).param("page", "-1").param("limit", "10"))
                .andExpect(status().is(400));
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductListNegativeLimitParam() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get(GET_ALL_PRODUCTS).param("page", "0").param("limit", "-10"))
                .andExpect(status().is(400));
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductListWithoutParamIncorrectLimit() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get("/product/").param("page", "0").param("limit", "55"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(50))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductListWithoutParam() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get("/product/"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(25))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void denyDeleteFromUserRole() throws Exception {
        Product tmp1 = productService.save(product1);
        mockMvc.perform(delete("/product/{id}", tmp1.getId())
                .with(httpBasic("ff@ffgfdg.cgd", "123526*Jtgf")))
                .andExpect(status().isForbidden());
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void deleteProductById() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(delete("/product/{id}", tmp.getId())
                .with(httpBasic("admin@gfd.cgm", "admin*I1")))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
    }

    @Test
    public void denyModifyFromUserRole() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(put("/product/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\" , \"productName\" : \"iPhone XS\" , \"price\" : 656.43}")
                .with(httpBasic("ff@ffgfdg.cgd", "123526*Jtgf"))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden());
        productService.deleteById(tmp.getId());
    }

    @Test
    public void modifyProduct() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(put("/product/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\" , \"productName\" : \"iPhone XS\" , \"price\" : 656.43}")
                .with(httpBasic("admin@gfd.cgm", "admin*I1"))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("updateProduct"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(tmp.getId().toString())))
                .andExpect(jsonPath("$.productName", is("iPhone XS")))
                .andExpect(jsonPath("$.price", is(656.43)))
                .andReturn();
        productService.deleteById(tmp.getId());
    }
}
