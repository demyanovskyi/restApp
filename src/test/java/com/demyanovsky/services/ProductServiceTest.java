package com.demyanovsky.services;


import com.demyanovsky.domain.Product;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {


    static Product product1 = new Product( "MacBook Pro", 2312.44);
    static Product product2 = new Product( "iPhone X", 844.43);
    static  Product product3 = new Product("AppleWatch 4", 400.00 );

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductService productService;

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS product ( id  UUID  PRIMARY KEY, product_name   VARCHAR(100)  NOT NULL, price numeric );";
        jdbcTemplate.execute(sql);
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
    }

  /*  @After
    public void destroy() {
        String sql = "DROP TABLE product";
        jdbcTemplate.execute(sql);
    }
*/
    @Test
    public void getAll() {
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
    assertEquals(tmp.size(), 3);
    }

    @Test
    public void getById() {
        assertEquals(productService.getById(product1.getId()), product1);
        assertEquals(productService.getById(product2.getId()), product2);
    }

    @Test
    public void deliteProductByID() {
        productService.deleteById(product2.getId());
        List<Product> tmp = new ArrayList();
        tmp = (List<Product>) productService.getAll();
        assertEquals(tmp.size(), 2);
    }

    @Test
    public void modifyProduct() {
        Product product3 = new Product( "iPhone X ref", 921.44);
        product3.setId(product2.getId());
        productService.modify(product3);
      Product tmp  = new Product();
      //  assertEquals(productService.getById(FIRST_PRODUCT_ID).getName(), "iPhone X ref");
        //assertTrue(productService.getById(FIRST_PRODUCT_ID).getPrice() == 921.44);
        tmp = productService.getById(product2.getId());
        assertEquals(tmp.getProductName(), "iPhone X ref");
    }
}
