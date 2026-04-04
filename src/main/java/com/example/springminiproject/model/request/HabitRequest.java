package com.example.springminiproject.model.request;

import com.example.springminiproject.model.enumation.HabitLogStatus;
import com.example.springminiproject.model.enumation.HabitStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    private String title;
    private String description;
    private HabitStatus frequency;
}
