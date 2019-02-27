package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.OrderNotFoundException;
import com.demyanovsky.exceptions.ProductNotValidException;
import com.demyanovsky.repository.OrderRepository;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO, UUID userId) {
        Order order = new Order();
        List<UUID> productsId = orderDTO.getProductList();
        order.addProducts(validateProducts(productsId));
        order.setUserId(userId);
        return orderRepository.save(order);
    }

    private List<Product> validateProducts(List<UUID> productsId) {
        Iterable<Product> existProducts = productRepository.findAllById(productsId);
        List<UUID> existProductId = new ArrayList<>();
        for (Product tmp : existProducts) {
            existProductId.add(tmp.getId());
        }
        for (UUID id : productsId) {
            if (!existProductId.contains(id)) {
                throw new ProductNotValidException(id);
            }
        }
        List<Product> products = new ArrayList<>();
        existProducts.iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Order getOrder(UUID id) {
        try {
            return orderRepository.findByUserId(id);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException(id);
        }
    }
}
