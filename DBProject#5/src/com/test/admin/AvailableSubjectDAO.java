package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.AvailableSubjectDTO;
import oracle.jdbc.OracleTypes;

/**
 * 교사의 강의 가능 과목와 관련된 클래스이다.
 * 강의 가능 교사 조회 기능을 포함한다.
 * 
 * @author 정경화
 */
public class AvailableSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	
	/**
	 * 강의 가능 과목 DAO의 기본 생성자이다.
	 */
	public AvailableSubjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("CourseDAO.CourseDAO()");
			e.printStackTrace();
		}
		
		
}

	/**
	 * 개설 과목 번호를 입력하면 강의 가능한 교사 목록을 반환한다.
	 * @param subjectSeq 개설 과목 번호
	 * @return 강의 가능한 교사명 정보
	 */
	public ArrayList<AvailableSubjectDTO> availableTeacher(String subjectSeq) {
		
		try {
			
			ArrayList<AvailableSubjectDTO> list = new ArrayList<AvailableSubjectDTO>();
			
			String sql = "{ call procAvailableTeacherName(?, ?) }";

			CallableStatement cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			ResultSet rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				AvailableSubjectDTO dto = new AvailableSubjectDTO();
				
				dto.setTeacherName(rs.getString("teacherName"));
					
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AvailableSubjectDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}

	
}