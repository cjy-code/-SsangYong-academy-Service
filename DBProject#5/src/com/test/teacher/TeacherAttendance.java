package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교사가 교육생의 출결을 조회하는 메인입니다.
 * @author User
 *
 */
public class TeacherAttendance {

	private static Scanner scan;
	private static TeacherAttendanceDAO dao;

	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherAttendanceDAO();
	}
	
	
	public static void main(String[] args) {
		teacherAttendance("1");//삭제
	}
	
	
	/**
	 * 교사가 교육생의 출결을 조회하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	public static void teacherAttendance(String teacherNum) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			
			String sql = "SELECT oc.teacherSeq AS 교사번호, oc.openCourseSeq AS 개설과정번호, c.courseName AS 과정명, c.courseTerm AS 과정기간, lr.lectureRoomName AS 강의실, lr.lectureRoomNumber AS 학생수, ls.courseState AS 과정진행상태 FROM tblOpenCourse oc INNER JOIN tblCourse c ON oc.courseSeq = c.courseSeq INNER JOIN tblLectureRoom lr ON oc.lectureRoomSeq = lr.lectureRoomSeq INNER JOIN tblLectureSchedule ls ON oc.openCourseSeq = ls.openCourseSeq INNER JOIN tblTeacher t ON oc.teacherSeq = t.teacherSeq";
			
			rs = stat.executeQuery(sql);
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                                 출결조회");
			System.out.println("                          전체 개설과정 목록 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[교사번호]\t[개설과정번호]\t[과정명]\t\t\t\t\t\t\t[과정기간]\t[강의실]\t[학생수]\t[과정진행상태]");
			
			while (rs.next()) {

				System.out.printf("%8s\t%8s\t%-40s\t%8s\t%-8s\t%8s\t%-8s\n"
						, rs.getString("교사번호")
						, rs.getString("개설과정번호")
						, rs.getString("과정명")
						, rs.getString("과정기간")
						, rs.getString("강의실")
						, rs.getString("학생수")
						, rs.getString("과정진행상태"));
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendance.teacherAttendance()");
			e.printStackTrace();
		}
		
		
		System.out.println();
		
		
		
		
		
		boolean loop = true;
		
		while (loop) {
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.print("1.담당 개설 과정 조회\n");
			System.out.print("0. 뒤로가기\n");
			System.out.println();
			System.out.print("선택: ");
			String num = scan.nextLine();
			System.out.println();
			
			if (num.equals("1")) {
				tCourseCheck(teacherNum);
				loop = false;
			} else if (num.equals("0")) {
				TeacherMain.TeacherMain(teacherNum);
			} else {
				System.out.println("잘 못 입력하셨습니다.");
			}
		}
	
	System.out.println();
		
	
	
		
	}

	public static void tCourseCheck(String teacherNum) {
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procCourseCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("                          담당 개설 과정 목록 조회");
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("[개설과정번호]\t[과정명]\t\t\t\t\t[과정시작일시]\t\t[과정종료일시]\t\t[강의실]\t[학생수]\t[과정진행상태]");
				
				cstat.setString(1, teacherNum); 
				cstat.registerOutParameter(2, OracleTypes.CURSOR);
				
				cstat.executeQuery();
				ResultSet rs = (ResultSet)cstat.getObject(2);

				
				while (rs.next()) {
					System.out.printf("%13s\t%-30s\t%12s\t\t%12s\t%10s\t%10s\t%10s\t\n",rs.getString("개설과정번호"),rs.getString("과정명"),rs.getString("과정시작일시"),rs.getString("과정종료일시"),rs.getString("강의실"),rs.getString("학생수"),rs.getString("과정진행상태"));
					
				}
			
				boolean loop = true;
				
				while (loop) {
					System.out.print("------------------------------------------------------------------------------\n");
					System.out.print("1.담당 개설 과정 교육생 출결 조회\n");
					System.out.print("0. 뒤로가기\n");
					System.out.println();
					System.out.print("번호 선택: ");
					String num = scan.nextLine();
					System.out.println();
					
					if (num.equals("1")) {
						System.out.println();
						System.out.print("개설 과정 번호: ");
						String openCourseSeq = scan.nextLine();
						teacherAllAtdCheck(teacherNum, openCourseSeq);
						loop = false;
					} else if (num.equals("0")){
						TeacherMain.TeacherMain(teacherNum);
					} else {
						System.out.println("잘 못 입력하셨습니다.");
					}
				}
			
			System.out.println();
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendance.tCourseCheck()");
			e.printStackTrace();
		}
		
		
	}
	


	/**
	 * 교사가 특정 과정의 모든 교육생의 출결을 조회하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherAllAtdCheck(String teacherNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procStdAtdCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                        "+ openCourseSeq + "과정 교육생 출결 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[교육생번호]\t[수강신청번호]\t[이름]\t[날짜]\t[출결현황]\t");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq); 
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);

			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%-8s\t%8s\t%-8s\t\n",rs.getString("교육생번호"),rs.getString("수강신청번호"),rs.getString("교육생이름"),rs.getString("날짜"),rs.getString("출결현황"));
				
			}
			
			boolean loop = true;
			
			while (loop) {
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1. 기간별 출결 조회\n");
				System.out.print("2. 특정 교육생 출결 조회\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("번호 선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println("[기간별 출결 조회]");
					teacehrPeriodAtd(teacherNum, openCourseSeq);
					loop = false;
				} else if (num.equals("2")) {
					System.out.println("[특정 교육생 출결 조회]");
					teacherOneStdAtd(teacherNum, openCourseSeq);
					loop = false;
				} else if (num.equals("0")){
					TeacherAttendance.teacherAttendance(teacherNum);
					
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("TeacherAttendance.teacherAllAtdCheck()");
			e.printStackTrace();
		}
		
		
	}




	/**
	 * 교사가 특정 교육생의 출결을 조회하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherOneStdAtd(String teacherNum, String openCourseSeq) {
		
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		System.out.print("교육생 번호: ");
		String stdNum = scan.nextLine();
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procAtdSdSeqCheck(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                         교육생 출결 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[교육생번호]\t[이름]\t\t[날짜]\t\t[출결현황]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq);
			cstat.setString(3, stdNum);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(4);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%-8s\t%8s\t%-8s\t\n",rs.getString("교육생번호"),rs.getString("교육생이름"),rs.getString("날짜"),rs.getString("출결현황"));
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherAttendance.teacherOneStdAtd()");
			e.printStackTrace();
		}
		
		
		pause();
		TeacherAttendance.teacherAllAtdCheck(teacherNum, openCourseSeq);
	}




	/**
	 * 교사가 특정기간의 교육생 출결을 조회하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacehrPeriodAtd(String teacherNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		System.out.print("1.조회 시작일(ex. 2020-12-20): ");
		String begin = scan.nextLine();
		System.out.print("2.조회 종료일(ex. 2020-12-20): ");
		String end = scan.nextLine();
		System.out.println();
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procAtdPeriodCheck(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("             "+ begin +" ~ " + end +" 기간 출결 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[교육생번호]\t[이름]\t\t[날짜]\t\t[출결현황]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq);
			cstat.setString(3, begin);
			cstat.setString(4, end);

			cstat.registerOutParameter(5, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(5);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%-8s\t%8s\t%-8s\t\n",rs.getString("교육생번호"),rs.getString("교육생이름"),rs.getString("날짜"),rs.getString("출결현황"));
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("TeacherAttendance.teacehrPeriodAtd()");
			e.printStackTrace();
		}
		
		pause();
		TeacherAttendance.teacherAllAtdCheck(teacherNum, openCourseSeq);
		
		
		
	}
	
	
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause

}
