package com.aloha.mvc.dto;

import lombok.Data;

@Data 
public class UserRequest {

  private String username;    // 아이디
  private String password;    // 패스워드
  private String name;        // 이름
  private String email;       // 이메일
  
}
