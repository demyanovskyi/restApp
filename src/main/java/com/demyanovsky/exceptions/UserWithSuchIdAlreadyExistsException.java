package com.demyanovsky.exceptions;

import com.demyanovsky.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exist")
public class UserWithSuchIdAlreadyExistsException extends RuntimeException{



    public UserWithSuchIdAlreadyExistsException(User user){
        super("User with id:  "+user.getId()+" already exist");
    }
}