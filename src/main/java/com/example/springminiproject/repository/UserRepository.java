package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository {

    @Results(id = "userMapper", value={
        @Result(property = "appUserId", column = "app_user_id"),
        @Result(property = "profileImageUrl", column = "profile_image_url"),
        @Result(property = "isVerified", column = "is_verified"),
        @Result(property = "createdAt", column = "created_at"),
    })
    @Select("""
       SELECT * from app_users;
    """)
    AppUser getUserByUsername(String username);

    @ResultMap("userMapper")
    @Select("""
       INSERT INTO app_users values (default, #{req.username},
                                     'empty', #{req.email}, #{req.password},
                                     #{req.level}, #{req.xp}, #{req.level},
                                     #{req.profileImage}, #{req.isVerified})
       returning *;
    """)
    AppUser saveUser(@Param("req") AppUserRequest request);
}
