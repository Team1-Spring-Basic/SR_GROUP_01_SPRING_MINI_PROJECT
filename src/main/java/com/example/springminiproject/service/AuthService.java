package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.request.auth.LoginRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AppUser register(AppUserRequest credentials);
    String login(LoginRequest credentials);
}
