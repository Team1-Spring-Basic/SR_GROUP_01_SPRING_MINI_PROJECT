package com.example.springminiproject.controller;

import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.model.request.auth.RegisterRequest;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.AuthService;
import com.example.springminiproject.service.serviceImpl.MailServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MailServiceImpl mailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@Valid @RequestBody RegisterRequest credentials){

        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.name())
                .message("User registered successfully! Please verify your email to complete the registration.")
                .payload(authService.register(credentials))
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest credentials) throws Exception{

        String token = authService.login(credentials);
        authenticate(credentials.getUsername(), credentials.getPassword());

        ApiResponse<String> response = ApiResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.name())
                .message("Login successful! Authentication token generated.")
                .payload(token)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/resend")
    public ResponseEntity<?> resend(@RequestParam String email){
        mailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("Email has been resent successfully");
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verify(@RequestParam String email, @RequestParam String code){

        authService.verifyCode(email, code);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.name())
                .message("Email successfully verified! You can now log in.")
                .payload(null)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
