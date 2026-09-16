package com.aloha.jpql.service;

import com.aloha.jpql.domain.Cart;

public interface CartService {
  Cart findByNo(Long no);
  Cart findByUserNo(Long userNo);
  long count();
}
