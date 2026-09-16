package com.aloha.jpql.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity 
@Data 
public class Product extends BaseEntity {

  @Column(nullable = false)
  String name;            // 상품명
  private int price;      // 가격
  private int stock;      // 재고
  
}
