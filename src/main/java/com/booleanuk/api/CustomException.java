package com.booleanuk.api;

public class CustomException extends Exception{
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}


// throw this error and also add throws Custom...  to method signature