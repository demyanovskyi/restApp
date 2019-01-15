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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
@Autowired
  private  MockMvc mockMvc;
@Autowired
    private ProductService productService;


    static final UUID FIRST_PRODUCT_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_PRODUCT_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product(SECOND_PRODUCT_ID, "Stiv", 232.44);
    static Product product2 = new Product(FIRST_PRODUCT_ID, "Bill", 5442.43);

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
    public void userById() throws Exception {
        mockMvc.perform(get("product/{id}",FIRST_PRODUCT_ID ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andReturn();
        mockMvc.perform(get("product/{id}", SECOND_PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getProduct"))
                .andReturn();
    }
    @Test
    public void listAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080//product/"))
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("getProductList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }


}
