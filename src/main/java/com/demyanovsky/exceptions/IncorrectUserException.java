package com.demyanovsky.exceptions;


import java.util.UUID;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(UUID id) {
        super("User with id:  " + id + " already exist");
    }
}