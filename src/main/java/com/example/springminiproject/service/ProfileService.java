package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;

import java.util.UUID;

public interface ProfileService {
    AppUserResponse getUser(UUID currentUserId);

    AppUserResponse updateUser(UUID currentUserId, ProfileRequest request);

    void deleteUser(UUID currentUserId);
}
