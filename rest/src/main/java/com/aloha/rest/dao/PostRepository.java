package com.aloha.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aloha.rest.domain.Posts;

public interface PostRepository extends JpaRepository<Posts, Long> {
  
}
