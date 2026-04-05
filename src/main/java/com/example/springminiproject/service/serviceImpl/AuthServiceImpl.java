package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.exception.*;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.model.request.AppUserRequest;
import com.example.springminiproject.model.request.auth.LoginRequest;
import com.example.springminiproject.model.request.auth.RegisterRequest;
import com.example.springminiproject.service.AuthService;
import com.example.springminiproject.service.UserService;
import com.example.springminiproject.util.IdentifierUtils;
import com.example.springminiproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MailServiceImpl mailService;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public AppUserResponse register(RegisterRequest request) {
        AppUserRequest userRequest = AppUserRequest.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImageUrl(request.getProfileImageUrl())
                .build();

        Boolean usernameExist = userService.existsByUsername(request.getUsername());

        if (usernameExist) {
            throw new ConflictException("Username already exists");
        }

        Boolean emailExist = userService.existsByEmail(request.getEmail());

        if (emailExist) {
            throw new ConflictException("Email already exists");
        }

        AppUserResponse newUser = userService.saveUser(userRequest);

        mailService.sendEmail(newUser.getEmail());

        return newUser;
    }

    @Override
    public String login(LoginRequest credentials) {

        Boolean isEmail = IdentifierUtils.isEmail(credentials.getUsername());

        Boolean exists = isEmail ? userService.existsByEmail(credentials.getUsername())
                                 : userService.existsByUsername(credentials.getUsername());

        if (!exists) {
            throw new UserInvalidCredential();
        }

        Boolean isVerifiedUser = userService.isVerifiedUser(credentials.getUsername());

        if (!isVerifiedUser) {
            throw new UserEmailNotVerifiedException();
        }

        return jwtUtils.generateToken(credentials.getUsername());
    }

    @Override
    public Void verifyCode(String email, String code) {

        Boolean exists = userService.existsByEmail(email);

        if (!exists) {
            throw new NotFoundException("this email does not exist");
        }

        String validOtp = redisTemplate.opsForValue().get(email);

        if (validOtp != null && !validOtp.equals(code)){
            throw new InvalidVerificationCode("The OTP entered is invalid or has expired. Please request a new OTP and try again.");
        }

        userService.updateVerificationStatus(email);

        return null;
    }
}
