package com.aloha.response.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.response.dto.Board;

import lombok.extern.slf4j.Slf4j;




/**
 * 게시글 목록/조회
 * [GET]      - /board/list       - /board/list.html
 * [GET]      - /board/read       - /board/read.html
 * 
 * 게시글 등록
 * [GET]      - /board/create     - /board/create.html
 * [POST]     - /board/create     - redirect:/board/list
 * 
 * 게시글 수정
 * [GET]      - /board/update     - /board/update.html
 * [POST]     - /board/update     - redirect:/board/list
 * 
 * 게시글 수정
 * [POST]     - /board/delete     - redirect:/board/list
 * 
 */
@Slf4j 
@Controller 
@RequestMapping("/board")     // 클래스 레벨 요청경로 매핑
public class BoardController {

  /**
   * 게시글 목록
   * 🔗 /board/list
   * 🎁 model : boardlist
   * @return
   */
  @GetMapping("/list")
  public String list(Model model) {
    log.info("[GET] - /board/list");
    // 데이터 요청
    List<Board> boardList = new ArrayList<>();
    boardList.add(new Board(1, "제목1", "작성자1","내용1"));
    boardList.add(new Board(2, "제목2", "작성자2","내용2"));
    boardList.add(new Board(3, "제목3", "작성자3","내용3"));
    // 모델 등록
    model.addAttribute("boardList", boardList);
    // 뷰 지정
    return "board/list";
  }

  /**
   * 게시글 조회
   * 🔗 /board/read
   * 🎁 model : board
   * @param model
   * @param no
   * @return
   */
  @GetMapping("/read")
  public String read(Model model, @RequestParam ("no") int no) {
    log.info("[GET] - /board/read");
    log.info("no : {}", no);
    // 데이터 요청
    Board board = Board.builder()
                       .no(no)
                       .title("제목")
                       .writer("작성자")
                       .content("내용")
                       .build();
    // 모델 등록
    model.addAttribute("board", board);
    // 뷰 지정
    return "board/read";
  }


  /**
   * 게시글 등록
   * [GET]
   * 🔗 /board/create
   * @return
   */
  @GetMapping("/create")
  public String create() {
    log.info("[GET] - /board/create");
    // 뷰 지정
    return "board/create";
  }

  /**
   * 게시글 등록 처리
   * [POST]
   * 🔗 /board/create
   * 💌 <form> ➡ Board
   * ➡🔗 게시글 목록으로 리다이렉트
   */
  @PostMapping("/create")
  public String createPost(Board board) {
    log.info("[POST] - /board/create");
    log.info("board : {}", board);
    // 데이터 등록 요청
    int result = new Random().nextInt(2);     // 0 또는 1
    // 리다이렉트
    // 실패
    if( result == 0 ) 
      return "redirect:/board/create?error";  // ➡ 글 등록 페이지
    // 성공
    return "redirect:/board/list";            // ➡ 글 목록 페이지
  }
  

  /**
   * 게시글 수정
   * [GET]
   * 🔗 /board/update
   * 🎁 model : board
   * @param param
   * @return
   */
  @GetMapping("/update")
  public String update(@RequestParam("no") int no, Model model) {
    log.info("[GET] - /board/update");
    log.info("no : {}", no);
    // 데이터 조회 요청
    Board board = Board.builder()
                       .no(no)
                       .title("제목")
                       .writer("작성자")
                       .content("내용")
                       .build();
    // 모델 등록
    model.addAttribute("board", board);
    // 뷰 지정
    return "board/update";
  }
  
  /**
   * 게시글 수정 처리
   * [POST]
   * 🔗 /board/update
   * 💌 <form> ➡ Board
   * ➡🔗 게시글 목록으로 리다이렉트
   */
  @PostMapping("/update")
  public String updatePost(Board board) {
    log.info("[POST] - /board/update");
    log.info("board : {}", board);
    // 데이터 수정 요청
    int result = new Random().nextInt(2);     // 0 또는 1
    // 리다이렉트
    // 실패
    if( result == 0 ) 
      return "redirect:/board/update?no=" + board.getNo() + "&error";  // ➡ 글 수정 페이지
    // 성공
    return "redirect:/board/list";                                     // ➡ 글 목록 페이지
  }
  
  /**
   * 게시글 삭제 처리
   * [POST]
   * 🔗 /board/delete
   * 💌 <form> ➡ no
   * @param no
   * @return
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("no") int no) {
    log.info("[POST] - /board/delete");
    log.info("no : {}", no);
    // 데이터 삭제 요청
    int result = new Random().nextInt(2);
    // 리다이렉트
    if( result == 0 ) 
      return "redirect:/board/update?no=" + no + "&error";
    return "redirect:/board/list";
  }
  
  
  
} 
