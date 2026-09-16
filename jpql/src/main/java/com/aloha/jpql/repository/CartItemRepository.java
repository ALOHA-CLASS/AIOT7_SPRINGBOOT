package com.aloha.jpql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.CartItem;
import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  // 장바구니별 장바구니 항목 리스트
  @Query("SELECT ci FROM CartItem ci WHERE ci.cart.no = :cartNo")
  List<CartItem> findByCartNo(@Param("cartNo") Long cartNo);
  
  // 회원별 장바구니 항목 리스트
  @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user.no = :userNo")
  List<CartItem> findByUserNo(@Param("userNo") Long userNo);
  
}
