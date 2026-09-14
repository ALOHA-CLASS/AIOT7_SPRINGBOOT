package com.aloha.mvc.service;

import java.util.List;

import com.aloha.mvc.domain.Posts;

public interface PostService {
  List<Posts> list();
  Posts select(Long no);
  Posts create(Posts post);
  Posts update(Posts post);
  void delete(Long no);
}
