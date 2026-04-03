package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.HabitLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "habitLogMapper",  value={
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned")
    })
    @Select("""
        SELECT * FROM habit_logs WHERE habit_log_id = #{habitLogId}
    """)
    HabitLog getHabitLogById(UUID habitLogId);
}
