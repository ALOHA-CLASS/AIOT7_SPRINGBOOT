package com.aloha.crud.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;

import com.aloha.crud.domain.Orders;
import com.aloha.crud.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller // ⭐ 빈 등록
@RequiredArgsConstructor 
public class OrderController {

  // lombok 활용 ⭐ 의존성 자동 주입 - 생성자 주입
  private final OrderService orderService;


  // ⭐ 의존성 자동 주입 - 필드 주입
  // @Autowired 
  // private OrderService orderService;

  private final Scanner sc = new Scanner(System.in);
  private List<Orders> orderList = null;

  // 
  // public OrderController() {
  //   this.orderService = null;
  //   this.sc = new Scanner(System.in);
  //   this.orderList = null;
  // }

  // ⭐ 의존성 자동 주입
  // @Autowired  // ⭐ 생성자 주입 시, @Autowired 생략 가능
  // public OrderController(OrderService orderService) {
  //   this.orderService = orderService;
  //   this.sc = new Scanner(System.in);
  //   this.orderList = null;
  // }

  /**
   * 메뉴 실행
   */
  public void start() {
    int menuNo = 0;

    do {
      menu();
      menuNo = sc.nextInt();
      sc.nextLine();

      if( menuNo == 0 ) break;

      switch (menuNo) {
        case 1: list(); break;
        case 2: select(); break;
        case 3: insert(); break;
        case 4: update(); break;
        case 5: delete(); break;
        default:
          System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
      }
    } while (menuNo != 0);
    System.out.println("프로그램을 종료합니다...");
  }

  /**
   * 메뉴판
   */
  public void menu() {
    System.out.println(":::::::::: 주문 관리 시스템 ::::::::::");
    System.out.println("1. 주문 목록");
    System.out.println("2. 주문 조회");
    System.out.println("3. 주문 등록");
    System.out.println("4. 주문 수정");
    System.out.println("5. 주문 삭제");
    System.out.print(":::::::::: 번호 입력 : ");
  }

  /**
   * 주문 목록
   */
  public void list() {
    System.out.println(":::::::::: 주문 목록 ::::::::::");
    orderList = orderService.list();
    printAll(orderList);
  }

  /**
   * 주문 목록 전체 출력
   * @param list
   */
  public void printAll(List<Orders> list ) {
    if( list == null || list.isEmpty() ) {
      System.err.println("조회된 주문이 없습니다.");
      return;
    }
    for (Orders orders : list) {
      print(orders);
    }
  }

  public void print(Orders order) {
    if( order == null ) {
      System.err.println("조회할 수 없는 주문입니다.");
      return;
    }
    Integer no = order.getNo();
    String id = order.getId();
    String orderName = order.getOrderName();
    Integer totalAmount = order.getTotalAmount();
    LocalDateTime createdAt = order.getCreatedAt();
    LocalDateTime updatedAt = order.getUpdatedAt();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String reg = (createdAt != null) ? createdAt.format(dtf) : "-";
    String upd = (updatedAt != null) ? updatedAt.format(dtf) : "-";

    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
    System.out.println("* 주문번호 : " + no);
    System.out.println("* 주문ID : " + id);
    System.out.println("* 주문명 : " + orderName);
    System.out.println("* 총 금액 : " + totalAmount);
    System.out.println("----------------------------------------------------");
    System.out.println("* 등록일자 : " + reg);
    System.out.println("* 수정일자 : " + upd);
    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
    System.out.println();
  }

  /**
   * 주문 조회
   */
  public void select() {
    System.out.println(":::::::::: 주문 조회 ::::::::::");
    System.out.print("주문 번호 : ");
    int no = sc.nextInt();
    sc.nextLine();

    Orders order = orderService.select(no);
    print(order);
  }

  /**
   * 주문 등록
   */
  public void insert() {
    System.out.println(":::::::::: 주문 등록 ::::::::::");
    Orders order = input();
    Orders result = orderService.insert(order);
    if( result != null ) {
      System.out.println("주문 등록 성공! (생성된 주문 번호 : " + result.getNo() + ")");
    } else {
      System.err.println("주문 등록 실패!");
    }
  }

  /**
   * 주문 정보 입력
   * @return
   */
  public Orders input() {
    System.out.print("* 주문명 : ");
    String orderName = sc.nextLine();
    System.out.print("* 총 금액 : ");
    int totalAmount = sc.nextInt();
    sc.nextLine();

    Orders order = new Orders();
    order.setOrderName(orderName);
    order.setTotalAmount(totalAmount);
    return order;
  }

  /**
   * 주문 수정
   */
  public void update() {
    System.out.println(":::::::::: 주문 수정 ::::::::::");
    System.out.print("주문 번호 : ");    
    int no = sc.nextInt();
    sc.nextLine();

    Orders order = input();
    order.setNo(no);

    int result = orderService.update(order);
    if( result > 0 ) {
      System.out.println("주문 수정 성공!");
    } else {
      System.err.println("주문 수정 실패!");
    }
  }

  /**
   *  주문 삭제
   */
  public void delete() {
    System.out.println(":::::::::: 주문 삭제 ::::::::::");
    System.out.print("주문 번호 : ");
    int no = sc.nextInt();
    sc.nextLine();
    int result = orderService.delete(no);
    if( result > 0 ) {
      System.out.println("주문 삭제 성공!");
    } else {
      System.err.println("주문 삭제 실패!");
    }
  }

}
