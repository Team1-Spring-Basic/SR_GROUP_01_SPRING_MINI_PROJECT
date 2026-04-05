package com.example.springminiproject.controller;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.model.enumation.ApiStatus;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById(
            @PathVariable("habit-id") UUID habitId,
            @RequestBody HabitRequest habitRequest) {

        try {
            Habit habit = habitService.updateHabitById(habitId, habitRequest);

            ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                    .isSuccess(true)
                    .message("Update Habit by Id Successfully")
                    .payload(habit)
                    .timestamp(java.time.Instant.now())
                    .build();
            return ResponseEntity.ok(response);
        } catch (NotFoundException e){
            ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                    .isSuccess(false)
                    .status(String.valueOf(ApiStatus.NOT_FOUND))
                    .message(e.getMessage())
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") UUID habitId) {
        try {
            Habit habit = habitService.deleteHabitById(habitId);

            ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                    .isSuccess(true)
                    .message("Delete Habit by Id Successfully")
                    .payload(habit)
                    .timestamp(java.time.Instant.now())
                    .build();
            return ResponseEntity.ok(response);
        } catch (NotFoundException e){
            ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                    .isSuccess(false)
                    .status(String.valueOf(ApiStatus.NOT_FOUND))
                    .message(e.getMessage())
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
