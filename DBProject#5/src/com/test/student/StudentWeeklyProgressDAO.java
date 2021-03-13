package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

/**
 * 학생의 세부 진도표의 데이터를 관리하는 DAO 클래스입니다.
 * @author User
 *
 */
public class StudentWeeklyProgressDAO {

	/**
	 * 학생의 세부 진도표 리스트를 받아오는 메소드입니다.
	 * @param studentSeq 로그인 할때 받아오는 학생의 기본키입니다.
	 * @return 세부 진도표의 리스트를 리턴합니다.
	 */
	public ArrayList<StudentWeeklyProgressDTO> weeklyProgressList(String studentSeq) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procWeeklyProgress(?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			String[] studentCourse = studentChargeCourseNumber(studentSeq);
			
			stat.setString(1, "7");
			stat.setString(2, studentCourse[0]);
			stat.setString(3, studentCourse[1]);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(4);
			
			ArrayList<StudentWeeklyProgressDTO> weeklyProgressList = new ArrayList<StudentWeeklyProgressDTO>();
			
			while (rs.next()) {
				
				StudentWeeklyProgressDTO weeklyProgressDto = new StudentWeeklyProgressDTO();
				
				weeklyProgressDto.setWeeklyProgressDate(rs.getString("weeklyProgressDate"));
				weeklyProgressDto.setSubjectProgress(rs.getString("subjectProgress"));
				weeklyProgressDto.setLectureContent(rs.getString("lectureContent"));
				
				weeklyProgressList.add(weeklyProgressDto);
				
			}
			
			return weeklyProgressList;
			

		} catch (Exception e) {
			System.out.println("StudentWeeklyProgressDAO.enclosing_method()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 학생이 수강하고 있는 과정의 번호를 받아오는 메소드입니다.
	 * @param studentSeq 학생이 수강하는 과정의 번호를 알아내기 위한 학생의 기본키입니다.
	 * @return 과정의 번호와 학생의 기본키를 배열형태로 리턴합니다.
	 */
	private String[] studentChargeCourseNumber(String studentSeq) {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procStudentChargerCourse(?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, studentSeq);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(2);
			
			String[] studentChargeCourseNumber = { "", "" };
			
			while (rs.next()) {
				
				studentChargeCourseNumber[0] = rs.getString("studentSeq");
				studentChargeCourseNumber[1] = rs.getString("openCourseSeq");
				
			}
			
			return studentChargeCourseNumber;

		} catch (Exception e) {
			System.out.println("StudentWeeklyProgressDAO.studentChargeCourseNumber()");
			e.printStackTrace();
		}
		
		return null;
	}

}
