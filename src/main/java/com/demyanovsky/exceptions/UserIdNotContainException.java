package com.demyanovsky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such id")
public class UserIdNotContainException extends RuntimeException{



    public UserIdNotContainException(int userId){
        super("User id: " + userId + " not found");
    }
}
