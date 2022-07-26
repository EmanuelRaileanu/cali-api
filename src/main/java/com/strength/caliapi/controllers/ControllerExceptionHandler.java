package com.strength.caliapi.controllers;

import com.strength.caliapi.exceptions.HttpError;
import com.strength.caliapi.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<HttpError> httpException(HttpException ex) {
        return new ResponseEntity<>(new HttpError(ex.getMessage(), ex.getStatus().value()), ex.getStatus());
    }
}
