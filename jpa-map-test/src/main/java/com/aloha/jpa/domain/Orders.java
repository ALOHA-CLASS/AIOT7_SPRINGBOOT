package com.aloha.jpa.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

// 엔터티 정의
@Data 
@Entity 
public class Orders {
  @Id // PK(기본키)
  @GeneratedValue(strategy = GenerationType.IDENTITY) // no INT AUTO_INCREMENT PRIAMRY KEY
  private Integer no;                   // 주문번호

  @Column(unique = true)
  private String id;                    // 주문ID

  @Column(length = 100)
  private String orderName;             // 주문명

  private int totalAmount;              // 총금액

  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private LocalDateTime createdAt;      // 등록일자
  
  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private LocalDateTime updatedAt;      // 수정일자

  // 주문 : 주문항목 = 1 : N
  @OneToMany(
    mappedBy = "order",           // OrderItem 의 order 변수로 관계 매핑
    cascade = CascadeType.ALL,    // 주문 데이터 저장,수정,삭제 시 주문항목 데이터도 연쇄적으로 처리
    orphanRemoval = true          // 주문에서 삭제된 주문항목도 DB에서 삭제
  ) 
  private List<OrderItem> orderItems;

  public Orders() {
    this.id = UUID.randomUUID().toString();
  }

  // INSERT 직전에 실행
  // @PrePersist 
  // public void preInsert() {
  //   LocalDateTime now = LocalDateTime.now();
  //   this.createdAt = now;
  //   this.updatedAt = now;
  // }

  // UPDATE 직전에 실행
  // @PreUpdate  
  // public void preUpdate() {
  //   LocalDateTime now = LocalDateTime.now();
  //   this.updatedAt = now;
  // }
}
