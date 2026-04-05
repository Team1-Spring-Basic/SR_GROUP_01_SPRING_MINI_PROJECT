package com.example.springminiproject.model.entity;

import com.example.springminiproject.model.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Habit {
    private UUID habitId;
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;
    private AppUserResponse appUserResponse;
    private Instant createdAt;
}
