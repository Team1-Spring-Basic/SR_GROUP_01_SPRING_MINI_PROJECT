package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.model.request.HabitLogRequest;

import java.util.UUID;

public interface HabitLogService {
    HabitLog getHabitLogById(UUID habitLogId);

    HabitLog createHabitLog(HabitLogRequest request);
}
