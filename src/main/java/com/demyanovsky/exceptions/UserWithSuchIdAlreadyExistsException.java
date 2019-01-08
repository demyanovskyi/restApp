package com.demyanovsky.exceptions;


import com.demyanovsky.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exist, or incorrect user")
public class UserWithSuchIdAlreadyExistsException extends RuntimeException {


    public UserWithSuchIdAlreadyExistsException(Long id) {
        super("User with id:  " + id + " already exist");
    }
}