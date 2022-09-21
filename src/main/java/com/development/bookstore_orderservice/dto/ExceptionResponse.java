package com.development.bookstore_orderservice.dto;

public class ExceptionResponse {
    public String errorMessage;

    public ExceptionResponse(String message) {
        this.errorMessage = message;
    }
}
