package com.demyanovsky.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderID) {
        super("Order with ID : " + orderID + " not found");
    }
}
