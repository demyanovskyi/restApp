package com.demyanovsky.services;


import com.demyanovsky.domain.Product;
import com.demyanovsky.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    static final UUID FIRST_PRODUCT_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_PRODUCT_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product(SECOND_PRODUCT_ID, "MacBook Pro", 2312.44);
    static Product product2 = new Product(FIRST_PRODUCT_ID, "iPhone X", 844.43);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductRepository productRepository;

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE product ( id  UUID  PRIMARY KEY, product_name   VARCHAR(100)  NOT NULL, price numeric );";
        jdbcTemplate.execute(sql);
        productRepository.save(product1);
        productRepository.save(product2);
    }

    @After
    public void destroy(){
        String sql = "DROP TABLE product";
        jdbcTemplate.execute(sql);
    }
    @Test
    public void getAll() {
        assertEquals(productRepository.getAll().size(), 2);
    }

    @Test
    public void getById() {
        assertEquals(productRepository.getProductById(FIRST_PRODUCT_ID), product2);
        assertEquals(productRepository.getProductById(SECOND_PRODUCT_ID), product1);
    }

    @Test
    public void deliteProductByID() {
        productRepository.deleteByID(SECOND_PRODUCT_ID);
        assertEquals(productRepository.getAll().size(), 1);
    }

    @Test
    public void modifyProduct() {
        Product product3 = new Product(FIRST_PRODUCT_ID, "iPhone X ref", 921.44);
        productRepository.modify(product3);
        assertEquals(productRepository.getProductById(FIRST_PRODUCT_ID).getName(), "iPhone X ref");
        assertTrue(productRepository.getProductById(FIRST_PRODUCT_ID).getPrice()==921.44);
    }
}
