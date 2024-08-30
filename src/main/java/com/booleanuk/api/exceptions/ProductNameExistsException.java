package com.booleanuk.api.exceptions;

public class ProductNameExistsException extends RuntimeException {
    public ProductNameExistsException(String msg) {
        super(msg);
    }
}
