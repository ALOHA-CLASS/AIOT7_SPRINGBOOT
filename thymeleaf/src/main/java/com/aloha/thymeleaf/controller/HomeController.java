package com.aloha.thymeleaf.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aloha.thymeleaf.domain.Person;
import com.aloha.thymeleaf.domain.UserAuth;
import com.aloha.thymeleaf.domain.Users;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


/**
 * HomeController
 */
@Slf4j 
@Controller 
public class HomeController {

  @GetMapping({"/", "/{page}"})
  public String home(
    @PathVariable(value = "page", required = false) String page,
    Model model,
    HttpSession session,
    Person person,
    Users loginUser
  ) { // 매개변수에 객체를 지정하면, 기본 생성하여 가져온다.
    // Person 👩‍💼
    person.setName("ALOHA");
    person.setAge(25);
    model.addAttribute("person", person);
    
    // 컬렉션
    List<String> items = List.of("item1", "item2", "item3", "item4", "item5");
    model.addAttribute("items", items);

    // User - UserAuth 리스트
    // 회원과 회원권한 리스트 추가해보기
    List<Users> userList = List.of(
                                    Users.builder().username("user1").name("사용자1").build(),
                                    Users.builder().username("user2").name("사용자2").build(),
                                    Users.builder().username("user3").name("사용자3").build(),
                                    Users.builder().username("user4").name("사용자4").build(),
                                    Users.builder().username("user5").name("사용자5").build()
                                  );
    // ROLE_USER : 사용자 권한, ROLE_ADMIN : 관리자 권한
    UserAuth roleUser = UserAuth.builder().auth("ROLE_USER").build();
    UserAuth roleAdmin = UserAuth.builder().auth("ROLE_ADMIN").build();

    List<UserAuth> authList = List.of( roleUser, roleAdmin );
    // 사용자들에게 사용자/관리자 권한 부여
    for (Users user : userList) {
      user.setAuthList(authList);
    }
    model.addAttribute("userList", userList);

    // 로그인 유저
    loginUser.setNo(1L);
    loginUser.setId(UUID.randomUUID().toString());
    loginUser.setUsername("ALOHA");
    loginUser.setPassword("123456");
    loginUser.setName("알로하");
    loginUser.setGender("여자");
    loginUser.setType("관리자");
    loginUser.setCreatedAt(new Date());
    loginUser.setUpdatedAt(new Date());
    loginUser.setAuthList(authList);

    session.setAttribute("loginUser", loginUser);
    model.addAttribute("loginUser", loginUser);
      
    // 뷰 지정
    return page == null ? "index" : page;
  }
  

  
}