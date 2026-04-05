package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.Achievement;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AchievementRepository {

    @Results(id = "achievementMapper", value = {
            @Result(property = "achievementId",column = "achievement_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "xpRequest", column = "xp_required")
    })
    @Select("""
        SELECT * FROM achievements OFFSET #{offSet} LIMIT #{size};
    """)
    List<Achievement> getAllAchievements(int offSet, int size);

    @ResultMap("achievementMapper")
    @Select("""
        SELECT * FROM achievements WHERE achievement_id =#{achievementId}
    """)
    Achievement getAchievementById(UUID achievementId);
}
