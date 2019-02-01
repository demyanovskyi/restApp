package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

//@ContextConfiguration(classes = CustomConfig.class)
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO, UUID userId)  {
        Order tempOrder = new Order();

        for (UUID id : orderDTO.getProductList()) {
            tempOrder.addProduct(productRepository.findById(id).get());
        }
        tempOrder.setUserId(userId);
//
        return orderRepository.save(tempOrder);
    }

    @Override
    public Order getOrder(UUID id) {
        return orderRepository.findByUserId(id);
    }


}
