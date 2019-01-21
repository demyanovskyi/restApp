package com.demyanovsky.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID orderID) {
        super("Order with ID : " + orderID + " not found");
    }
}
