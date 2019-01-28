package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
  private  OrderRepository orderRepository;
 

    @Override
    public void save(Order order) {

            orderRepository.saveOrder(order);

    }

    @Override
    public Optional<Order> getOrder(UUID id) {

        Optional<Order> pr = orderRepository.findById(id);
        return pr;

    }

}
