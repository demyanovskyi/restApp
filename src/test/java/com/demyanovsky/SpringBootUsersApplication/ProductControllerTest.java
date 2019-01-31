package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.ProductController;
import com.demyanovsky.domain.Product;
import com.demyanovsky.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    static Product product1 = new Product("MacBook Pro", 2332.44);
    static Product product2 = new Product("iPhone X", 542.43);


    @Test
    public void productById() throws Exception {

        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get("http://localhost:8080//product/{id}", tmp.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"price\":542.43,\"name\":\"iPhone X\"}"))
                .andReturn();
        mockMvc.perform(get("http://localhost:8080//product/{id}", tmp1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + tmp1.getId() + "\",\"price\":2332.44,\"name\":\"MacBook Pro\"}"))
                .andReturn();
        productService.deleteById(tmp.getId());
        productService.deleteById(tmp1.getId());
    }

    @Test
    public void getProductList() throws Exception {
        mockMvc.perform(get("http://localhost:8080//product/"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void deleteProductById() throws Exception {
        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(delete("http://localhost:8080//product/{id}", tmp.getId()))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
        mockMvc.perform(delete("http://localhost:8080//product/{id}", tmp.getId()))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
    }

    @Test
    public void modifyProduct() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(put("http://localhost:8080//product/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\" , \"name\" : \"iPhone XS\" , \"price\" : 931.43}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("updateProduct"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"price\":931.43,\"name\":\"iPhone XS\"}"))
                .andReturn();
        productService.deleteById(tmp.getId());
    }

}
