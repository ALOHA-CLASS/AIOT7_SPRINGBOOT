package com.aloha.crud.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data 
public class Orders {
  private Integer no;
  private String id;
  private String orderName;
  private int totalAmount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // 기본 생성자
  public Orders() {
    this.id = UUID.randomUUID().toString(); // 36자리 UID 자동 생성
  }
}
