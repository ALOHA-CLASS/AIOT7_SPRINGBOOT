package com.aloha.jpql.service;

import java.util.List;

import com.aloha.jpql.domain.OrderItem;

public interface OrderItemService {
  
  List<OrderItem> findByOrderNo(Long orderNo);
  OrderItem findByNo(Long no);
  int getOrderTotal(Long orderNo);
  long count();
  
}
