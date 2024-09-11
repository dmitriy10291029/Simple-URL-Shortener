package com.example.url.shortener.controller;

import com.example.url.shortener.exception.LinkNotFoundException;
import com.example.url.shortener.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private record ErrorDetails(int status, String message, String description) { }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<?> handleLinkNotFoundException(LinkNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new ErrorDetails(
                status.value(), ex.getMessage(), request.getDescription(false)), status);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<?> handleValidationFailedException(ValidationFailedException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorDetails(
                status.value(), ex.getMessage(), request.getDescription(false)), status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(new ErrorDetails(
                status.value(), ex.getMessage(), request.getDescription(false)), status);
    }
}