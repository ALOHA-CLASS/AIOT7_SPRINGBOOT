package com.aloha.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.mvc.domain.Users;
import com.aloha.mvc.dto.UserRequest;
import com.aloha.mvc.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j 
@Controller 
@RequiredArgsConstructor 
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  /**
   * 회원가입 화면
   * @param param
   * @return
   */
  @GetMapping("/signup")
  public String signup() {
    // 뷰 지정
    return "signup";
  }

  /**
   * 회원가입 처리
   * @param userRequest
   * @return
   */
  @PostMapping("")
  public String signup(UserRequest userRequest) {
      Users newUser = userService.signup(userRequest);
      // 회원가입 성공 ➡ 메인화면
      if( newUser != null )
        return "redirect:/";
      // 회원가입 실패
      return "redirect:/users/signup?error";
  }
  
  
  
}
