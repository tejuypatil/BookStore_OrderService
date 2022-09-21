package com.development.bookstore_orderservice.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String token){
        super("Invalid Token:   "+token);
    }
}
