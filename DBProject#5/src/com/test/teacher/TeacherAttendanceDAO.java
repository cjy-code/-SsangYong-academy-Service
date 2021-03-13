package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

public class TeacherAttendanceDAO {
	
	
	private Connection conn;
	private Statement stat; //매개변수x
	private PreparedStatement pstat; //매개변수o
	private CallableStatement cstat; //프로시저로 가져올 때
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public TeacherAttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendanceDAO.enclosing_method()");
			e.printStackTrace();
		}
		
	}
	
	

}
