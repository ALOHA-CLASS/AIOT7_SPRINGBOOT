package com.aloha.mvc.domain;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder 
@AllArgsConstructor 
@Entity 
public class Users {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;
  @Column(unique = true, length = 36)
  private String id;
  @Column(length = 50)
  private String username;        // 아이디
  @Column(length = 100)
  private String password;        // 비밀번호
  @Column(length = 100)
  private String name;            // 성명
  @Column(length = 100, unique = true)
  private String email;           // 이메일
  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private Date createdAt;
  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private Date updatedAt;

  public Users() {
    this.id = UUID.randomUUID().toString();
  }
  
}
