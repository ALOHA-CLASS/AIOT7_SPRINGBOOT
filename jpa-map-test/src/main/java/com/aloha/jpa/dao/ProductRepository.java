package com.aloha.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.jpa.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  
}
