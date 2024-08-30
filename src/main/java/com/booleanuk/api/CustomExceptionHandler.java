package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
/*
 Annotation to handle exceptions globally for the whole application.
 Specifically for REST controllers.
 Defines methods to handle exceptions thrown by any controllers in the application.
*/
public class CustomExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    /*
    Annotation specifies that the method should handle exceptions of the type ResponseStatusException.
    When ResponseStatusException is thrown by any controller, this method is invoked to handle it.
     */
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // ResponseEntity is a Spring class used to represent the entire HTTP response.
        if (ex.getMessage().contains("418")) {
            String htmlResponse = "<img src='https://media2.giphy.com/media/ARmZmMqobLtZKrJRrU/200w.gif?cid=6c09b95287okszcbz59ao584wd835y08z53ju0u1pwjpok20&ep=v1_gifs_search&rid=200w.gif&ct=g'>";
            return new ResponseEntity<>(htmlResponse, HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

