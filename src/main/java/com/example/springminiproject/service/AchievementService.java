package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.Achievement;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAllAchievements(Integer page, Integer size);
}
