package com.aloha.jpa.service;

import java.util.List;

import com.aloha.jpa.domain.OrderItem;
import com.aloha.jpa.dto.OrderItemRequest;

public interface OrderItemService {

  // 목록
  List<OrderItem> list();

  // 주문별 목록
  List<OrderItem> listByOrderNo(Integer no);

  // 조회
  OrderItem select(Integer no);

  // 등록
  OrderItem insert(OrderItemRequest orderItemRequest);

  // 수정
  OrderItem update(OrderItem orderItem);
  
  // 삭제
  void delete(Integer no);
  
}
