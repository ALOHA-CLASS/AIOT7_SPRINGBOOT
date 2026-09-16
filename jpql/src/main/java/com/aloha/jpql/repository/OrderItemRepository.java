package com.aloha.jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  // 주문번호를 기준으로 주문 상품 목록 조회
  @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.product WHERE oi.orders.no = :orderNo")
  List<OrderItem> findByOrderNo(@Param("orderNo") Long orderNo);

  // 주문번호를 기준으로 주문 총 금액 계산
  @Query("SELECT COALESCE(SUM(oi.price * oi.quantity), 0) FROM OrderItem oi WHERE oi.orders.no = :orderNo")
  int getOrderTotal(@Param("orderNo") Long orderNo);
  
}
