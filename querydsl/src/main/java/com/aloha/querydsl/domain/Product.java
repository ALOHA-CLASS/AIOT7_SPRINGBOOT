package com.aloha.querydsl.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity 
@Data 
public class Product extends BaseEntity {

  @Column(nullable = false)
  String name;      // 상품명
  int price;        // 가격
  int stock;        // 재고

}
