package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.repository.UserRepository;
import com.example.springminiproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.getUserByIdentifier(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    @Override
    public AppUserResponse saveUser(AppUserRequest request) {
        return modelMapper.map(userRepository.saveUser(request), AppUserResponse.class);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean isVerifiedUser(String identifier) {
        return userRepository.isVerifiedUser(identifier);
    }

    @Override
    public AppUserResponse getUserByIdentifier(String identifier) {
        return modelMapper.map(userRepository.getUserByIdentifier(identifier), AppUserResponse.class);
    }

    @Override
    public void updateVerificationStatus(String identifier) {
        userRepository.updateVerificationStatus(identifier);
    }

}
