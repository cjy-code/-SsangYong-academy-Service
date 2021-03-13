package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/**
 * 과정, 과목, 교육생의 데이터를 관리하는 DAO입니다.
 * @author User
 *
 */
public class TeacherLectureScheduleDAO {

	/**
	 * 과정 목록을 조회하는 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 * @return 과정 목록을 리스트 형태로 return 합니다.
	 */
	public ArrayList<TeacherCourseDTO> courseList(String state) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procLectureSchedule(?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, state);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(2);
			
			ArrayList<TeacherCourseDTO> courseList = new ArrayList<TeacherCourseDTO>();
			
			while (rs.next()) {
				
				TeacherCourseDTO courseDto = new TeacherCourseDTO();
				
				courseDto.setSeq(rs.getString("seq"));
				courseDto.setCourseName(rs.getString("courseName"));
				courseDto.setCourseBegin(rs.getString("courseBegin"));
				courseDto.setCourseEnd(rs.getString("courseEnd"));
				courseDto.setLectureRoomName(rs.getString("lectureRoomName"));
				courseDto.setcourseStudent(rs.getString("courseStudent"));
				courseDto.setCourseState(rs.getString("courseState"));
				
				courseList.add(courseDto);
				
			}
			
			return courseList;
			

		} catch (Exception e) {
			System.out.println("TeacherLectureScheduleDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과정의 목록을 rownum으로 조회함에 따라 courseSeq 값을 따로 알기 위해서 만든 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 * @param num 과정 목록의 courseSeq를 넘겨주는 매개변수입니다.
	 * @return courseSeq로 받아온 과정 번호의 과정을 과목 목록으로 String 형태로 return합니다.
	 */
	private String courseNumber(String state, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procCourseNumber(?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, state);
			stat.setString(2, num);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(3);
			ArrayList<TeacherCourseNumberDTO> numberlist = new ArrayList<TeacherCourseNumberDTO>();
			
			String courseNumber = null;
			
			while (rs.next()) {
				
				TeacherCourseNumberDTO numberDto = new TeacherCourseNumberDTO();
				
				numberDto.setSeq("seq");
				numberDto.setCourseSeq("courseSeq");
				
				courseNumber = rs.getString("courseSeq");
				
				numberlist.add(numberDto);
				
			}
				
				return courseNumber;
	

		} catch (Exception e) {
			System.out.println("TeacherLectureScheduleDAO.subjectNumber()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과목 목록을 조회하는 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 * @param num 과정 목록의 courseSeq를 넘겨주는 매개변수입니다.
	 * @return courseSeq로 받아온 과정 번호의 과정을 과목 목록으로 list 형태로 return합니다.
	 */
	public ArrayList<TeacherCourseSubjectDTO> subjectList(String state, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procCourseSubject(?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String courseNumber = courseNumber(state, num);
			
			stat.setString(1, state);
			stat.setString(2, courseNumber);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(3);
			
			ArrayList<TeacherCourseSubjectDTO> subjectList = new ArrayList<TeacherCourseSubjectDTO>();
			
			while (rs.next()) {
				
				TeacherCourseSubjectDTO subjectDto = new TeacherCourseSubjectDTO();
				
				subjectDto.setSeq(rs.getString("seq"));
				subjectDto.setCourseName(rs.getString("courseName"));
				subjectDto.setSubjectName(rs.getString("subjectName"));
				subjectDto.setOpenSubjectBegin(rs.getString("openSubjectBegin"));
				subjectDto.setOpenSubjectEnd(rs.getString("openSubjectEnd"));
				subjectDto.setCourseStudent(rs.getString("courseStudent"));
				subjectDto.setBookTitle(rs.getString("bookTitle"));
				
				subjectList.add(subjectDto);
				
			}
			
			return subjectList;

		} catch (Exception e) {
			System.out.println("TeacherLectureScheduleDAO.subjectList()");
			e.printStackTrace();
		}
		
		return null;
		
	}

	/**
	 * 교육생 목록을 조회하는 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 * @param num 과정 목록의 courseSeq를 넘겨주는 매개변수입니다.
	 * @return courseSeq로 받아온 과정 번호의 과정을 교육생 목록으로 list 형태로 return합니다.
	 */
	public ArrayList<TeacherCourseStudentDTO> studentList(String state, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procCourseStudent(?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String courseNumber = courseNumber(state, num);
			
			stat.setString(1, state);
			stat.setString(2, courseNumber);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(3);
			
			ArrayList<TeacherCourseStudentDTO> studentList = new ArrayList<TeacherCourseStudentDTO>();
			
			while (rs.next()) {
				
				TeacherCourseStudentDTO studentDto = new TeacherCourseStudentDTO();
				
				studentDto.setSeq(rs.getString("seq"));
				studentDto.setStudentName(rs.getString("studentName"));
				studentDto.setStudentTel(rs.getString("studentTel"));
				studentDto.setStudentRegister(rs.getString("studentRegister"));
				studentDto.setCompletionCheck(rs.getString("CompletionCheck"));		
				
				studentList.add(studentDto);
				
			}
			
			return studentList;
			
		} catch (Exception e) {
			System.out.println("TeacherLectureScheduleDAO.studentList()");
			e.printStackTrace();
		}
		
		return null;
	}

}























