package com.demyanovsky.services;

import com.demyanovsky.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {


    void save(Order order);


    Optional<Order> getOrder(UUID id);

}
