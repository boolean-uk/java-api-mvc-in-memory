package com.booleanuk.api.exceptions;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(HttpStatus badRequest, String message) {
        super(message);
    }
}
