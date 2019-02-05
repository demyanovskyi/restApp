
package com.demyanovsky.controllers;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.exceptions.IncorrectOrderException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.services.OrderService;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.OrderCRUDConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = OrderCRUDConstants.GET_ORDER, method = RequestMethod.GET)
    private ResponseEntity<Order> getOrder(@PathVariable("id") UUID id) {
        Order order = orderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = OrderCRUDConstants.CREATE_ORDER, method = RequestMethod.POST)
    private ResponseEntity<Order> createOrder(@PathVariable("id") UUID userId, @RequestBody OrderDTO orderDTO) throws IncorrectOrderException, UserNotFoundException {
        if (userService.getById(userId) == null) {
            throw new UserNotFoundException(userId);
        } else {
            Order order = orderService.save(orderDTO, userId);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
    }
}

