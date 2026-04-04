package com.example.springminiproject.controller;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.request.HabitRequest;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/habits")
@RequiredArgsConstructor
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SecurityRequirement(name = "bearerAuth")
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    @Operation(summary = "Get All Habits")
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabit(@AuthenticationPrincipal AppUserResponse userResponse,
            @RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") Integer page, @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") Integer size) {

        List<Habit> habits = habitService.getAllHabits(page, size, userResponse);
        ApiResponse<List<Habit>> response = ApiResponse.<List<Habit>>builder()
                .isSuccess(true)
                .message("Habits retrieved successfully")
                .status("success")
                .payload(habits)
                .timestamp(java.time.Instant.now())
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping
    @Operation(summary = "Create a new habit")
    public ResponseEntity<ApiResponse<Habit>> createHabit(@AuthenticationPrincipal AppUserResponse userResponse, @Valid  @RequestBody HabitRequest habitRequest) {

        Habit createdHabit = habitService.createHabit(userResponse, habitRequest);
        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .isSuccess(true)
                .message("Habit created successfully")
                .status("success")
                .payload(createdHabit)
                .timestamp(java.time.Instant.now())
                .build();
        return ResponseEntity.ok(response);}

    @GetMapping("/{habit-id}")
    @Operation(summary = "Get habit by ID")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@AuthenticationPrincipal AppUserResponse userResponse, @PathVariable("habit-id") UUID habitId) {
        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .isSuccess(true)
                .message("Habit retrieved successfully")
                .status("success")
                .payload(habitService.getHabitById(userResponse,habitId))
                .timestamp(java.time.Instant.now())
                .build();
        return ResponseEntity.ok(response);}

}
