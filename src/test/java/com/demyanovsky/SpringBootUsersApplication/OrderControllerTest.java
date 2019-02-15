package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.domain.*;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.OrderService;
import com.demyanovsky.services.ProductService;
import com.demyanovsky.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
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
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    static Product product1 = new Product("MacBook Pro", 2312.44);
    static Product product2 = new Product("iPhone X", 844.43);
    static List<UUID> productsID = new ArrayList<>();
    static OrderDTO orderDTO = new OrderDTO();

    @Test
    public void orderById() throws Exception {
        Product testProduct = productService.save(product1);
        Product testProduct1 = productService.save(product2);
        UserDTO userDTO1 = new UserDTO("Qwerty", "fwgerhwr");
        User testUser = userService.save(userDTO1, Role.USER_ROLE);
        productsID.add(product1.getId());
        productsID.add(product2.getId());
        orderDTO.setProductList(productsID);
        Order order = orderService.save(orderDTO, testUser.getId());

        mockMvc.perform(get("/user/{id}/order/", testUser.getId())
                .with(httpBasic("Qwerty", "fwgerhwr")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("getOrder"))
                .andExpect(jsonPath("$.id", is(order.getId().toString())))
                .andExpect(jsonPath("$.userId", is(order.getUserId().toString())))
                .andExpect(jsonPath("$.products[0].id", is(product1.getId().toString())))
                .andExpect(jsonPath("$.products[0].productName", is(product1.getProductName())))
                .andExpect(jsonPath("$.products[0].price", is(product1.getPrice())))
                .andExpect(jsonPath("$.products[1].id", is(product2.getId().toString())))
                .andExpect(jsonPath("$.products[1].productName", is(product2.getProductName())))
                .andExpect(jsonPath("$.products[1].price", is(product2.getPrice())));

        orderRepository.delete(order);
        productService.deleteById(testProduct.getId());
        productService.deleteById(testProduct1.getId());
        userService.deleteById(testUser.getId());
    }
}
