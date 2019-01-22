package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.exceptions.IncorrectOrderException;
import com.demyanovsky.exceptions.OrderNotFoundException;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Order order) {
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new IncorrectOrderException(order);
        }
    }

    @Override
    public Order getOrder(UUID id) {
        try {
            return orderRepository.getOrder(id);
        } catch (Exception e) {
            throw new OrderNotFoundException(id);
        }
    }

}
