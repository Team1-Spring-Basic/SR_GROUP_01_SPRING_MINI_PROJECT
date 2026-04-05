package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.model.request.HabitLogRequest;
import com.example.springminiproject.repository.HabitLogRepository;
import com.example.springminiproject.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public HabitLog getHabitLogById(UUID habitLogId) {

        HabitLog habitLog = habitLogRepository.getHabitLogById(habitLogId);
        if (habitLog == null) {
            throw new NotFoundException(
                    String.format("HabitLog with id %s not found", habitLogId)
            );
        }

        return habitLog;
    }

    @Override
    public HabitLog createHabitLog(HabitLogRequest request) {
        habitLogRepository.insertHabitLog(request);

        HabitLog habitLog = habitLogRepository.getHabitLogById(request.getHabitLogId());

        AppUserResponse user = modelMapper.map(habitLog.getHabit().getAppUserResponse(), AppUserResponse.class);
        updateXpAndLevel(user);

        habitLogRepository.updateXpAndLevel(user.getAppUserId(), user.getXp(), user.getLevel());

        return habitLogRepository.getHabitLogById(request.getHabitLogId());
    }
    private void updateXpAndLevel(AppUserResponse user) {
        int newXp = user.getXp() + 10;
        int newLevel = user.getLevel();

        if (newXp >= 100) {
            newXp = newXp - 100;
            newLevel = newLevel + 1;
        }

        user.setXp(newXp);
        user.setLevel(newLevel);
    }
}
