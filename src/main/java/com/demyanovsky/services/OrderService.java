package com.demyanovsky.services;

import com.demyanovsky.domain.Order;

import java.util.UUID;

public interface OrderService {
    /**
     * Add the order .
     *
     * @param order
     */
    void save(Order order);

    /**
     * Get the order by id.
     *
     * @param id
     * @return order
     */
    Order getOrder(UUID id);

}
