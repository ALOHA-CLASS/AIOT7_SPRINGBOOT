package com.aloha.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.mvc.domain.Posts;
import com.aloha.mvc.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PostServiceImpl implements PostService {
  // ⭐ 의존성 주입
  private final PostRepository postRepository;
  
  @Override
  public List<Posts> list() {
    return postRepository.findAll();
  }

  @Override
  public Posts select(Long no) {
    return postRepository.findById(no).orElse(null);
  }

  @Override
  public Posts create(Posts post) {
    return postRepository.save(post);
  }

  @Override
  public Posts update(Posts post) {
    Posts old = postRepository.findById(post.getNo()).orElse(null);
    if( old == null )
      return null;
    old.setTitle(post.getTitle());
    old.setWriter(post.getWriter());
    old.setContent(post.getContent());
    return postRepository.save(old);
  }

  @Override
  public void delete(Long no) {
    postRepository.deleteById(no);
  }
  
}
