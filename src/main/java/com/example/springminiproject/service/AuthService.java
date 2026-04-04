package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.model.request.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {
    AppUser register(RegisterRequest credentials);
    String login(LoginRequest credentials);
    Void verifyCode(String email, String code);
}
