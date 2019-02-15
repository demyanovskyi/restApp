package com.demyanovsky.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException() {
        super("Error: You do not have permission to access the requested module.");
    }
}
