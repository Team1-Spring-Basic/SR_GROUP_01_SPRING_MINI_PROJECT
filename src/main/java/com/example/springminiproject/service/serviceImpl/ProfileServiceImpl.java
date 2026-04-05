package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.repository.ProfileRepository;
import com.example.springminiproject.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public AppUserResponse getUser(UUID currentUserId) {
        return profileRepository.getUser(currentUserId);
    }

    @Override
    public AppUserResponse updateUser(UUID currentUserId, ProfileRequest request) {
        return profileRepository.updateUser(currentUserId, request);
    }

    @Override
    public void deleteUser(UUID currentUserId) {
        profileRepository.deleteUser(currentUserId);
    }
}
