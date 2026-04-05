package com.example.springminiproject.exception;

public class UserInvalidCredential extends RuntimeException{
    public UserInvalidCredential() {
        super("Authentication required or token is invalid");
    }
}
