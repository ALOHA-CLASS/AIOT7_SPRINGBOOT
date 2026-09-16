package com.aloha.jpql.domain;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@MappedSuperclass     // 다른 엔터티 클래스들이 상속받아 공통 변수를 가지도록 해준다.
public abstract class BaseEntity {
  
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;
  @Column(unique = true, length = 36)
  private String id;

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

  @PrePersist
  private void generateId() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString();
    }
  }
}
