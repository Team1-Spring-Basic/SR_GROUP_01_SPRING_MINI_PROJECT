package com.example.springminiproject.controller;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final ProfileService profileService;

    @Operation(
            summary = "Get user profile"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> getUser(@AuthenticationPrincipal AppUser user) {
        UUID currentUserId = user.getAppUserId();
        AppUserResponse result = profileService.getUser(currentUserId);
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .message("User profile fetched successfully!")
                .status(HttpStatus.OK.name())
                .payload(result)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update user profile"
    )
    @PutMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> updateUser(@AuthenticationPrincipal AppUser user, @RequestBody ProfileRequest request) {
        UUID currentUserId = user.getAppUserId();
        AppUserResponse result = profileService.updateUser(currentUserId, request);
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .message("User profile updated successfully!")
                .status(HttpStatus.OK.name())
                .payload(result)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete user profile"
    )
    @DeleteMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> deleteUser(@AuthenticationPrincipal AppUser user) {
        UUID currentUserId = user.getAppUserId();
        profileService.deleteUser(currentUserId);
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .message("User profile deleted successfully!")
                .status(HttpStatus.OK.name())
                .payload(null)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
