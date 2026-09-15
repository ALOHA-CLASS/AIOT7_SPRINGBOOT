package com.aloha.thymeleaf.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Users {

  private Long no;
  private String id;
  private String username;
  private String password;
  private String name;

  private String gender;
  private String type;

  private Date createdAt;
  private Date updatedAt;

  private List<UserAuth> authList;
 
  // 사용자 권한 확인 메소드
  public boolean containsAuth(String auth) {
    for (UserAuth userAuth : authList) {
      if( userAuth.getAuth().equals(auth) )
        return true;
    }
    return false;
  }
}
