package com.aloha.jpql.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 
@Data 
public class Orders extends BaseEntity {

  // user_no 외래키로 Users 엔터티와 1:1매핑
  @ManyToOne 
  @JoinColumn(name = "user_no", nullable = false, unique = false)
  private Users user;

  private LocalDateTime orderDate = LocalDateTime.now(); // 주문일시

  // 주문상태
  @Enumerated(EnumType.STRING)  // DB 에 저장 시, 문자열 타입으로
  private OrderStatus status = OrderStatus.READY;

  // 주문항목 리스트
  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>();

}
