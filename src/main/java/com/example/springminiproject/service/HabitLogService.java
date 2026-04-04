package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.HabitLog;

import java.util.UUID;

public interface HabitLogService {
    HabitLog getHabitLogById(UUID habitLogId);
}
