package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.util.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

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
        select * from app_users;
    """)
    AppUserResponse getUser();

    @ResultMap("ProfileMapper")
    @Select("""
        update app_users set username = #{req.username}, profile_image = #{req.profileImageUrl} returning *;
    """)
    AppUserResponse updateUser(@Param("req") ProfileRequest request);


    @ResultMap("ProfileMapper")
    @Select("""
        delete from app_users returning *
    """)
    void deleteUser();
}
