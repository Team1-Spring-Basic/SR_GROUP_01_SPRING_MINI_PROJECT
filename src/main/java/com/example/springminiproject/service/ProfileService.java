package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;

public interface ProfileService {
    AppUserResponse getUser();

    AppUserResponse updateUser(ProfileRequest request);

    void deleteUser();
}
