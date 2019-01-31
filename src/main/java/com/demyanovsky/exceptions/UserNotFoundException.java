package com.demyanovsky.exceptions;

import java.util.UUID;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(UUID userId) {
        super("User with id: " + userId + " not found");
    }
}
