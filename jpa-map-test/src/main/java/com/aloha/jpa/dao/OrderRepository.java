package com.aloha.jpa.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.jpa.domain.Orders;

/**
 * ⭐ JpaRepository 를 상속하면 JPA 기본 CRUD 기능을 사용할 수 있다!
 * - save()         : INSERT, UPDATE
 * - findAll()      : SELECT * FROM ? 
 * - findById()     : SELECT * FROM ? WHERE id = ?
 * - deleteById()   : DELETE FROM ? WHERE id = ?
 * - count()        : SELECT COUNT(*) FROM ?
 */
public interface OrderRepository extends JpaRepository<Orders, Integer> {

  // ⭐ OrderItem 주문항목 목록도 같이 조회
  @EntityGraph(attributePaths = "orderItems")
  Optional<Orders> findById(Integer no);

}
