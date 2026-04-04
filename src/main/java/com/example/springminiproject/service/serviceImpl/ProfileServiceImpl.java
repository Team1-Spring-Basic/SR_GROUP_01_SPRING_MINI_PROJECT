package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.repository.ProfileRepository;
import com.example.springminiproject.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public AppUserResponse getUser() {
        return profileRepository.getUser();
    }

    @Override
    public AppUserResponse updateUser(ProfileRequest request) {
        return profileRepository.updateUser(request);
    }

    @Override
    public void deleteUser() {
        profileRepository.deleteUser();
    }
}
