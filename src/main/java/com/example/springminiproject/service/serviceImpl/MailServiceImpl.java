package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.AppUser;
import com.example.springminiproject.model.response.AppUserResponse;
import com.example.springminiproject.service.MailService;
import com.example.springminiproject.service.UserService;
import com.example.springminiproject.util.OtpCodeGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public void sendEmail(String email) {
        try{
            String otp = OtpCodeGenerator.generate();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            AppUserResponse user = userService.getUserByIdentifier(email);

            redisTemplate.opsForValue().set(email, otp, 5, TimeUnit.MINUTES);

            Context context = new Context();
            context.setVariable("otp", otp.toCharArray());
            context.setVariable("email", user.getUsername());
            context.setVariable("expiration", "5");

            helper.setTo(email);
            helper.setSubject("Code Verification");
            helper.setFrom("yannvanneth.27@gmail.com");
            helper.setText(templateEngine.process("email", context), true);

            mailSender.send(message);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
