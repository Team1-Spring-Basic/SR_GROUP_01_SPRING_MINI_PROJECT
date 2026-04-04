package com.example.springminiproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/hobit-logs/**").permitAll()  // add this
                        .requestMatchers("/api/v1/hobit-logs").permitAll()     // add this
                        // ... your other rules
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
