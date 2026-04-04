package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.request.HabitRequest;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size, AppUserResponse userResponse);

    Habit createHabit(AppUserResponse userResponse, HabitRequest habit);

    Habit getHabitById(AppUserResponse userResponse, UUID habitId);

    Habit updateHabitById(UUID habitId, HabitRequest habitRequest);

    Habit deleteHabitById(UUID habitId);
}
