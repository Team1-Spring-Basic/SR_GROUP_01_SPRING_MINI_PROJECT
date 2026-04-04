package com.example.springminiproject.controller;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hobit-logs")
public class HabitLogController {

    private final HabitLogService habitLogService;

    @GetMapping("{habit-id}")
    public ResponseEntity<ApiResponse<HabitLog>> getHobitLogById(
            @PathVariable("habit-id") UUID habitLogId) {

        try {
            HabitLog habitLog = habitLogService.getHabitLogById(habitLogId);

            ApiResponse<HabitLog> response = ApiResponse.<HabitLog>builder()
                    .isSuccess(true)
                    .status(HttpStatus.OK.name())
                    .message("Get HabitLog By ID Successfully")
                    .payload(habitLog)
                    .timestamp(Instant.now()).build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NotFoundException e){
            ApiResponse<HabitLog> response = ApiResponse.<HabitLog>builder()
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
