package com.demyanovsky.services;


import com.demyanovsky.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    private Product product1 = new Product("MacBook Pro", 2312.44);
    private Product product2 = new Product("iPhone X", 844.43);
    private Product product3 = new Product("AppleWatch 4", 400.00);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductService productService;

    @Test
    public void getAll() {
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
        assertEquals(tmp.size(), 3);
        productService.deleteById(product1.getId());
        productService.deleteById(product2.getId());
        productService.deleteById(product3.getId());
    }

    @Test
    public void getById() {
        productService.save(product1);
        productService.save(product2);
        assertEquals(productService.getById(product1.getId()), product1);
        assertEquals(productService.getById(product2.getId()), product2);
        productService.deleteById(product1.getId());
        productService.deleteById(product2.getId());
    }

    @Test
    public void deliteProductByID() {
        productService.save(product2);
        productService.deleteById(product2.getId());
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
        assertEquals(tmp.size(), 0);
    }

    @Test
    public void modifyProduct() {
        productService.save(product2);
        Product product3 = new Product("iPhone X ref", 921.44);
        product3.setId(product2.getId());
        productService.modify(product3);
        Product tmp = new Product();
        tmp = productService.getById(product2.getId());
        assertEquals(tmp.getProductName(), "iPhone X ref");
        productService.deleteById(product2.getId());
    }
}
