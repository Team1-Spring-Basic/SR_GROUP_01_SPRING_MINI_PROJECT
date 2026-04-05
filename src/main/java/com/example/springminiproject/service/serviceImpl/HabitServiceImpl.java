package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.HabitRequest;
import com.example.springminiproject.repository.HabitRepository;
import com.example.springminiproject.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits(Integer page, Integer size, AppUser userResponse) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabits(size,offset, userResponse);
    }

    @Override
    public Habit createHabit(AppUser userResponse, HabitRequest habitRequest) {
        UUID createdHabitId = habitRepository.createHabit(userResponse, habitRequest).getHabitId();
        return habitRepository.getHabitById(userResponse, createdHabitId);


    }

    @Override
    public Habit getHabitById(AppUser userResponse, UUID habitId) {

        Habit habit = habitRepository.getHabitById(userResponse, habitId);
        if (habit == null) {
            throw  new NotFoundException("Habit not found with the given ID");
        }
        return habit;
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {

        if (habitId == null) {
            throw  new NotFoundException("Habit id not found");
        }

        return habitRepository.updateHabitById(habitId, habitRequest);
    }

    @Override
    public Habit deleteHabitById(UUID habitId) {

        if (habitId == null) {
            throw  new NotFoundException("Habit id not found");
        }
        return habitRepository.deleteHabitById(habitId);
    }
}
