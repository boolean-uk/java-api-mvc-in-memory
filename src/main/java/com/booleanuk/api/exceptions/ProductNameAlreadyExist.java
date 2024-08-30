package com.booleanuk.api.exceptions;

public class ProductNameAlreadyExist extends RuntimeException {
    public ProductNameAlreadyExist(String message) {
        super(message);
    }
}
