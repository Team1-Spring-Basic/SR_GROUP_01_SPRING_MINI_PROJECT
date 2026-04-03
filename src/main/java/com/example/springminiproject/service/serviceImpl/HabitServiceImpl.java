package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.repository.HabitRepository;
import com.example.springminiproject.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits(Integer page, Integer size) {
        Integer offset = (page - 1) * size;

        return List.of();
    }
}
