package com.aloha.jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.Users;
import com.aloha.jpql.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional(readOnly = true) 
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<Users> findAll() {
    return userRepository.findAllSort();
  }

  @Override
  public List<Users> search(String keyword) {
    return userRepository.search(keyword);
  }

  @Override
  public Users findByNo(Long no) {
    return userRepository.findById(no).orElse(null);
  }

  @Override
  public long count() {
    return userRepository.count();
  }

  
  
}
