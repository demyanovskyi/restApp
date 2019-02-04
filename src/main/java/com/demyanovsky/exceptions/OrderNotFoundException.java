package com.demyanovsky.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID userId) {
        super("Order with userId : " + userId + " not found");
    }
}
