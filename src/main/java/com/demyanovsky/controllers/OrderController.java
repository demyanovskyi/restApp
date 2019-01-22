package com.demyanovsky.controllers;

import com.demyanovsky.domain.Order;
import com.demyanovsky.exceptions.IncorrectOrderException;
import com.demyanovsky.services.OrderService;
import com.demyanovsky.services.mappingConstants.OrderCRUDConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = OrderCRUDConstants.GET_ORDER, method = RequestMethod.GET)
    private ResponseEntity<Order> getOrder(@PathVariable("id") UUID id) {
        Order order = orderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = OrderCRUDConstants.CREATE_ORDER, method = RequestMethod.POST)
    private ResponseEntity<Order> createOrder(@PathVariable("id") UUID id, @RequestBody Order order) {
        if (order.getId().equals(id)) {
            orderService.save(order);
        }else {
            throw new IncorrectOrderException(order);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


}
