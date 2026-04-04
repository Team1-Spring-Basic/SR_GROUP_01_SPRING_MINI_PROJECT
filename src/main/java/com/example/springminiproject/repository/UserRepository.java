package com.example.springminiproject.repository;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository {

    @Results(id = "userMapper", value={
        @Result(property = "appUserId", column = "app_user_id"),
        @Result(property = "profileImageUrl", column = "profile_image"),
        @Result(property = "isVerified", column = "is_verified"),
        @Result(property = "createdAt", column = "created_at"),
    })
    @Select("""
       SELECT * from app_users where email = #{username};
    """)
    AppUserResponse getUserByUsername(String username);

    @ResultMap("userMapper")
    @Select("""
       INSERT INTO app_users(username, email, password, profile_image, full_name) values (#{req.username},
                                     #{req.email}, #{req.password},
                                     #{req.profileImageUrl}, 'empty')
       returning *;
    """)
    AppUserResponse saveUser(@Param("req") AppUserRequest request);

    @Select("""
       SELECT exists(SELECT * from app_users where username = #{username});
    """)
    Boolean existsByUsername(String username);

    @Select("""
       SELECT exists(SELECT * from app_users where email = #{email});
    """)
    Boolean existsByEmail(String email);

    @Select("""
       SELECT is_verified from app_users where email = #{identifier} or username = #{identifier};
    """)
    Boolean isVerifiedUser(String identifier);

    @ResultMap("userMapper")
    @Select("SELECT * from app_users where username = #{identifier} or email = #{identifier}")
    AppUserResponse getUserByIdentifier(String identifier);

    @Select("""
        UPDATE app_users
        SET is_verified = true
        where username = #{identifier} or email = #{identifier}
    """)
    Void updateVerificationStatus(String identifier);
}
