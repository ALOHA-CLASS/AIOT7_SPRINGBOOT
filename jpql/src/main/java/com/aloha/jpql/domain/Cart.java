package com.aloha.jpql.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity 
@Data 
public class Cart extends BaseEntity {

  // user_no 를 외래키로, Users 엔터티와 1:1 매핑
  @OneToOne 
  @JoinColumn(name = "user_no", nullable = false, unique = true)
  private Users user;

  // 장바구니 항목
  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> cartItems = new ArrayList<>();
  
}
