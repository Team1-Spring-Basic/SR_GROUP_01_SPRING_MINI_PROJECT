package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.repository.AchievementRepository;
import com.example.springminiproject.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<Achievement> getAllAchievements(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return achievementRepository.getAllAchievements(offset, size);
    }

    @Override
    public Achievement getAchievementByAppUserId(String appUserId, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return achievementRepository.getAchievementByAppUserId(appUserId, offset, size);
    }
}
