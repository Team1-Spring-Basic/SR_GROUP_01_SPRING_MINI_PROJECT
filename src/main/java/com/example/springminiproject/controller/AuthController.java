package com.example.springminiproject.controller;

import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody AppUserRequest credentials){
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(credentials));
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(credentials));
    }
}
