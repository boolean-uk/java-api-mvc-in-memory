package com.booleanuk.api.models;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

  public static class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message) {
      super(message);
    }

  }
}
