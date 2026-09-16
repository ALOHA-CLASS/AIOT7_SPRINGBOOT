package com.aloha.jpql.service;

import java.util.List;

import com.aloha.jpql.domain.Users;

public interface UserService {
  
  List<Users> findAll();
  List<Users> search(String keyword);
  Users findByNo(Long no);
  long count();
  
}
