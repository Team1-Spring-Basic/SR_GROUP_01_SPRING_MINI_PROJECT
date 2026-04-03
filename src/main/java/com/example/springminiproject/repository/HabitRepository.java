package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitResultMap", value = {
            @Result(property = Habit.Fields.habitId, javaType = UUID.class,
                    typeHandler = UuidTypeHandler.class, column = "habit_id"),
            @Result(property = Habit.Fields.title,       column = "title"),
            @Result(property = Habit.Fields.description, column = "description"),
            @Result(property = Habit.Fields.frequency,   column = "frequency"),
            @Result(property = Habit.Fields.isActive,    column = "is_active"),
            @Result(property = Habit.Fields.createdAt,   column = "created_at")
    })
    @Select(
            "SELECT habit_id, title, description, frequency, is_active, created_at " +
                    "FROM habits " +
                    "ORDER BY created_at DESC " +
                    "LIMIT #{size} OFFSET #{offset}"
    )
    List<Habit> findAll( int size,  int offset);
}


}
