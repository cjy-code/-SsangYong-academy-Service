/*
 * package jcbc;
 * 
 * import java.sql.Connection;
 * 
 * public class DButil {
 * 
 * private static Connection conn;
 * 
 * public static Connection open() {
 * 
 * String url = "jdbc:oracle:thin:@localhost:1521:xe"; String id = "hr"; String
 * pw = "123456";
 * 
 * try {
 * 
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * 
 * return conn;
 * 
 * } catch (Exception e) { // TODO: handle exception
 * System.out.println("DButil.open()"); } return conn; }
 * 
 * 
 * }
 */

package com.test.teacher;

import java.sql.Connection;
import java.sql.DriverManager;

//자바의 주석
//1. 단일 라인 주석

/*
 2. 다중 라인 주석
*/

/**
 3. 자바 도큐먼트 주석
*/

// javaDoc.exe - 도큐먼트 생성 도구 

/**
 * DButill. 데이터베이스 연결 클래스 입니다.
 * @author 최진영
 *
 */
public class DBUtil {

   private static Connection conn;
   
   /**
    * 데이터 베이스 연결 메소드입니다.
    * @return 연결된 Connection 객체
    */
   public static Connection open() {
	  //java.sql.SQLException: 부적합한 Oracle URL이 지정되었습니다
	  //java.sql.SQLException: Listener refused the connection with the following error:
	  //ORA-12505, TNS:listener does not currently know of SID given in connect descriptor
      String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
      String id = "hr";
      String pw = "123456";
      
      try {

         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         conn = DriverManager.getConnection(url, id, pw);
         //conn.setAutoCommit(false);-- 이거 안쓰는 건가?
         
         return conn;

      } catch (Exception e) {
         System.out.println("DBUtil.open()");
         e.printStackTrace();
      }
      
      return null;
      
   }
   
   /**
    * 데이터 베이스 연결 메소드입니다.
    * @param server 접속할 서버 주소입니다.
    * @param id 접속할 계정명입니다.
    * @param pw 접속할 비밀번호입니다.
    * @return 연결된 Connection 객체
    */
   public static Connection open(String server, String id, String pw) {
	      
	      String url = "jdbc:oracle:thin:@" +server+":1521:xe"; 
	      
	      
	      try {

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection(url, id, pw);
	         
	         return conn;

	      } catch (Exception e) {
	         System.out.println("DBUtil.open()");
	         e.printStackTrace();
	      }
	      
	      return null;
	      
	   }
   
   
   
   
   
   
   
}









