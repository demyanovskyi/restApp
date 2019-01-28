package com.demyanovsky.services;


import com.demyanovsky.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    static final UUID FIRST_PRODUCT_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_PRODUCT_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product( "MacBook Pro", 2312.44);
    static Product product2 = new Product( "iPhone X", 844.43);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductService productService;

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE product ( id  UUID  PRIMARY KEY, product_name   VARCHAR(100)  NOT NULL, price numeric );";
        jdbcTemplate.execute(sql);
        productService.save(product1);
        productService.save(product2);
    }

    @After
    public void destroy() {
        String sql = "DROP TABLE product";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void getAll() {
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
    assertEquals(tmp.size(), 2);
    }

    @Test
    public void getById() {
        assertEquals(productService.getById(FIRST_PRODUCT_ID).get(), product2);
        assertEquals(productService.getById(SECOND_PRODUCT_ID).get(), product1);
    }

    @Test
    public void deliteProductByID() {
        productService.deleteById(SECOND_PRODUCT_ID);
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
        assertEquals(tmp.size(), 1);
    }

    @Test
    public void modifyProduct() {
        Product product3 = new Product( "iPhone X ref", 921.44);
        productService.modify(product3);
        Optional<Product> tmp  = Optional.of(new Product());
      //  assertEquals(productService.getById(FIRST_PRODUCT_ID).getName(), "iPhone X ref");
        //assertTrue(productService.getById(FIRST_PRODUCT_ID).getPrice() == 921.44);
        tmp = productService.getById(FIRST_PRODUCT_ID);
        assertEquals(tmp.get().getName(), "iPhone X ref");
    }
}
