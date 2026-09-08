package com.aloha.crud.dao;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aloha.crud.domain.Orders;

/**
 * 
 * 주문 DAO
 * - 주문 목록
 * - 주문 조회
 * - 주문 등록
 * - 주문 수정
 * - 주문 삭제
 */


public class OrderRepository extends JDBConnection {

  /**
   * 주문 목록
   */
  public List<Orders> list() {
    List<Orders> list = new ArrayList<>();
    String sql = " SELECT * FROM orders ORDER BY no DESC ";

    try {
      con = getConnection();
      psmt = con.prepareStatement(sql);
      rs = psmt.executeQuery();

      while(rs.next()) {
        Orders orders = new Orders();
        orders.setNo(rs.getInt("no"));
        orders.setId(rs.getString("id"));
        orders.setOrderName(rs.getString("order_name"));
        orders.setTotalAmount(rs.getInt("total_amount"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        if( createdAt != null ) {
          orders.setCreatedAt(createdAt.toLocalDateTime());
        }
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if( updatedAt != null ) {
          orders.setUpdatedAt(updatedAt.toLocalDateTime());
        }
        list.add(orders);
      }
    } catch (Exception e) {
      System.err.println("주문 목록 조회 중 예외 발생!");
      e.printStackTrace();
    } finally {
      close(con, psmt, rs);
    }
    return list;
  }


  /**
   * 주문 조회
   */
  public Orders select(Integer no) {
    String sql = " SELECT * FROM orders WHERE no = ? ";

    try {
      con = getConnection();
      psmt = con.prepareStatement(sql);
      psmt.setInt(1, no);
      rs = psmt.executeQuery();

      if(rs.next()) {
        Orders orders = new Orders();
        orders.setNo(rs.getInt("no"));
        orders.setId(rs.getString("id"));
        orders.setOrderName(rs.getString("order_name"));
        orders.setTotalAmount(rs.getInt("total_amount"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        if( createdAt != null ) {
          orders.setCreatedAt(createdAt.toLocalDateTime());
        }
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if( updatedAt != null ) {
          orders.setUpdatedAt(updatedAt.toLocalDateTime());
        }
        return orders;
      }
    } catch (Exception e) {
      System.err.println("주문 단건 조회 중 예외 발생!");
      e.printStackTrace();
    } finally {
      close(con, psmt, rs);
    }
    return null;
  }

  /**
   * 주문 등록
   * @param orders
   * @return
   */
  public Orders insert(Orders orders) {
    String sql = " INSERT INTO orders (id, order_name, total_amount) "
               + " VALUES( ?, ?, ? )";

    try {
      con = getConnection();
      psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      psmt.setString(1, orders.getId());
      psmt.setString(2, orders.getOrderName());
      psmt.setInt(3, orders.getTotalAmount());
      psmt.executeUpdate();

      // AUTO_INCREMENT 로 생성된 no(PK) 가져오기
      rs = psmt.getGeneratedKeys();
      if( rs.next() ) {
        orders.setNo(rs.getInt(1));
      }
      return orders;
    } catch (Exception e) {
      System.err.println("주문 등록 중 예외 발생!");
      e.printStackTrace();
    } finally {
      close(con, psmt, rs);
    }
    return null;
  }

  /**
   * 주문 수정
   * @param orders
   * @return
   */
  public int update(Orders orders) {
    String sql = " UPDATE orders "
               + " SET order_name = ? "
               + "    ,total_amount = ? "
               + " WHERE no = ? ";
    int result = 0;

    try {
      con = getConnection();
      psmt = con.prepareStatement(sql);
      psmt.setString(1, orders.getOrderName());
      psmt.setInt(2, orders.getTotalAmount());
      psmt.setInt(3, orders.getNo());
      result = psmt.executeUpdate();
    } catch (Exception e) {
      System.err.println("주문 수정 중 예외 발생!");
      e.printStackTrace();
    } finally {
      close(con, psmt);
    }
    return result;
  }

  /**
   * 주문 삭제
   * @param no
   * @return
   */
  public int delete(Integer no) {
    String sql = " DELETE FROM orders WHERE no = ? ";
    int result = 0;

    try {
      con = getConnection();
      psmt = con.prepareStatement(sql);
      psmt.setInt(1, no);
      result = psmt.executeUpdate();
    } catch (Exception e) {
      System.err.println("주문 삭제 중 예외 발생!");
      e.printStackTrace();
    } finally {
      close(con, psmt);
    }
    return result;
  }
}
