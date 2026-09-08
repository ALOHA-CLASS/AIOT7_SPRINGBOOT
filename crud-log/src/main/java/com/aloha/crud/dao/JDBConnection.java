package com.aloha.crud.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;


@Slf4j       // 📜 로깅 설정
public class JDBConnection {
  
  private String url;
  private String username;
  private String password;

  protected Connection con;
  protected Statement stmt;
  protected PreparedStatement psmt;
  protected ResultSet rs;

  public JDBConnection() {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
      Properties prop = new Properties();
      if( input != null ) {
        prop.load(input);
        this.url = prop.getProperty("db.url");
        this.username = prop.getProperty("db.username");
        this.password = prop.getProperty("db.password");
      }
    } catch (Exception e) {
      // System.err.println("application.properties 로드 시 예외 발생!");
      log.error("application.properties 로드 시 예외 발생!");
      e.printStackTrace();
    }
  }



  /**
   * DB 연결
   * @return
   */
  public Connection getConnection() {
    Connection con = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);
      // System.out.println("DB 연결 성공!");
      log.info("DB 연결 성공! - {}", username);
    } catch (Exception e) {
      // System.err.println("DB 연결 실패!");
      log.error("DB 연결 실패!");
      e.printStackTrace();
    }
    return con;
  }

  /**
   * 자원 해제 
   * @param con
   * @param psmt
   * @param rs
   */
  protected void close(Connection con, PreparedStatement psmt, ResultSet rs) {
    try {
      if( rs != null ) rs.close();
      if( psmt != null ) psmt.close();
      if( con != null ) con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void close(Connection con, PreparedStatement psmt) {
    close(con, psmt, null);
  }
}
