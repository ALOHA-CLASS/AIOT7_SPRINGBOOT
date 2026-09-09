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
      log.info("DB 연결 성공! - {}", username);
    } catch (Exception e) {
      // ⭐ 예외 로깅/처리는 AOP(LoggingAspect, ExceptionAspect)가 전담하므로 여기선 변환만 한다.
      throw new RuntimeException("DB 연결 실패", e);
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
