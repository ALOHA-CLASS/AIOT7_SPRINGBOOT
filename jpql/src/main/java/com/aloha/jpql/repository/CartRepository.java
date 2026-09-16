package com.aloha.jpql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

  // 회원별 장바구니 조회
  @Query("SELECT c FROM Cart c WHERE c.user.no = :userNo")
  Optional<Cart> findByUserNo(@Param("userNo") Long userNo);
  
}
