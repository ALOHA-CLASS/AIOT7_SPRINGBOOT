package com.aloha.querydsl.domain;

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

  // PK(기본키) : no 번호 자동증가(AUTO_INCREMENT)
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;

  // UK(고유키) : UUID 중복되지 않는 문자열
  @Column(unique = true, length = 36)
  private String id;

  // 등록일자
  @Column(
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      insertable = false,
      updatable = false
  )
  private Date createdAt;

  // 수정일자
  @Column(
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
      insertable = false,
      updatable = false
  )
  private Date updatedAt;

  // 저장 전에 미리 실행시킬 메소드 지정
  @PrePersist
  private void generateId() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString(); // 등록할 때 UUID 자동으로 세팅
    }
  }
}

