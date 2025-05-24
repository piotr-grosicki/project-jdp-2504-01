package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductGroupNotFoundException.class)
    public ResponseEntity<Object> handleProductGroupNotFoundException(ProductGroupNotFoundException exception) {
        return new ResponseEntity<>("The product group with a given id does not exist", HttpStatus.BAD_REQUEST);
    }

}
