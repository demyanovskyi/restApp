package com.demyanovsky.exceptions;

import org.omg.SendingContext.RunTime;

import java.util.UUID;

public class IncorrectProductException extends RuntimeException {
    public IncorrectProductException(UUID id) {
        super("Incorect User, or  User with id:  " + id + " already exist");
    }
}
