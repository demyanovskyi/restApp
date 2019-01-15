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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    static final UUID FIRST_USER_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_USER_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product(SECOND_USER_ID, "Stiv", 232.44);
    static Product product2 = new Product(FIRST_USER_ID, "Bill", 5442.43);

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
        assertEquals(productRepository.getProductById(FIRST_USER_ID), product2);
        assertEquals(productRepository.getProductById(SECOND_USER_ID), product1);
    }

    @Test
    public void deliteUserByID() {
        productRepository.deletebyID(SECOND_USER_ID);
        assertEquals(productRepository.getAll().size(), 1);
    }

    @Test
    public void modifyUser() {
        Product user3 = new Product(FIRST_USER_ID, "Edvard", 321.44);
        productRepository.modify(user3);
        assertEquals(productRepository.getProductById(FIRST_USER_ID).getName(), "Edvard");
    }
}
