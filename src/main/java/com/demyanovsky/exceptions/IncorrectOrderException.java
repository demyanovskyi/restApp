package com.demyanovsky.exceptions;

import com.demyanovsky.domain.Order;

public class IncorrectOrderException extends RuntimeException {
    public IncorrectOrderException(Order order) {
        super("Incorrect order : " + order.toString() + " Or order already exists ");
    }
}
