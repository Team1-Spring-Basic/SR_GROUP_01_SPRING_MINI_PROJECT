package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.repository.UserRepository;
import com.example.springminiproject.service.AuthService;
import com.example.springminiproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
//    private final AuthenticationManager authenticationManager;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public AppUser register(AppUserRequest request) {

        userRepository.saveUser(request);

        return null;
    }

    @Override
    public String login(LoginRequest credentials) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                                                        credentials.getPassword());
//        authenticationManager.authenticate(authentication);

//        AppUser appUser = (AppUser) authentication.getPrincipal();

//        if (appUser == null) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }

//        return jwtUtils.generateToken(appUser.getUsername());
        return "";
    }
}
