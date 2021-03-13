package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

/**
 * 학생 정보를 받아오는 클래스입니다.
 * @author User
 *
 */
public class StudentDAO {

	/**
	 * 학생의 정보를 리스트로 정리하는 메소드입니다.
	 * @return 로그인에 학생의 정보를 리턴합니다.
	 */
	public ArrayList<StudentDTO> studentList() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procStudent(?) }";
			
			stat = conn.prepareCall(sql);
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			ArrayList<StudentDTO> studentList = new ArrayList<StudentDTO>();
			
			while (rs.next()) {
				
				StudentDTO studentDto = new StudentDTO();
				
				studentDto.setStudentSeq(rs.getString("studentSeq"));
				studentDto.setStudentName(rs.getString("studentName"));
				studentDto.setStudentTel(rs.getString("studentTel"));
				studentDto.setStudentRegister(rs.getString("studentRegister"));
				studentDto.setStudentSsn(rs.getString("studentSsn"));
				
				studentList.add(studentDto);
				
			}
			
			return studentList;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherlist()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 학생 번호를 입력하면 수강 신청 번호를 반환하는 메소드
	 * @param seq 학생 번호
	 */
	public static StudentDTO get(String seq) {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "select classSeq from tblClass where studentSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			ResultSet rs = pstat.executeQuery();
				
			StudentDTO dto = new StudentDTO();				
			
			if (rs.next()) {
				
				dto.setClassSeq(rs.getString(1));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("StudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	

}
