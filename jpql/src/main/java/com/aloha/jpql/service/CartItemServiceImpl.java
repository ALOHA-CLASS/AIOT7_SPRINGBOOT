package com.aloha.jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.CartItem;
import com.aloha.jpql.repository.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

  private final CartItemRepository cartItemRepository;

  @Override
  public List<CartItem> findByCartNo(Long cartNo) {
    return cartItemRepository.findByCartNo(cartNo);
  }

  @Override
  public List<CartItem> findByUserNo(Long userNo) {
    return cartItemRepository.findByUserNo(userNo);
  }

  @Override
  public CartItem findByNo(Long no) {
    return cartItemRepository.findById(no).orElse(null);
  }

  @Override
  public long count() {
    return cartItemRepository.count();
  }
  
}
