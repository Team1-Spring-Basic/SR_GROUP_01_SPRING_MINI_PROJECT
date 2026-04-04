package com.example.springminiproject.exception;

public class InvalidVerificationCode extends RuntimeException {
    public InvalidVerificationCode(String message) {
        super(message);
    }
}
