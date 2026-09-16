package com.aloha.jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.Product;
import com.aloha.jpql.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> serach(String keyword) {
    return productRepository.search(keyword);
  }

  @Override
  public Product findByNo(Long no) {
    return productRepository.findById(no).orElse(null);
  }

  @Override
  public long count() {
    return productRepository.count();
  }

  
  
}
