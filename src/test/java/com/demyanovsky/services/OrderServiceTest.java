package com.demyanovsky.services;

import com.demyanovsky.domain.Order;
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
    static final UUID FIRST_PRODUCT_ID = UUID.fromString("4431b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_PRODUCT_ID = UUID.fromString("4531b533-ba17-4787-98a3-f2df37de2ad2");
    static Product product1 = new Product( "MacBook Pro", 2312.44);
    static Product product2 = new Product( "iPhone X", 844.43);

    static final UUID FIRST_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad2");
    static User user2 = new User(SECOND_USER_ID, "Stiv");
    static User user1 = new User(FIRST_USER_ID, "Bill");

    static final UUID FIRST_ORDER_ID = UUID.fromString("436c2730-1cdd-11e9-ab14-d663bd873d93");
    static List<UUID> productsID = new ArrayList<>();

    static Order order1 = new Order();

    @Before
    public void init() {
        String createOrderTable = "CREATE TABLE \"order\" (\n" +
                "\tid uuid NOT NULL,\n" +
                "\tuser_id uuid NOT NULL,\n" +
                "\tCONSTRAINT order_pkey PRIMARY KEY (id),\n" +
                "\tCONSTRAINT order_users_fk FOREIGN KEY (user_id) REFERENCES users(id)\n" +
                ");";
        String createUsersTable = "CREATE TABLE users (id uuid NOT NULL, " +
                "name varchar(100) NOT NULL, " +
                "CONSTRAINT users_pkey PRIMARY KEY (id));";
        String creatreProductOrderTable = "CREATE TABLE order_product (\n" +
                "\torder_id uuid NULL,\n" +
                "\tproduct_id uuid NULL,\n" +
                "\tCONSTRAINT order_product_order_fk FOREIGN KEY (order_id) REFERENCES \"order\"(id),\n" +
                "\tCONSTRAINT order_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id)\n" +
                ");";
        String createProductTable = "CREATE TABLE product (\n" +
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
        productService.save(product1);
        productService.save(product2);

        productsID.add(FIRST_PRODUCT_ID);
        productsID.add(SECOND_PRODUCT_ID);
        order1.setListProductID(productsID);
        order1.setUserId(FIRST_USER_ID);
        order1.setId(FIRST_ORDER_ID);
        orderService.save(order1);
    }

    @After
    public void destroy() {
        String destroyProduct = "DROP TABLE product";
        String destroyUsers = "DROP TABLE users";
        String destroyOrder = "DROP TABLE \"order\"";
        String destroyProductOrder = "DROP TABLE order_product";
        jdbcTemplate.execute(destroyOrder);
        jdbcTemplate.execute(destroyProduct);
        jdbcTemplate.execute(destroyUsers);
        jdbcTemplate.execute(destroyProductOrder);
    }

    @Test
    public void getById() {
        assertEquals(orderService.getOrder(FIRST_ORDER_ID), order1);
        //assertEquals(orderService.getOrder(FIRST_ORDER_ID).getUserId(), FIRST_USER_ID);
      //  assertEquals(orderService.getOrder(FIRST_ORDER_ID).getListProductID(), productsID);
    }
}
