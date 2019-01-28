package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.ProductController;
import com.demyanovsky.domain.Product;
import com.demyanovsky.services.ProductService;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;


    static final UUID FIRST_PRODUCT_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_PRODUCT_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product( "MacBook Pro", 2332.44);
    static Product product2 = new Product("iPhone X", 542.43);

    @Before
    public void init() throws Exception {
        productService.save(product1);
        productService.save(product2);
    }

    @After
    public void destroy() throws Exception {
        productService.deleteById(FIRST_PRODUCT_ID);
        productService.deleteById(SECOND_PRODUCT_ID);
    }

    @Test
    public void productById() throws Exception {
        mockMvc.perform(get("http://localhost:8080//product/{id}", FIRST_PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + FIRST_PRODUCT_ID + "\",\"price\":542.43,\"name\":\"iPhone X\"}"))
                .andReturn();
        mockMvc.perform(get("http://localhost:8080//product/{id}", SECOND_PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andExpect(content().string("{\"id\":\"" + SECOND_PRODUCT_ID + "\",\"price\":2332.44,\"name\":\"MacBook Pro\"}"))
                .andReturn();
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
        mockMvc.perform(delete("http://localhost:8080//product/{id}", SECOND_PRODUCT_ID))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
        mockMvc.perform(delete("http://localhost:8080//product/{id}", FIRST_PRODUCT_ID))
                .andExpect(handler().methodName("deleteProduct"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class));
    }

    @Test
    public void modifyProduct() throws Exception {
        mockMvc.perform(put("http://localhost:8080//product/{id}", SECOND_PRODUCT_ID).content("{ \"id\" : \"" + SECOND_PRODUCT_ID + "\" , \"name\" : \"iPhone XS\" , \"price\" : 931.43}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("updateProduct"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + SECOND_PRODUCT_ID + "\",\"price\":931.43,\"name\":\"iPhone XS\"}"))
                .andReturn();
    }

}
