package com.demyanovsky.exceptions;


public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(Long id) {
        super("User with id:  " + id + " already exist");
    }
}