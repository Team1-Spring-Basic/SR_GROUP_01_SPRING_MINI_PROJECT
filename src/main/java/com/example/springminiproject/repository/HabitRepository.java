package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.entity.Habit;
import com.example.springminiproject.model.request.HabitRequest;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitResultMap", value = {
            @Result(property = "habitId",       column = "habit_id",    typeHandler = UuidTypeHandler.class),
            @Result(property = "title",         column = "title"),
            @Result(property = "description",   column = "description"),
            @Result(property = "frequency",     column = "frequency"),
            @Result(property = "isActive",      column = "is_active"),
            @Result(property = "createdAt",     column = "h_created_at"),  // match alias

            @Result(property = "appUserResponse.appUserId",       column = "app_user_id",   typeHandler = UuidTypeHandler.class),
            @Result(property = "appUserResponse.username",        column = "username"),
            @Result(property = "appUserResponse.email",           column = "email"),
            @Result(property = "appUserResponse.level",           column = "level"),
            @Result(property = "appUserResponse.xp",              column = "xp"),
            @Result(property = "appUserResponse.profileImageUrl", column = "profile_image"),
            @Result(property = "appUserResponse.isVerified",      column = "is_verified"),
            @Result(property = "appUserResponse.createdAt",       column = "u_created_at")
    })
    @Select("""
    SELECT
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
        FROM habits h
            LEFT JOIN app_users u ON h.app_user_id  = u.app_user_id
        where u.app_user_id = #{reg.appUserId}
        limit #{size} offset #{offset}
"""
    )
    List<Habit> getAllHabits(@Param("size") int size, @Param("offset") int offset, @Param("reg") AppUserResponse appUserResponse);





    @ResultMap("habitResultMap")
    @Select("""
    SELECT
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
        FROM habits h
            LEFT JOIN app_users u ON h.app_user_id  = u.app_user_id
        where u.app_user_id = #{reg.appUserId} AND h.habit_id = #{habitId}
    
"""
    )
    Habit getHabitById(@Param("reg") AppUserResponse userResponse, UUID habitId);



//    @ResultMap("habitResultMap")
//    @Select("""
//     SELECT habit_id
//        FROM
//        habits
//        WHERE app_user_id = #{reg.appUserId};
//""")
//    UUID getHabitIdByUserId(@Param("reg" ) AppUserResponse userResponse);


    @ResultMap("habitResultMap")
    @Select("""
    INSERT INTO habits (title, description, frequency, app_user_id)
    VALUES (#{req.title}, #{req.description}, #{req.frequency}, #{req1.appUserId})
    RETURNING *;
""")
    Habit createHabit( @Param("req1")AppUserResponse userResponse,@Param("req") HabitRequest habitRequest);
}






