package com.example.springminiproject.model.entity;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {
    private UUID habitLogId;
    private Instant logDate;
    private String status;
    private Integer xpEarned;
    private Habit habit;
}
