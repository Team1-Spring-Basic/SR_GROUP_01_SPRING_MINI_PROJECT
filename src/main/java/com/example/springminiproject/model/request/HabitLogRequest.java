package com.example.springminiproject.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    private String status;
    private UUID habitId;

    @JsonIgnore  // hides from Swagger request body
    private UUID habitLogId;
}
