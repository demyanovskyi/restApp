package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.ProductController;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.User;
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
    UserRepository userRepository;
    private Product product1 = new Product("MacBook Pro", 2332.44);
    private Product product2 = new Product("iPhone X", 542.43);

    @Before
    public void setup() {
        User user2 = new User("Jon", "123526tgf");
        User testUser = userService.save(user2);
    }

    @After
    public void destroy() {
        userService.deleteById(userRepository.findByName("Jon").getId());
    }

    @Test
    public void productById() throws Exception {
        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get("/product/{id}", tmp.getId())
                .with(httpBasic("Jon", "123526tgf")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(tmp.getId().toString())))
                .andExpect(jsonPath("$.productName", is(tmp.getProductName())))
                .andExpect(jsonPath("$.price", is(tmp.getPrice())))
                .andReturn();
        mockMvc.perform(get("/product/{id}", tmp1.getId()).with(httpBasic("Jon", "123526tgf")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(jsonPath("$.id", is(tmp1.getId().toString())))
                .andExpect(jsonPath("$.productName", is(tmp1.getProductName())))
                .andExpect(jsonPath("$.price", is(tmp1.getPrice())))
                .andReturn();
        productService.deleteById(tmp.getId());
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductList() throws Exception {
        mockMvc.perform(get("/product/")
                .with(httpBasic("Jon", "123526tgf")))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void deleteProductById() throws Exception {
        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(delete("/product/{id}", tmp.getId())
                .with(httpBasic("Jon", "123526tgf")))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
        mockMvc.perform(delete("/product/{id}", tmp1.getId())
                .with(httpBasic("Jon", "123526tgf")))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
    }

    @Test
    public void modifyProduct() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(put("/product/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\" , \"productName\" : \"iPhone XS\" , \"price\" : 656.43}")
                .with(httpBasic("Jon", "123526tgf"))
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
