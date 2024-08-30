package com.booleanuk.api.exceptions;

import com.booleanuk.api.model.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
