package com.booleanuk.api.exceptions;

public class NoProductsInCategoryException extends RuntimeException {
    public NoProductsInCategoryException(String msg) {
        super(msg);
    }
}
