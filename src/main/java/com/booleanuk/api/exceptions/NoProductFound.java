package com.booleanuk.api.exceptions;

public class NoProductFound extends RuntimeException {
    public NoProductFound(String message) {
        super(message);
    }
}
