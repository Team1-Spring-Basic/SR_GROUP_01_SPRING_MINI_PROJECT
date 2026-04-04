package com.example.springminiproject.exception;

public class UserEmailNotVerifiedException extends RuntimeException {
    public UserEmailNotVerifiedException(String message) {
        super(message);
    }
}
