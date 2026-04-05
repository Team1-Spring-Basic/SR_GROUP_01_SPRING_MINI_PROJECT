package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface ProfileRepository {
    @Results(id = "ProfileMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
        select * from app_users where app_user_id=#{currentUserId};
    """)
    AppUserResponse getUser(UUID currentUserId);

    @ResultMap("ProfileMapper")
    @Select("""
        update app_users set username = #{req.username}, profile_image = #{req.profileImageUrl} where app_user_id=#{currentUserId} returning *;
    """)
    AppUserResponse updateUser(UUID currentUserId, @Param("req") ProfileRequest request);


    @ResultMap("ProfileMapper")
    @Select("""
        delete from app_users where app_user_id=#{currentUserId};
    """)
    void deleteUser(UUID currentUserId);
}
