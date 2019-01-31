package com.demyanovsky.services;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;
import com.demyanovsky.exceptions.IncorrectOrderException;

import java.util.UUID;

public interface OrderService {


    Order save(OrderDTO orderDTO, UUID userId) throws IncorrectOrderException;
    Order getOrder(UUID id);

}
