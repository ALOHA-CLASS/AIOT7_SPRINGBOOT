package com.aloha.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.mybatis.domain.Posts;
import com.aloha.mybatis.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PostServiceImpl implements PostService {

  private final PostMapper postMapper;

  @Override
  public List<Posts> list() throws Exception {
    return postMapper.list();
  }

  @Override
  public Posts select(Integer no) throws Exception {
    return postMapper.select(no);
  }

  @Override
  public boolean insert(Posts post) throws Exception {
    return postMapper.insert(post) > 0;
  }

  @Override
  public boolean update(Posts post) throws Exception {
    return postMapper.update(post) > 0;
  }

  @Override
  public boolean delete(Integer no) throws Exception {
    return postMapper.delete(no) > 0;
  }
  
}
