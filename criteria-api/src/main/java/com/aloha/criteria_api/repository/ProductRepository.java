package com.aloha.criteria_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.criteria_api.domain.Product;

// JPA 레포지토리
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
  


}
