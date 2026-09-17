package com.aloha.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.querydsl.domain.Product;

// JPA 레포지토리
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
  


}
