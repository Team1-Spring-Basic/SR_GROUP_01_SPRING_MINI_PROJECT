package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.service.MailService;
import com.example.springminiproject.util.OtpCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;

    public void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String otp = OtpCodeGenerator.generate();

        String content = "your verification code is " + otp + "\nthis code will expire in 5 minutes.";

        redisTemplate.opsForValue().set(email, otp, 5, TimeUnit.MINUTES);

        message.setSubject("Verification Code");
        message.setText(content);
        message.setFrom("yannvanneth.27@gmail.com");
        message.setTo(email);
        mailSender.send(message);
    }
}
