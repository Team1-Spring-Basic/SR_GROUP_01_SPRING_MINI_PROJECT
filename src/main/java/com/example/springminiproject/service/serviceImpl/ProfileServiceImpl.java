package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.ProfileRequest;
import com.example.springminiproject.repository.ProfileRepository;
import com.example.springminiproject.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppUserResponse getUser(UUID currentUserId) {
        return modelMapper.map(profileRepository.getUser(currentUserId), AppUserResponse.class);
    }

    @Override
    public AppUserResponse updateUser(UUID currentUserId, ProfileRequest request) {
        return modelMapper.map(profileRepository.updateUser(currentUserId, request), AppUserResponse.class);
    }

    @Override
    public void deleteUser(UUID currentUserId) {
        profileRepository.deleteUser(currentUserId);
    }
}
