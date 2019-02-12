package com.demyanovsky.services;

import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.domain.Product;
import com.demyanovsky.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        userService.save(user1);
        userService.save(user2);
        Product pr2 = productService.save(product2);
        Product pr1 = productService.save(product1);
        productsID.add(pr1.getId());
        productsID.add(pr2.getId());
        testProductList.add(product1);
        testProductList.add(product2);
        orderDTO.setProductList(productsID);
        orderService.save(orderDTO, user1.getId());
    }

    @Test
    public void getById() {
        assertEquals(orderService.getOrder(user1.getId()).getUserId(), user1.getId());
        assertEquals(orderService.getOrder(user1.getId()).getProducts().toString(), testProductList.toString());
    }
}
