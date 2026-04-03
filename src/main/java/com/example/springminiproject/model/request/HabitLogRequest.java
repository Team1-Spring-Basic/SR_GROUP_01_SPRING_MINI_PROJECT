package com.example.springminiproject.model.request;

import com.example.springminiproject.model.entity.Habit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    private String status;
    private Habit habit;
}
