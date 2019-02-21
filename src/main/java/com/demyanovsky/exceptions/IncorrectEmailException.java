package com.demyanovsky.exceptions;

public class IncorrectEmailException extends RuntimeException {
    public IncorrectEmailException(String email) {
        super("Incorrect email, or  User with email:  " + email + " dont exist");
    }
}
