package com.aloha.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.mvc.domain.Posts;
import com.aloha.mvc.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j 
@Controller 
@RequiredArgsConstructor 
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  /**
   * 게시글 목록
   * ⭐ Model : Controller 에서 View 데이터를 전달하기 위한 객체
   * ⚡ 컨트롤러 메소드에 매개변수로 객체를 선언하면,
   *    스프링이 객체를 생성하여 주입해준다.
   * @return
   */
  @GetMapping("")
  public String list(Model model) {
    // 게시글 목록 데이터 요청
    List<Posts> posts = postService.list();
    // 모델에 "posts" 라는 이름으로 등록 : View 에서 ${posts} 로 사용
    model.addAttribute("posts", posts);
    return "posts/list";
  }

  /**
   * 게시글 조회
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public String read(@PathVariable("id") String id, Model model) {
    // 데이터 조회
    Posts post = postService.selectById(id);
    // 모델에 데이터 등록
    model.addAttribute("post", post);
    return "posts/read";
  }

  /**
   * 게시글 등록 화면
   * @return
   */
  @GetMapping("/create")
  public String create() {
    return "posts/create";
  }

  /**
   * 게시글 등록 처리
   * @param post
   * @return
   */
  @PostMapping("")
  public String createPost(Posts post) {
    // 게시글 등록 처리
    Posts newPost = postService.create(post);

    // 등록 성공 시, 게시글 목록으로 이동
    if( newPost != null )
      return "redirect:/posts";

    // 등록 실패 시, 게시글 등록으로 다시 이동
    return "redirect:/posts/create?error";
  }
  
  /**
   * 게시글 수정
   * @param id
   * @return
   */
  @GetMapping("/{id}/update")
  public String update(@PathVariable("id") String id, Model model) {
    // 데이터 조회
    Posts post = postService.selectById(id);
    // 모델에 데이터 등록
    model.addAttribute("post", post);
    return "posts/update";
  }

  /**
   * 게시글 수정 처리
   * @param post
   * @return
   */
  @PostMapping("/update")
  public String updatePost(Posts post) {
    // 데이터 수정 처리
    Posts updatedPost = postService.updateById(post);
    // 수정 성공 시, 게시글 목록으로 이동
    if( updatedPost != null )
      return "redirect:/posts";
    // 수정 실패 시, 수정화면으로 다시 이동
    return "redirect:/posts/" + post.getId() + "/update?error";
  }
  
  /**
   * 게시글 삭제 처리
   * @param id
   * @return
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("id") String id) {
    // 데이터 삭제 처리
    boolean result = postService.deleteById(id);
    // 삭제 성공 시, 게시글 목록으로 이동
    if( result )
      return "redirect:/posts";

    // 삭제 실패 시, 다시 수정으로 이동
    return "redirect:/posts/" + id + "/update?error";
  }
  
  

  
}
