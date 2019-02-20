package com.demyanovsky.exceptions;

import java.util.UUID;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(UUID id) {
        super("Incorrect User, or  User with id:  " + id + " already exist");
    }
    public IncorrectUserException(String  email) {
        super("Incorrect email, or  User with id:  " +email + " dont exist");
    }
}
