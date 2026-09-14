package com.aloha.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration    // 스프링 설정 클래스로 지정
public class SecurityConfig {

  @Bean 
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/users/**").permitAll()
        .requestMatchers("/**").permitAll()
        .anyRequest().authenticated()
      )
      .csrf(csrf -> csrf.disable())
      ;

    return http.build();
  }


  @Bean   // 메소드가 반환하는 객체를 스프링 빈으로 등록
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
}
