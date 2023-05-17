package com.mohsin.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), getExceptionMessage(ex));
    }

    private String getExceptionMessage(Exception ex) {
        final String msgPrefix = "@RestControllerAdvice";
        return  msgPrefix + " - " + ex.getMessage();
    }
}
