package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.domain.Product;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public Order save(OrderDTO orderDTO, UUID userId) {
        Order tempOrder = new Order();
        List<UUID> productsId = orderDTO.getProductList();
        if (validateProducts(productsId))
            for (UUID id : productsId) {
                tempOrder.addProduct(productRepository.findById(id).get());
            }
        tempOrder.setUserId(userId);
        return orderRepository.save(tempOrder);
    }

    private boolean validateProducts(List<UUID> productsId) {

        List<Product> allProduct = (List<Product>) productRepository.findAll();
        for (UUID id : productsId) {
            if (allProduct.contains(id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Order getOrder(UUID id) {
        return orderRepository.findByUserId(id);
    }


}
