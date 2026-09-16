package com.aloha.jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

  // 회원을 전체 조회하고 회원번호를 기준으로 내림차순
  @Query("SELECT u FROM Users u ORDER BY u.no DESC")
  List<Users> findAllSort();
  
  // 아이디름 검색하여 회원 목록 조회
  @Query("SELECT u FROM Users u WHERE u.username LIKE CONCAT('%', :keyword, '%')")
  List<Users> search(@Param("keyword") String keyword);
  
}
