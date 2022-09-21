package com.development.bookstore_orderservice.exception;

import com.development.bookstore_orderservice.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class BookStoreExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> invalidTokenException(InvalidTokenException invalidTokenException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(invalidTokenException.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
