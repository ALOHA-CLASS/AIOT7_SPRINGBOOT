package com.aloha.jpql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity 
@Data 
public class OrderItem extends BaseEntity {
  
  // 외래키 order_no 로, Orders 엔터티와 M:1 매핑
  @ManyToOne 
  @JoinColumn(name = "order_no", nullable = false)
  private Orders orders;

  // 외래키 product_no 로, Product 엔터티와 M:1 매핑
  @ManyToOne 
  @JoinColumn(name = "product_no", nullable = false)
  private Product product;

  private int quantity;     // 수량
  private int price;        // 가격(주문 시)
  
}
