package com.aloha.jpql.service;

import java.util.List;

import com.aloha.jpql.domain.CartItem;

public interface CartItemService {

  List<CartItem> findByCartNo(Long cartNo);
  List<CartItem> findByUserNo(Long userNo);
  CartItem findByNo(Long no);
  long count();
  
}
