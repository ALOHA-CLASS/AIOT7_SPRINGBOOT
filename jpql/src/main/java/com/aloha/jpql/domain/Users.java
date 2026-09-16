package com.aloha.jpql.domain;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity                   // 엔터티 정의
@Table(name = "users")    // 테이블명 지정
@Data 
public class Users extends BaseEntity {

  @Column(nullable = false, unique = true)  // NOT NULL, UNIQUE
  private String username;    // 아이디
  private String password;    // 비밀번호
  private String name;        // 이름
  private String email;       // 이메일
  
}
