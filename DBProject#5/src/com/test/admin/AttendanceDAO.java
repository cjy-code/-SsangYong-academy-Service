package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 * 
 * @author 최진영
 * 관리자의 출결 조회 기능 DAO
 */
public class AttendanceDAO {
	
	private  Connection conn;
	private  PreparedStatement pstat;
	private  CallableStatement cstat; 
	private  Statement stat;			
	private  ResultSet rs;	
	private  Scanner sc = new Scanner(System.in);

public AttendanceDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


/**
 * 출결검색하기 위한 과정 리스트
 * @return ArrayList<AttendanceDTO>
 */
public ArrayList<AttendanceDTO> AttendaceList() {

	
	try {
		
		
		 String sql = String.format("select * from vwOpenCourseExam order by OpenCourseSeq asc"); 
		
		 rs = stat.executeQuery(sql);
		 
		 ArrayList<AttendanceDTO> list = new ArrayList<AttendanceDTO>();
		 
		 while(rs.next()) {
			 
			 AttendanceDTO dto = new AttendanceDTO();
			 
			 dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
			 dto.setCourseName(rs.getString("courseName"));
			 dto.setOpenCourseBegin(rs.getString("openCourseBegin"));
			 dto.setOpencourseEnd(rs.getString("opencourseEnd"));
			 dto.setLectureroomName(rs.getString("lectureroomName"));
			 dto.setLectureroomNumber(rs.getString("lectureroomNumber"));
			 list.add(dto);
			 
		 }
		return list;
		
		
		
		
	} catch (Exception e) {
		System.out.println("AttendanceDAO.AttendaceList()");
		e.printStackTrace();
	}

	return null;
}





/**
 * 특정과정 출결 조회
 * @param 특정과정번호 
 * @return ArrayList<AttendanceDTO>
 */
public ArrayList<AttendanceDTO> courseAttendace(String seq) {
	
	try {
		String sql = "call procOpenCourseAttendance(?, ?)";
		                
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, seq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
        
		cstat.executeQuery();
		rs = (ResultSet) cstat.getObject(2);
		
		ArrayList<AttendanceDTO> list = new ArrayList<AttendanceDTO>();
		
		while(rs.next()) {
			AttendanceDTO dto = new AttendanceDTO();
			
			dto.setCourseName(rs.getString("courseName"));
			dto.setStudentSeq(rs.getString("studentSeq"));
			dto.setStudentName(rs.getString("studentName"));
			dto.setAttendanceState(rs.getString("attendanceState"));
			dto.setAttendanceenterdate(rs.getString("attendanceenterdate"));
			dto.setAttendanceExitDate(rs.getString("attendanceExitDate"));
			
			list.add(dto);
		}
			
//			rs.close();
//			cstat.close();
//			conn.close();
			
			return list;
			
	} catch (Exception e) {
		System.out.println("AttendanceDAO.subjectList()");
		e.printStackTrace();
	}
	
	
	
	return null;
}






/**
 * 특정과정의 특정 기간 출석정보 조회 DAO
 * @param seq 특정 과정 번호
 * @param start 시작 기준 날짜
 * @param end   종료 기준 날짜
 * @return ArrayList<AttendanceDTO>
 */
public ArrayList<AttendanceDTO> courseAttendace(String seq, String start, String end) {
	
	
	try {
		
		String sql = "call procAttendSearch(?, ?, ? ,?)";
		                
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, seq);
		cstat.setString(2, start);
		cstat.setString(3, end);
		cstat.registerOutParameter(4, OracleTypes.CURSOR);
        
		cstat.executeQuery();
		rs = (ResultSet) cstat.getObject(4);
		
		ArrayList<AttendanceDTO> list = new ArrayList<AttendanceDTO>();
		
		while(rs.next()) {
			AttendanceDTO dto = new AttendanceDTO();
			
			dto.setCourseName(rs.getString("courseName"));
			dto.setStudentSeq(rs.getString("studentSeq"));
			dto.setStudentName(rs.getString("studentName"));
			dto.setAttendanceState(rs.getString("attendanceState"));
			dto.setAttendanceenterdate(rs.getString("attendanceenterdate"));
			dto.setAttendanceExitDate(rs.getString("attendanceExitDate"));
			
			list.add(dto);
		}
			
//			rs.close();
//			cstat.close();
//			conn.close();
			
			return list;
			
	} catch (Exception e) {
		System.out.println("AttendanceDAO.subjectList()");
		e.printStackTrace();
	}
	
	return null;
}














}
