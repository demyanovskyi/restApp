package com.demyanovsky.exceptions;


import java.util.UUID;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(UUID id) {
        super("Incorect User, or  User with id:  " + id + " already exist");
    }
}