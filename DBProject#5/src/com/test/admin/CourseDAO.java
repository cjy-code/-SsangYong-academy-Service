package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.CourseDTO;

/**
 * 과정과 관련한 기능을 담당하는 클래스이다.
 * 전체 과정 조회, 새 과정 등록, 과정 정보 수정, 과정 삭제 기능을 포함한다.
 * @author 이은규
 *
 */
public class CourseDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 과정DAO의 기본 생성자이다.
	 */
	public CourseDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("CourseDAO.CourseDAO() : " + e.toString());
		}
		
	}

	
	/**
	 * 전체 과정 정보를 배열로 반환하는 메소드이다.
	 * 과정의 정보에는 과정 번호, 과정 이름, 과정 기간(일)이 포함된다.
	 * @return 과정 정보 배열
	 */
	//전체 과정 목록 조회
	public ArrayList<CourseDTO> courseList() {
		
		try {

			String sql = "select * from tblCourse order by courseSeq asc";
			rs = stat.executeQuery(sql);
			
			ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
			while(rs.next()) {
				
				CourseDTO dto = new CourseDTO();
				
				dto.setCourseSeq(rs.getString("courseSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseTerm(rs.getString("courseTerm"));
				
				result.add(dto);
			}
			rs.close();
			
			return result;
			

		} catch (Exception e) {
			System.out.println("CourseDAO.courseList() : " + e.toString());
		}
		
		return null;
	}
	
	/**
	 * 새 과정을 등록하는 메소드이다.
	 * 과정 이름과 기간(일)이 담긴 과정 데이터 정보를 받아 과정을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param pdto 과정 데이터 정보
	 * @return 성공 여부
	 */
	//과정 등록
	public int addCourse(CourseDTO cdto) {
		
		String sql = "{ call procaddCourse(?, ?) }";
		
		try {
			cstat = conn.prepareCall(sql);
			cstat.setString(1, cdto.getCourseName());//과정명
			cstat.setString(2, cdto.getCourseTerm());//기간
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("ProcessDAO.addProcess() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 과정의 정보를 수정하는 메소드이다.
	 * 수정할 과정 번호와 수정할 과정 이름 또는 과정 기간(일)을 받아와 기존 과정의 정보를 수정한다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param processSeq 과정 번호
	 * @param courseName 과정 이름
	 * @param courseTerm 과정 기간(일)
	 * @return 성공 여부
	 */
	//과정 수정
	public int updateCourse(String courseSeq, String getCourseName, String getCourseTerm) {
		
		//원래 값
		String courseName = "";
		String courseTerm = "";
		
		try {

			//String sql = "select title, days from tblCourse where processSeq = " + courseSeq;
			String sql = "select courseName, courseTerm from tblCourse where courseSeq = " + courseSeq;
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				courseName = rs.getString("courseName");
				courseTerm = rs.getString("courseTerm");
			}
			rs.close();
			
			
			//과정명 수정
			if (!getCourseName.equals("")) {
				courseName = getCourseName;
			}
			
			//기간 수정
			if (!getCourseTerm.equals("")) {
				courseTerm = getCourseTerm;
			}
			
			
			sql = "{ call procupdateCourse(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, courseSeq);
			cstat.setString(2, courseName);
			cstat.setString(3, courseTerm);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CourseDAO.updateCourse() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 과정을 삭제하는 메소드이다.
	 * 삭제할 과정 번호를 받아 과정을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param processSeq 과정 번호
	 * @return 성공 여부
	 */
	//과정 삭제
	public int deleteCourse(String courseSeq) {
		
		try {

			String sql = "{ call procdeleteCourse(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, courseSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CourseDAO.deleteCourse() : " + e.toString());
		}
		return 0;
	}



	


	
	
}
