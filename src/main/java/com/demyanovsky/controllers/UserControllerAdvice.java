package com.demyanovsky.controllers;

import com.demyanovsky.exceptions.ErrorResponse;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleThereIsNoSuchUserException(UserNotFoundException e) {

        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectUserException.class)
    public ResponseEntity<ErrorResponse> handleThereIsNoSuchUserException(IncorrectUserException e) {

        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}


