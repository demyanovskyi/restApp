package com.demyanovsky.services;

import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.User;
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
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    static Product product1 = new Product("MacBook Pro", 2312.44);
    static Product product2 = new Product("iPhone X", 844.43);
    static User user2 = new User("Stiv");
    static User user1 = new User("Bill");
    static OrderDTO orderDTO = new OrderDTO();
    static List<UUID> productsID = new ArrayList<>();
    private List<Product> testProductList = new ArrayList<>();

    @Before
    public void init() {
        String createOrderTable = "CREATE TABLE torder (\n" +
                "\tid uuid NOT NULL,\n" +
                "\tuser_id uuid NOT NULL,\n" +
                "\tCONSTRAINT order_pkey PRIMARY KEY (id),\n" +
                "\tCONSTRAINT order_users_fk FOREIGN KEY (user_id) REFERENCES tusers(id)\n" +
                ");";
        String createUsersTable = "CREATE TABLE tusers (id uuid NOT NULL, " +
                "name varchar(100) NOT NULL, " +
                "CONSTRAINT users_pkey PRIMARY KEY (id));";
        String creatreProductOrderTable = "CREATE TABLE torder_product (\n" +
                "\torder_id uuid NULL,\n" +
                "\tproduct_id uuid NULL,\n" +
                "\tCONSTRAINT order_product_order_fk FOREIGN KEY (order_id) REFERENCES torder(id),\n" +
                "\tCONSTRAINT order_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES tproduct(id)\n" +
                ");";
        String createProductTable = "CREATE TABLE tproduct (\n" +
                "\tid uuid NOT NULL,\n" +
                "\tproduct_name varchar(100) NOT NULL,\n" +
                "\tprice numeric NOT NULL,\n" +
                "\tCONSTRAINT product_pkey PRIMARY KEY (id)\n" +
                ");";

        jdbcTemplate.execute(createUsersTable);
        jdbcTemplate.execute(createProductTable);
        jdbcTemplate.execute(createOrderTable);
        jdbcTemplate.execute(creatreProductOrderTable);

        userService.save(user1);
        userService.save(user2);
        Product pr1 = productService.save(product1);
        Product pr2 = productService.save(product2);
        productsID.add(pr1.getId());
        productsID.add(pr2.getId());

        testProductList.add(product1);
        testProductList.add(product2);

        orderDTO.setProductList(productsID);
        orderService.save(orderDTO, user1.getId());
    }

    @After
    public void destroy() {
        String destroyProduct = "DROP TABLE tproduct";
        String destroyUsers = "DROP TABLE tusers";
        String destroyOrder = "DROP TABLE torder";
        String destroyProductOrder = "DROP TABLE torder_product";
        jdbcTemplate.execute(destroyOrder);
        jdbcTemplate.execute(destroyProduct);
        jdbcTemplate.execute(destroyUsers);
        jdbcTemplate.execute(destroyProductOrder);
    }

    @Test
    public void getById() {
        assertEquals(orderService.getOrder(user1.getId()).getUserId(), user1.getId());
        assertEquals(orderService.getOrder(user1.getId()).getProducts().toString(), testProductList.toString());
    }
}
