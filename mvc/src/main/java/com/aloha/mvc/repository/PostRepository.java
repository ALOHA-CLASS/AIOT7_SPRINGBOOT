package com.aloha.mvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aloha.mvc.domain.Posts;


public interface PostRepository extends JpaRepository<Posts, Long> {

  // id 로 게시글 조회
  // SELECT * FROM posts WHERE id = ?
  Optional<Posts> findById(String id);

  // id 로 게시글 수정
  // UPDATE posts SET title = ? ... WHERE id = ?
  // ⭐ JPQL
  @Modifying 
  @Query(
    """
      UPDATE Posts p
         SET p.title = :title,
             p.writer = :writer,
             p.content = :content
      WHERE p.id = :id
    """
  )
  int updateById(
    @Param("id") String id,
    @Param("title") String title,
    @Param("writer") String writer,
    @Param("content") String content
  );


  // id 를 기준으로 삭제
  // DELETE FROM posts WHERE id = ?
  // ⭐ JPQL
  @Modifying
  @Query("DELETE FROM Posts p WHERE id = :id")
  int deleteById(@Param("id") String id);

  
}
