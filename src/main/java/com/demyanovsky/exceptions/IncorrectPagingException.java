package com.demyanovsky.exceptions;

public class IncorrectPagingException extends RuntimeException {
    public IncorrectPagingException() {
        super("Page number or limit size cant be negative");
    }
}
