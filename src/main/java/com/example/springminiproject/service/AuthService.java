package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.model.request.auth.RegisterRequest;

public interface AuthService {
    AppUserResponse register(RegisterRequest credentials);
    String login(LoginRequest credentials);
    Void verifyCode(String email, String code);
}
