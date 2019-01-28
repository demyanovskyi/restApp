package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.OrderService;
import com.demyanovsky.services.ProductService;
import com.demyanovsky.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    JdbcTemplate jdbcTemplate;

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
    public void init() throws Exception {
        productService.save(product1);
        productService.save(product2);
        userService.save(user2);
        userService.save(user1);

        productsID.add(FIRST_PRODUCT_ID);
        productsID.add(SECOND_PRODUCT_ID);
        order1.setListProductID(productsID);
        order1.setUserId(FIRST_USER_ID);
        order1.setId(FIRST_ORDER_ID);
        orderService.save(order1);
    }

    @After
    public void destroy() throws Exception {
        String sql1 = "DELETE FROM public.order_product WHERE order_id= '436c2730-1cdd-11e9-ab14-d663bd873d93' ;";
        String sql2 = "DELETE FROM public.\"order\" WHERE id='436c2730-1cdd-11e9-ab14-d663bd873d93';";
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        productService.deleteById(FIRST_PRODUCT_ID);
        productService.deleteById(SECOND_PRODUCT_ID);
        userService.deleteById(FIRST_USER_ID);
        userService.deleteById(SECOND_USER_ID);

    }

    @Test
    public void orderById() throws Exception {
        mockMvc.perform(get("http://localhost:8080//order/{id}", FIRST_ORDER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getOrder"))
                .andExpect(content().string("{\"id\":\"" + FIRST_ORDER_ID + "\",\"userId\":\""
                        + FIRST_USER_ID + "\",\"listProductID\":[\"" + FIRST_PRODUCT_ID + "\",\"" + SECOND_PRODUCT_ID + "\"]}"))
                .andReturn();

    }
}
