package com.aloha.jpql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity 
@Data 
public class CartItem extends BaseEntity {

  // cart_no 외래키로, Cart 엔터티와 M:1 매핑
  @ManyToOne
  @JoinColumn(name = "cart_no", nullable = false)
  private Cart cart;

  // product_no 외래키로, Product 엔터티와 M:1 매핑
  @ManyToOne 
  @JoinColumn(name = "product_no", nullable = false)
  private Product product;

  private int quantity;     // 수량
  
}
