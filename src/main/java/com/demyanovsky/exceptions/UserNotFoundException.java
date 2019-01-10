package com.demyanovsky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id: " + userId + " not found");
    }
}
