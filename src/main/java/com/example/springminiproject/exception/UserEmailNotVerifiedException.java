package com.example.springminiproject.exception;

public class UserEmailNotVerifiedException extends RuntimeException {
    public UserEmailNotVerifiedException() {
        super("Your email address is not yet verified. Please verify your email before logging in.");
    }
}
