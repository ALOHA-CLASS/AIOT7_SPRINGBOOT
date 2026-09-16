package com.aloha.jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.OrderItem;
import com.aloha.jpql.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {

  private final OrderItemRepository orderItemRepository;

  @Override
  public List<OrderItem> findByOrderNo(Long orderNo) {
    return orderItemRepository.findByOrderNo(orderNo);
  }

  @Override
  public OrderItem findByNo(Long no) {
    return orderItemRepository.findById(no).orElse(null);
  }

  @Override
  public int getOrderTotal(Long orderNo) {
    return orderItemRepository.getOrderTotal(orderNo);
  }

  @Override
  public long count() {
    return orderItemRepository.count();
  }
  
}
