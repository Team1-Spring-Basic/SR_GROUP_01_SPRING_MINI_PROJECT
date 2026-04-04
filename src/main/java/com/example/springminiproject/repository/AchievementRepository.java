package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AchievementRepository {
    @Results(id = "achievementMapper", value = {
            @Result(property = "achievementId", column = "achievement_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "xpRequest", column = "xp_required")
    })
    @Select("""
                SELECT * FROM achievements OFFSET #{offset} LIMIT #{size};
            """)
    List<Achievement> getAllAchievements(Integer offset, Integer size);
}
