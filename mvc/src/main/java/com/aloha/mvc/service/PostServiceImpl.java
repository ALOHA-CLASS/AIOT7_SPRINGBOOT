package com.aloha.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.mvc.domain.Posts;
import com.aloha.mvc.repository.PostRepository;

import jakarta.transaction.Transactional;
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
    // no (PK)를 기준으로 조회
    // Posts old = postRepository.findById(post.getNo()).orElse(null);

    // id 를 기준으로 조회
    Posts old = postRepository.findById(post.getId()).orElse(null);

    if( old == null )
      return null;
    // 수정할 데이터로 세팅
    old.setTitle(post.getTitle());
    old.setWriter(post.getWriter());
    old.setContent(post.getContent());
    // 저장 (UPDATE)
    return postRepository.save(old);
  }

  @Override
  public void delete(Long no) {
    postRepository.deleteById(no);
  }

  @Override
  public Posts selectById(String id) {
    return postRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional 
  public Posts updateById(Posts post) {
    // id 를 기준으로 수정
    int result = postRepository.updateById(
                                  post.getId(), 
                                  post.getTitle(), 
                                  post.getWriter(), 
                                  post.getContent()
                                );
    // 수정 실패
    if( result == 0 ) 
      return null;

    // 수정 성공
    return post;
  }

  @Override
  @Transactional 
  public boolean deleteById(String id) {
    int result = postRepository.deleteById(id);

    // 삭제 성공
    if( result > 0 )
      return true;

    // 삭제 실패
    return false;
  }
  
}
