package com.demyanovsky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exist, or incorrect user")
public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(Long id) {
        super("User with id:  " + id + " already exist");
    }
}