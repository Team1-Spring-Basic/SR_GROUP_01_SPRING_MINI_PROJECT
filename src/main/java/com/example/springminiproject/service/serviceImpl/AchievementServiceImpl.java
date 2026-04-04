package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.repository.AchievementRepository;
import com.example.springminiproject.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAchievements(int page, int size) {

        if (page <= 0){
            throw new NotFoundException(
                    String.format("Page must be greater then 0", page)
            );
        }

        if (size <= 0){
            throw new NotFoundException(
                    String.format("Size must be greater then 0", size)
            );
        }
        int offSet = (page - 1) * size;

        return achievementRepository.getAllAchievements(offSet, size);
    }

    @Override
    public Achievement getAchievementsById(UUID achievementId) {

        if (achievementId == null){
            throw new NotFoundException(
                    String.format("Achievement id %d must not be null", achievementId)
            );
        }

        return achievementRepository.getAchievementById(achievementId);
    }
}
