package com.aloha.mvc.service;

import com.aloha.mvc.domain.Users;
import com.aloha.mvc.dto.UserRequest;

public interface UserService {

  // 회원가입
  Users signup(UserRequest userRequest);
  
}
