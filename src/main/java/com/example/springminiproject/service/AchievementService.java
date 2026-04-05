package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    List<Achievement> getAllAchievements(int page, int size);

    Achievement getAchievementsById(UUID achievementId);
}
