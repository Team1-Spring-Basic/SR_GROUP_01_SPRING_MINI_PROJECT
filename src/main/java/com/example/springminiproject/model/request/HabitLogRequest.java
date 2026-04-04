package com.example.springminiproject.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    @NotBlank
    @Schema(example = "COMPLETE")
    private String status;
    private UUID habitId;

    @JsonIgnore
    private UUID habitLogId;
}
