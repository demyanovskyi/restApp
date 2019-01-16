package com.demyanovsky.exceptions;

import java.util.UUID;

public class IncorrectProductException extends RuntimeException {
    public IncorrectProductException(UUID id) {
        super("Incorrect Product, or  Product with id:  " + id + " already exist");
    }
}
