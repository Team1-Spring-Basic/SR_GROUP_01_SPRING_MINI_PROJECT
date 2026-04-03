package com.example.springminiproject.controller;

import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    @Operation(summary = "Get All Habits")




    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabit(
            @RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") Integer page, @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") Integer size) {
        List<Habit> habits = habitService.getAllHabits(page, size);
        ApiResponse<List<Habit>> response = ApiResponse.<List<Habit>>builder()
                .isSuccess(true)
                .message("Habits retrieved successfully")
                .status("success")
                .payload(habits)
                .timestamp(java.time.Instant.now())
                .build();
        return ResponseEntity.ok(response);

    }




}
