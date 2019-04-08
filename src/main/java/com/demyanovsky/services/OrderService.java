package com.demyanovsky.services;

import com.demyanovsky.domain.Order;
import com.demyanovsky.domain.OrderDTO;

import java.util.UUID;

public interface OrderService {
    /**
     * Add the Order .
     *
     * @param order
     */
    Order save(OrderDTO order, UUID userId);

    /**
     * Get the Order by User id.
     *
     * @param id
     * @return Order
     */
    Order getOrder(UUID id);
}
