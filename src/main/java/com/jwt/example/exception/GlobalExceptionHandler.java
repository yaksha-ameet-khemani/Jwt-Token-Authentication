package com.jwt.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFound resourceNotFound) {
        return new ResponseEntity<>(resourceNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

}
