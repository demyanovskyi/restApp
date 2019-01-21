package com.demyanovsky.services;

import com.demyanovsky.domain.Order;

import java.util.UUID;

public interface OrderService {
    void save(Order order);

//    List<Order> getAll();

    Order getOrder(UUID id);

}
