package com.aloha.jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.jpql.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  // 상품 검색 및 내림차순 정렬
  @Query("SELECT p "
       + "FROM Product p "
       + "WHERE p.name LIKE CONCAT('%', :keyword, '%') "
       + "ORDER BY p.no DESC "
      )
  List<Product> search(@Param("keyword") String keyword);

}
