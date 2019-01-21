package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {

        return orderRepository.getAll();
    }

    @Override
    public Order getOrder(UUID id) {
        return orderRepository.getOrder(id);
    }


}
