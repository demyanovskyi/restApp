package com.demyanovsky.repository;

import com.demyanovsky.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface CustomOrderRepository  {
     void saveOrder(Order order);
   Optional<Order> getOrder (UUID id);
}
