package com.aloha.jpql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.OrderStatus;
import com.aloha.jpql.domain.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

  // 회원별 주문내역
  @Query("SELECT o FROM Orders o WHERE o.user.no = :userNo ORDER BY o.no DESC")
  List<Orders> findByUserNo(@Param("userNo") Long userNo);

  // 주문정보와 연관된 회원정보를 조인하여 조회
  @Query("SELECT o FROM Orders o JOIN FETCH o.user WHERE o.no = :no")
  Optional<Orders> findDetail(@Param("no") Long no);

  // 주문상태별 주문 개수 조회
  @Query("SELECT o.status, COUNT(o) FROM Orders o GROUP BY o.status")
  List<Object[]> countByStatus();

  // 상태를 조건으로 주문 내역 조회
  List<Orders> findByStatus(OrderStatus status);
  
}
