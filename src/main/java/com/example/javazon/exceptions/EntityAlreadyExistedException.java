package com.example.javazon.exceptions;

public class EntityAlreadyExistedException extends RuntimeException {
    public EntityAlreadyExistedException(String message) {
        super(message);
    }
}
