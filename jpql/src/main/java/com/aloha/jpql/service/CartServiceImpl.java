package com.aloha.jpql.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.Cart;
import com.aloha.jpql.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  @Override
  public Cart findByNo(Long no) {
    return cartRepository.findById(no).orElse(null);
  }

  @Override
  public Cart findByUserNo(Long userNo) {
    return cartRepository.findByUserNo(userNo).orElse(null);
  }

  @Override
  public long count() {
    return cartRepository.count();
  }

  
  
  
}
