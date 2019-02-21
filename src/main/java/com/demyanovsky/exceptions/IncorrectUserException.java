package com.demyanovsky.exceptions;

import java.util.UUID;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(UUID id) {
        super("Incorrect User, or  User with id:  " + id + " already exist");
    }
}
