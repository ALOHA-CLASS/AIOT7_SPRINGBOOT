package com.aloha.mvc.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aloha.mvc.domain.Users;
import com.aloha.mvc.dto.UserRequest;
import com.aloha.mvc.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Users signup(UserRequest userRequest) {
    // 비밀번호 암호화
    String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

    // 회원정보 등록
    Users user = Users.builder()
                      .id(UUID.randomUUID().toString())
                      .username(userRequest.getUsername())
                      .password(encodedPassword)
                      .name(userRequest.getName())
                      .email(userRequest.getEmail())
                      .build();

    Users newUser = userRepository.save(user);
    return newUser;
  }
  
}
