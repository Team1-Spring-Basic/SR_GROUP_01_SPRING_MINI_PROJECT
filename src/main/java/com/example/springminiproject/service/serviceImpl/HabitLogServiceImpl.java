package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.repository.HabitLogRepository;
import com.example.springminiproject.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;

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
}
