package com.example.springminiproject.controller;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @Operation(
            summary = "Get user profile"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> getUser() {
        AppUserResponse user = profileService.getUser();
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .message("User profile fetched successfully!")
                .status(HttpStatus.OK.name())
                .payload(user)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update user profile"
    )
    @PutMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> updateUser(@RequestBody ProfileRequest request) {
        AppUserResponse user = profileService.updateUser(request);
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .isSuccess(true)
                .message("User profile updated successfully!")
                .status(HttpStatus.OK.name())
                .payload(user)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete user profile"
    )
    @DeleteMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> deleteUser() {
        profileService.deleteUser();
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
