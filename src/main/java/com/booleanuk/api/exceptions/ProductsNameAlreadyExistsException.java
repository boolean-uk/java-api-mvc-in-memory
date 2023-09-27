package com.booleanuk.api.exceptions;

public class ProductsNameAlreadyExistsException extends RuntimeException{
    public ProductsNameAlreadyExistsException(String message) {
        super(message);
    }
}
