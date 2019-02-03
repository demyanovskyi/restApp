package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.OrderService;
import com.demyanovsky.services.ProductService;
import com.demyanovsky.services.UserService;
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

    static Product product1 = new Product("MacBook Pro", 2312.44);
    static Product product2 = new Product("iPhone X", 844.43);

    static User user2 = new User("Stiv");
    static User user1 = new User("Bill");

    static List<UUID> productsID = new ArrayList<>();
    static OrderDTO orderDTO = new OrderDTO();

    @Test
    public void orderById() throws Exception {
        productService.save(product1);
        productService.save(product2);
        userService.save(user2);
        userService.save(user1);

        productsID.add(product1.getId());
        productsID.add(product2.getId());
        orderDTO.setProductList(productsID);
        Order tmp = orderService.save(orderDTO, user1.getId());

        mockMvc.perform(get("http://localhost:8080//user/{id}/order/", user1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getOrder"))
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"userId\":\""
                        + user1.getId() + "\",\"products\":[{\"id\":\"" + product1.getId() + "\",\"productName\":\"" +
                        product1.getProductName() + "\",\"price\":" + product1.getPrice() + "},{\"id\":\"" + product2.getId()
                        + "\",\"productName\":\"" + product2.getProductName() + "\",\"price\":" + product2.getPrice() + "}]}"))
                .andReturn();

    }
}
