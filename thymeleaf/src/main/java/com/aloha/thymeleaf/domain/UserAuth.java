package com.aloha.thymeleaf.domain;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class UserAuth {

  private String username;  // 아이디
  private String auth;      // 회원권한
  
}
