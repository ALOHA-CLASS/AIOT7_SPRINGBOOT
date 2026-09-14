package com.aloha.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.mvc.domain.Posts;

public interface PostRepository extends JpaRepository<Posts, Long> {
  
}
