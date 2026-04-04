package com.example.springminiproject.model.request;

import com.example.springminiproject.model.enumation.HabitLogStatus;
import com.example.springminiproject.model.enumation.HabitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.examples.Example;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {

    @NotBlank(message = "must not be blank")
    private String title;
    private String description;

    @Schema(example = "DAILY")
    private HabitStatus frequency;
}
