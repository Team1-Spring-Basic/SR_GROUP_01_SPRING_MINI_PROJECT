package com.example.springminiproject.exception;

public class UserInvalidCredential extends RuntimeException{
    public UserInvalidCredential(String message) {
        super(message);
    }
}
