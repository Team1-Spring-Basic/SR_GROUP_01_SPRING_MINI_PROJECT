package com.example.springminiproject.controller;

import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.model.response.ApiResponseAchievement;
import com.example.springminiproject.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/api/v1/achievements")
public class AchievementController {
    private final AchievementService achievementService;


    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseAchievement<List<Achievement>>> getAllAchievements(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Achievement> achievements = achievementService.getAllAchievements(page, size);
        ApiResponseAchievement<List<Achievement>> response = ApiResponseAchievement.<List<Achievement>>builder().isSuccess(true).message("Retrieved achievements successfully").status("OK").payload(achievements).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/app-users")
    public Achievement getAchievementByAppUserId(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        String appUserId = "dd5a5f04-25b4-4252-9109-37aceae50a1a";
        return achievementService.getAchievementByAppUserId(appUserId, page , size);
    }

}
