package com.demyanovsky.exceptions;

import com.demyanovsky.domain.Order;

public class IncorrectOrderException extends Exception {
    public IncorrectOrderException(Order order) {
        super("Incorrect order : " + order.toString() + " Or order already exists ");
    }
}
