package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.AppUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser saveUser(AppUserRequest request);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean isVerifiedUser(String identifier);
    AppUser getUserByIdentifier(String identifier);
    Void updateVerificationStatus(String identifier);
}
