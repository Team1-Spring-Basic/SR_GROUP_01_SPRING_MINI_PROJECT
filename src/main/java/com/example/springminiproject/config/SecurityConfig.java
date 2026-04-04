package com.example.springminiproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityconfig(HttpSecurity http){
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}
