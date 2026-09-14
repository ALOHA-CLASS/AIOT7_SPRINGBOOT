package com.aloha.rest.service;

import java.util.List;

import com.aloha.rest.domain.Posts;

public interface PostService {
  List<Posts> list();
  Posts select(Long no);
  Posts create(Posts post);
  Posts update(Posts post);
  void delete(Long no);
}
