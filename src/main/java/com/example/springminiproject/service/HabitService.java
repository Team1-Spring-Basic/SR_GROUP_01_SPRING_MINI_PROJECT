package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.Habit;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size);
}
