package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

/**
 * 교사의 시험, 배점 데이터를 관리하는 DAO 입니다.
 * @author User
 *
 */
public class TeacherPointExamDAO {

	/**
	 * 교사가 담당하는 과정의 리스트를 받아오는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @return 교사가 담당하는 과정의 리스트를 리턴합니다.
	 */
	public ArrayList<TeacherChargeCourseDTO> chargeCourseList(String teacherNumber) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();

			String sql = "{ call procChargeCourse(?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherNumber);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(2);
			
			ArrayList<TeacherChargeCourseDTO> chargeCourseList = new ArrayList<TeacherChargeCourseDTO>();
			
			while (rs.next()) {
				
				TeacherChargeCourseDTO chargeCourseDto = new TeacherChargeCourseDTO();
				
				chargeCourseDto.setSeq(rs.getString("seq"));
				chargeCourseDto.setCourseName(rs.getString("courseName"));
				chargeCourseDto.setCourseBegin(rs.getString("courseBegin"));
				chargeCourseDto.setCourseEnd(rs.getString("courseEnd"));
				chargeCourseDto.setLectureRoomName(rs.getString("lectureRoomName"));
				chargeCourseDto.setCourseStudent(rs.getString("courseStudent"));
				chargeCourseDto.setCourseState(rs.getString("courseState"));
				
				chargeCourseList.add(chargeCourseDto);
				
			}
			
