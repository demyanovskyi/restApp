package com.demyanovsky.exceptions;

import java.util.UUID;

public class ProductNotValidException extends RuntimeException {
    public ProductNotValidException(UUID id) {
        super("Product not valid, or Product with id:  " + id + " already exist");
    }
}
