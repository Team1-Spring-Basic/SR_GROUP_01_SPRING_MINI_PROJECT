package com.example.springminiproject.controller;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.AchievementService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/achievements")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SecurityRequirement(name = "bearerAuth")
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping
    private ResponseEntity<ApiResponse<List<Achievement>>> getAchievements(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){

        try {
            List<Achievement> achievements = achievementService.getAllAchievements(page, size);

            ApiResponse<List<Achievement>> response = ApiResponse.<List<Achievement>>builder()
                    .isSuccess(true)
                    .status(HttpStatus.OK.name())
                    .message("Get Achievement Successfully")
                    .payload(achievements)
                    .timestamp(Instant.now()).build();
            return ResponseEntity.ok(response);
        } catch (NotFoundException e){
            ApiResponse<List<Achievement>> response = ApiResponse.<List<Achievement>>builder()
                    .isSuccess(false)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(e.getMessage())
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{achievement-id}")
    private ResponseEntity<ApiResponse<Achievement>> getAchievementById(@PathVariable("achievement-id") UUID achievementId){

        try {
            Achievement achievement = achievementService.getAchievementsById(achievementId);

            ApiResponse<Achievement> response = ApiResponse.<Achievement>builder()
                    .isSuccess(true)
                    .status(HttpStatus.OK.name())
                    .message("Get Achievement By ID Successfully")
                    .payload(achievement)
                    .timestamp(Instant.now()).build();
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            ApiResponse<Achievement> response = ApiResponse.<Achievement>builder()
                    .isSuccess(false)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(e.getMessage())
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