			return chargeCourseList;

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.enclosing_method()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사가 담당하는 과정의 과목 목록을 조회하는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @param num 과정의 번호를 받아오는 매개변수입니다.
	 * @return 과목의 리스트를 리턴합니다.
	 */
	public ArrayList<TeacherChargeCourseSubjectDTO> subjectList(String teacherNumber, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procChargeCourseSubject(?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String[] chargeCourseNumber = { "" , "" };
			
			chargeCourseNumber = chargeCourseNumber(teacherNumber, num);
			
			stat.setString(1, teacherNumber);
			stat.setString(2, chargeCourseNumber[0]);
			stat.setString(3, chargeCourseNumber[1]);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(4);
			
			ArrayList<TeacherChargeCourseSubjectDTO> subjectList = new ArrayList<TeacherChargeCourseSubjectDTO>();
			
			while (rs.next()) {
				
				TeacherChargeCourseSubjectDTO subjectDto = new TeacherChargeCourseSubjectDTO();
				
				subjectDto.setSeq(rs.getString("seq"));
				subjectDto.setCourseName(rs.getString("courseName"));
				subjectDto.setSubjectName(rs.getString("subjectName"));
				subjectDto.setOpenSubjectBegin(rs.getString("openSubjectBegin"));
				subjectDto.setOpenSubjectEnd(rs.getString("openSubjectEnd"));
				subjectDto.setCourseStudent(rs.getString("courseStudent"));
				subjectDto.setCourseState(rs.getString("courseState"));
				
				subjectList.add(subjectDto);
				
			}
			
			return subjectList;

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.subjectList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사가 담당하는 과정의 번호를 받아오는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @param num 교사가 담당하는 과정을 받아오기 위한 매개변수입니다.
	 * @return 교사가 담당하는 과정의 번호와 시작일을 배열형태로 리턴합니다.
	 */
	private String[] chargeCourseNumber(String teacherNumber, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procChargeCourseNumber(?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherNumber);
			stat.setString(2, num);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(3);
			
			String[] chargeCourseNumber = { "" , "" };
			
			while (rs.next()) {
				
				chargeCourseNumber[0] = rs.getString("courseSeq");
				chargeCourseNumber[1] = rs.getString("courseBegin");
				
			}
			
			return chargeCourseNumber;

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.chargeCourseNumber()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사가 담당하는 과정의 과목의 배점을 리스트로 받아오는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @param courseNumber 교사가 담당하는 과정키를 받아오는 매개변수입니다.
	 * @param num 배점의 키를 받아오는 매개변수입니다.
	 * @return 배점리스트를 리턴합니다.
	 */
	public ArrayList<TeacherPointDTO> pointList(String teacherNumber, String courseNumber, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procPoint(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String[] chargeCourseNumber = { "" , "" };
			
			chargeCourseNumber = chargeCourseNumber(teacherNumber, courseNumber);
			
			stat.setString(1, teacherNumber);
			stat.setString(2, chargeCourseNumber[0]);
			stat.setString(3, chargeCourseNumber[1]);
			stat.setString(4, num);
			stat.registerOutParameter(5, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(5);
			
			ArrayList<TeacherPointDTO> pointList = new ArrayList<TeacherPointDTO>();
			
			while (rs.next()) {
				
				TeacherPointDTO pointDto = new TeacherPointDTO();
				
				pointDto.setCourseName(rs.getString("courseName"));
				pointDto.setSubjectName(rs.getString("subjectName"));
				pointDto.setPointAttendance(rs.getString("pointAttendance"));
				pointDto.setPointHandWriting(rs.getString("pointHandWriting"));
				pointDto.setPointPractical(rs.getString("pointPractical"));
				pointDto.setPointSeq(rs.getString("pointSeq"));
				pointDto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				
				pointList.add(pointDto);
				
			}
			
			return pointList;

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.pointList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 배점 목록을 수정, 삭제하는 메소드입니다.
	 * @param pointNumber 배점의 기본키를 받아오는 매개변수입니다.
	 * @param pointSubjectNumber 배점의 과목키를 받아오는 매개변수입니다.
	 * @param pointUpdateDto 배점의 업데이트 목록을 받아오는 DTO입니다.
	 * @param number 수정인지 삭제인지 결정하는 매개변수입니다.
	 * @return 배점을 성공적으로 수정했는지 INT 형태로 리턴합니다.
	 */
	public int pointUpdateDelete(String pointNumber, String pointSubjectNumber, TeacherPointDTO pointUpdateDto, String number) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			
			if (number.equals("1")) {
			
				String sql = "{ call procPointUpdate(?, ?, ?, ?, ?) }";
				
				stat = conn.prepareCall(sql);
				
				stat.setString(1, pointUpdateDto.getPointAttendance());
				stat.setString(2, pointUpdateDto.getPointHandWriting());
				stat.setString(3, pointUpdateDto.getPointPractical());
				stat.setString(4, pointNumber);
				stat.setString(5, pointSubjectNumber);
			
			} else if (number.equals("2")) {
				
				String sql = "{ call procPointDelete(?, ?, ?, ?, ?) }";
				
				stat = conn.prepareCall(sql);
				
				stat.setString(1, null);
				stat.setString(2, null);
				stat.setString(3, null);
				stat.setString(4, pointNumber);
				stat.setString(5, pointSubjectNumber);
				
			}
			
			return stat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.pointUpdateDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교사가 담당하는 시험의 정보를 리스트로 받아오는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @param courseNumber 교사가 담당하는 과정키를 받아오는 매개변수입니다.
	 * @param num 시험의 키를 받아오는 매개변수입니다.
	 * @return 시험리스트를 리턴합니다.
	 */
	public ArrayList<TeacherExamDTO> examList(String teacherNumber, String courseNumber, String num) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procExam(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String[] chargeCourseNumber = { "" , "" };
			
			chargeCourseNumber = chargeCourseNumber(teacherNumber, courseNumber);
			
			stat.setString(1, teacherNumber);
			stat.setString(2, chargeCourseNumber[0]);
			stat.setString(3, num);
			stat.setString(4, chargeCourseNumber[1]);
			stat.registerOutParameter(5, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(5);
			
			ArrayList<TeacherExamDTO> examList = new ArrayList<TeacherExamDTO>();
			
			while (rs.next()) {
				
				TeacherExamDTO examDto = new TeacherExamDTO();
				
				examDto.setSeq(rs.getString("seq"));
				examDto.setExamDate(rs.getString("examDate"));
				examDto.setExamPaperDate(rs.getString("examPaperDate"));
				examDto.setQuestion(rs.getString("question"));
				examDto.setAnswer(rs.getString("answer"));
				
				examList.add(examDto);
				
			}
			
			return examList;

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.examList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사가 담당하는 과정의 시험의 번호를 리스트로 받아오는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 받아오는 매개변수입니다.
	 * @param courseNumber 교사가 담당하는 과정의 키를 받아오는 매개변수입니다.
	 * @param subjectNumber 교사가 담당하는 과정의 과목 키를 받아오는 매개변수입니다.
	 * @param questionNumber 교사가 담당하는 과정의 과목의 시험 문제 키를 받아오는 매개변수입니다.
	 * @return 교사가 담당하는 과정의 시험의 번호를 리스트로 리턴합니다.
	 */
	public ArrayList<TeacherExamDTO> examNumberList(String teacherNumber, String courseNumber, String subjectNumber, String questionNumber) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procExamNumber(?, ?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String[] chargeCourseNumber = { "" , "" };
			
			chargeCourseNumber = chargeCourseNumber(teacherNumber, courseNumber);
			
			stat.setString(1, teacherNumber);
			stat.setString(2, chargeCourseNumber[0]);
			stat.setString(3, subjectNumber);
			stat.setString(4, chargeCourseNumber[1]);
			stat.setString(5, questionNumber);
			stat.registerOutParameter(6, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(6);
			
			ArrayList<TeacherExamDTO> examNumberList = new ArrayList<TeacherExamDTO>();
			
			while (rs.next()) {
				
				TeacherExamDTO examNumberDto = new TeacherExamDTO();
				
				examNumberDto.setSeq(rs.getString("seq"));
				examNumberDto.setCourseName(rs.getString("courseName"));
				examNumberDto.setSubjectName(rs.getString("subjectName"));
				examNumberDto.setQuestion(rs.getString("question"));
				examNumberDto.setAnswer(rs.getString("answer"));
				examNumberDto.setExamQuestionSeq(rs.getString("examQuestionSeq"));
				examNumberDto.setExamPaperSeq(rs.getNString("examPaperSeq"));
				
				examNumberList.add(examNumberDto);
				
			}
			
			return examNumberList;
			
			
		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.examNumberList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교사가 담당하는 과정의 시험을 수정하는 메소드입니다.
	 * @param examQuestionSeq 시험의 문제 번호를 받아오는 매개변수입니다.
	 * @param examPaperSeq 시험의 문제지 번호를 받아오는 매개변수입니다.
	 * @param examUpdateDto 시험의 문제와 답을 가져오는 DTO입니다.
	 * @param number 
	 * @return 시험이 성공적으로 수정이 됐는지 INT 형태로 리턴합니다.
	 */
	public int examUpdate(String examQuestionSeq, String examPaperSeq, TeacherExamDTO examUpdateDto, String number) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();

			String sql = "{ call procExamUpdate(?, ?, ?, ?) }";
			
			if (number.equals("1")) {
			
				stat = conn.prepareCall(sql);
				
				stat.setString(1, examUpdateDto.getQuestion());
				stat.setString(2, examUpdateDto.getAnswer());
				stat.setString(3, examQuestionSeq);
				stat.setString(4, examPaperSeq);
			
			} else if (number.equals("2")) {
				
				stat = conn.prepareCall(sql);
				
				stat.setString(1, null);
				stat.setString(2, null);
				stat.setString(3, examQuestionSeq);
				stat.setString(4, examPaperSeq);
				
			}
			
			
			return stat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherPointExamDAO.pointUpdate()");
			e.printStackTrace();
		}
		
		return 0;
	}

}























