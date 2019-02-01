package com.demyanovsky.services;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;

import java.util.UUID;

public interface OrderService {


    Order save(OrderDTO order, UUID userId);
    Order getOrder(UUID id);

}
