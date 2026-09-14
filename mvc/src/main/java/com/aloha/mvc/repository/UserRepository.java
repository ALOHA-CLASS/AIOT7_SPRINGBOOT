package com.aloha.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.mvc.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
  
}
