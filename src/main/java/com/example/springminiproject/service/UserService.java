package com.example.springminiproject.service;

import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.AppUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUserResponse saveUser(AppUserRequest request);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean isVerifiedUser(String identifier);
    AppUserResponse getUserByIdentifier(String identifier);
    void updateVerificationStatus(String identifier);
}
