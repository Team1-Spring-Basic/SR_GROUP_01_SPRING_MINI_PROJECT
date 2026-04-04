package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.HabitLog;
import com.example.springminiproject.model.request.HabitLogRequest;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "habitLogResultMap", value = {

            // HabitLog
            @Result(property = "habitLogId", column = "habit_log_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "logDate",    column = "log_date"),
            @Result(property = "status",     column = "status"),
            @Result(property = "xpEarned",   column = "xp_earned"),

            // Habit
            @Result(property = "habit.habitId",     column = "habit_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "habit.title",       column = "title"),
            @Result(property = "habit.description", column = "description"),
            @Result(property = "habit.frequency",   column = "frequency"),
            @Result(property = "habit.isActive",    column = "is_active"),
            @Result(property = "habit.createdAt",   column = "h_created_at"),

            // AppUserResponse (inside habit)
            @Result(property = "habit.appUserResponse.appUserId",       column = "app_user_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "habit.appUserResponse.username",        column = "username"),
            @Result(property = "habit.appUserResponse.email",           column = "email"),
            @Result(property = "habit.appUserResponse.level",           column = "level"),
            @Result(property = "habit.appUserResponse.xp",              column = "xp"),
            @Result(property = "habit.appUserResponse.profileImageUrl", column = "profile_image"),
            @Result(property = "habit.appUserResponse.isVerified",      column = "is_verified"),
            @Result(property = "habit.appUserResponse.createdAt",       column = "u_created_at")
    })
    @Select("""
        SELECT
            hl.habit_log_id,
            hl.log_date,
            hl.status,
            hl.xp_earned,
    
            h.habit_id,
            h.title,
            h.description,
            h.frequency,
            h.is_active,
            h.created_at  AS h_created_at,
    
            u.app_user_id,
            u.username,
            u.email,
            u.level,
            u.xp,
            u.profile_image,
            u.is_verified,
            u.created_at  AS u_created_at
    
        FROM habit_logs hl
            LEFT JOIN habits    h ON hl.habit_id   = h.habit_id
            LEFT JOIN app_users u ON h.app_user_id = u.app_user_id
    
        WHERE hl.habit_log_id = #{habitLogId}
    """)
    HabitLog getHabitLogById(UUID habitLogId);

    @Insert("""
        INSERT INTO habit_logs (habit_id, status)
        VALUES (#{habitId}, #{status})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "habitLogId", keyColumn = "habit_log_id")
    void insertHabitLog(HabitLogRequest request);

    @Update("""
        UPDATE app_users
        SET xp    = #{xp},
            level = #{level}
        WHERE app_user_id = #{appUserId}
    """)
    void updateXpAndLevel(UUID appUserId, Integer xp, Integer level);
}
