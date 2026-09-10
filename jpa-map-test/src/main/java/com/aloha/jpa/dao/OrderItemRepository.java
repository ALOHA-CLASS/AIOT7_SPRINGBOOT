package com.aloha.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.jpa.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

  /**
   * 주문별 주문항목 리스트
   * SELECT *
   * FROM order_item
   * WHERE order_no = ?
   */
  List<OrderItem> findByOrderNo(Integer orderNo);
  
  
}
