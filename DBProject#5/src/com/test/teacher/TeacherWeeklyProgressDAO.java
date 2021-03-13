package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;
//import com.test.mvc.AddressDTO;

import oracle.jdbc.OracleTypes;

public class TeacherWeeklyProgressDAO {

	private Connection conn;
	private Statement stat; //매개변수x
	private PreparedStatement pstat; //매개변수o
	private CallableStatement cstat; //프로시저로 가져올 때
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public TeacherWeeklyProgressDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgressDAO.TeacherWeeklyProgressDAO()");
			e.printStackTrace();
		}
		
	}
	
	public int add(TeacherWeeklyProgressDTO dto) {

		
		try {
			
			String sql = "{ call procAddWp(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getWeeklyProgressDate());
			cstat.setString(4, dto.getLectureContent());
			cstat.setString(5, dto.getSubjectProgress());			
			cstat.executeQuery();


//			return cstat.executeUpdate(); //성공하면 1반환. 찾았다 프로시저에서 이미 insert했는데 얘때매 한번 더 추가 됨
			
//			rs.close();
//			cstat.close();
//			conn.close();
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgressDAO.add()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	
	public ArrayList<TeacherWeeklyProgressDTO> list(String word) {
		
		try {
			
			ArrayList<TeacherWeeklyProgressDTO> list = new ArrayList<TeacherWeeklyProgressDTO>();
			
			while (rs.next()) {
				
				
				TeacherWeeklyProgressDTO dto = new TeacherWeeklyProgressDTO();
//				[개설과정번호]\t[개설과목번호]\t[수업날짜]\t[수업내용]\t[과목별진행률]
				
				dto.setTeacherSeq(rs.getString("teacherNum"));
				dto.setOpenSubjectSeq(rs.getString("osNum"));
				dto.setWeeklyProgressDate(rs.getString("date"));
				dto.setLectureContent(rs.getString("contents"));
				dto.setSubjectProgress(rs.getString("progress"));
				
				list.add(dto);
				
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgressDAO.list()");
			e.printStackTrace();
		}
		
		
		
		return null;
			
	}

	public int edit(TeacherWeeklyProgressDTO dto) {
		
		try {
			
			String sql = "{ call procUpdateWp(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getWeeklyProgressSeq());
			cstat.setString(3, dto.getLectureContent());		
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgressDAO.edit()");
			e.printStackTrace();
		}
		
		
		
		
		return 0;
	}

	public int delete(TeacherWeeklyProgressDTO dto) {
		
		try {
			
			String sql = "{ call procDeleteWp(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getWeeklyProgressSeq());	
			cstat.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgressDAO.delete()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	

//	public TeacherWeeklyProgressDTO get(String wpNum) {
//		
//		try {
//			
//			String sql = "select * from tblWeeklyProgress where weeklyProgressSeq = ?";
//			
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, wpNum);
//			
//			rs = pstat.executeQuery();
//			
//			if (rs.next()) {
//				
//				TeacherWeeklyProgressDTO dto = new TeacherWeeklyProgressDTO();
//				
////				dto.setTeacherSeq(rs.getString("teacherNum"));
//				dto.setOpenSubjectSeq(rs.getString("osNum"));
//				dto.setWeeklyProgressDate(rs.getString("date"));
//				dto.setLectureContent(rs.getString("contents"));
//				dto.setSubjectProgress(rs.getString("progress"));
//				
//				return dto;
//			}
//			
//		} catch (Exception e) {
//			System.out.println("TeacherWeeklyProgressDAO.get()");
//			e.printStackTrace();
//		}
//		
//		
//		return null;
//	}
	
	
	
	
	

//	public int edit(TeacherWeeklyProgressDTO dto3) {
//		
//		try {
//			
//			String sql = "update tblWeeklyProgress set openSubjectSeq=? ,weeklyProgressDate=?, lectureContent=?, subjectProgress=? where weeklyProgressSeq=?";
//			
//			pstat = conn.prepareStatement(sql);
//			
//			pstat.setString(1, dto3.getOpenSubjectSeq());
//			pstat.setString(2, dto3.getWeeklyProgressDate());
//			pstat.setString(3, dto3.getLectureContent());
//			pstat.setString(4, dto3.getSubjectProgress());
//			pstat.setString(5, dto3.getWeeklyProgressSeq());
//			
//			return pstat.executeUpdate();
//			
//		} catch (Exception e) {
//			System.out.println("TeacherWeeklyProgressDAO.edit()");
//			e.printStackTrace();
//		}
//		
//		
//		
//		return 0;
//	}
	
		
	
	
	
	
	
	
}
