package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.request.HabitRequest;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size, AppUser userResponse);

    Habit createHabit(AppUser userResponse, HabitRequest habit);

    Habit getHabitById(AppUser userResponse, UUID habitId);

    Habit updateHabitById(UUID habitId, HabitRequest habitRequest);

    Habit deleteHabitById(UUID habitId);
}
