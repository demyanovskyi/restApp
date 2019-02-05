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
import org.springframework.transaction.annotation.Transactional;

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

    private Product product1 = new Product("MacBook Pro", 2332.44);
    private Product product2 = new Product("iPhone X", 542.43);

    @Transactional
    @Test
    public void productById() throws Exception {

        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(get("http://localhost:8080//product/{id}", tmp.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"productName\":\"iPhone X\",\"price\":542.43}"))
                .andReturn();
        mockMvc.perform(get("http://localhost:8080//product/{id}", tmp1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + tmp1.getId() + "\",\"productName\":\"MacBook Pro\",\"price\":2332.44}"))
                .andReturn();
    }

    @Transactional
    @Test
    public void getProductList() throws Exception {
        mockMvc.perform(get("http://localhost:8080//product/"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Transactional
    @Test
    public void deleteProductById() throws Exception {
        Product tmp = productService.save(product2);
        Product tmp1 = productService.save(product1);
        mockMvc.perform(delete("http://localhost:8080//product/{id}", tmp.getId()))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
        mockMvc.perform(delete("http://localhost:8080//product/{id}", tmp1.getId()))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
    }

    @Transactional
    @Test
    public void modifyProduct() throws Exception {
        Product tmp = productService.save(product2);
        mockMvc.perform(put("http://localhost:8080//product/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\" , \"productName\" : \"iPhone XS\" , \"price\" : 656.43}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("updateProduct"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"productName\":\"iPhone XS\",\"price\":656.43}"))
                .andReturn();
    }
}
