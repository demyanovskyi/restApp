package com.demyanovsky.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Error: You do not have permission to access the requested module.");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
