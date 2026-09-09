package com.aloha.jpa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

// 엔터티 정의
@Data 
@Entity 
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // no INT AUTO_INCREMENT PRIAMRY KEY
  private Integer no;
  private String id;
  private String orderName;
  private int totalAmount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Orders() {
    this.id = UUID.randomUUID().toString();
  }

  // INSERT 직전에 실행
  @PrePersist 
  public void preInsert() {
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;
  }

  // UPDATE 직전에 실행
  @PreUpdate  
  public void preUpdate() {
    LocalDateTime now = LocalDateTime.now();
    this.updatedAt = now;
  }
}
