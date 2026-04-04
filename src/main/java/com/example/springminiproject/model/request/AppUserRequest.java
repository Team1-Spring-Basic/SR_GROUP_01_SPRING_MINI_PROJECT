package com.example.springminiproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRequest {
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
}
