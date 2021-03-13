package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교사가 교육생 성적 관리를 할 수 있는 기능의 메인입니다.
 * @author User
 *
 */
public class TeacherScore {

	private static Scanner scan;
	private static TeacherScoreDAO dao;

	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherScoreDAO();
	}
	
	
	
	/**
	 * 교사가 교육생 성적을 관리할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	public static void teacherScore(String teacherNum) {
		
	
		
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
			System.out.println("                                  성적관리");
			System.out.println("                          전체 개설과정 목록 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[교사번호]\t[개설과정번호]\t[과정명]\t\t\t\t\t\t\t\t[과정기간]\t\t[강의실]\t[학생수]\t[과정진행상태]");
			
			while (rs.next()) {

				System.out.printf("%10s\t%13s\t%-50s\t%10s\t%10s\t%8s\t%10s\n"
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
			System.out.println("TeacherScore.teacherScore()");
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
					teacherCourseCheck(teacherNum);
					loop = false;
				} else if (num.equals("0")) {
					TeacherMain.TeacherMain(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
		
		System.out.println();
		

		
		
	}
	
	/**
	 * 교사가 담당하는 과정의 교육생 성적을 관리할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	public static void teacherCourseCheck(String teacherNum) {
	
	TeacherDTO dto = new TeacherDTO();
	
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
				System.out.print("1.담당 과정 과목 목록 조회\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println();
					System.out.print("개설 과정 번호: ");
					String openCourseSeq = scan.nextLine();
					teacherSubjectCheck(teacherNum, openCourseSeq);
					loop = false;
				} else if (num.equals("0")) {
					TeacherScore.teacherScore(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				} 
				
		
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
		
		
		
	} catch (Exception e) {
		System.out.println("TeacherScore.teacherScore()");
		e.printStackTrace();
	}
	
	
	
}


	/**
	 * 교사가 특정 과목의 교육생 성적을 관리할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
private static void teacherSubjectCheck(String teacherNum, String openCourseSeq) {
	
	TeacherDTO dto = new TeacherDTO();
	
	Connection conn = null;
	CallableStatement cstat = null;
	
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "{ call procSubjectCheck(?, ?, ?) }";
		
		cstat = conn.prepareCall(sql);
		
		System.out.println();
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println("                       개설 과정 과목 목록 조회");
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println("[개설과목번호]\t[과목명]\t[과목시작일시]\t[과목종료일시]\t[강의실]\t[교재명]\t[출결배점]\t[필기배점]\t[실기배점]\t[성적등록여부]\t\n");
		
		cstat.setString(1, teacherNum); 
		cstat.setString(2, openCourseSeq);

		cstat.registerOutParameter(3, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		ResultSet rs = (ResultSet)cstat.getObject(3);
		
		
		while (rs.next()) {
			System.out.printf("%13s\t%-30s\t%30s\t%15s\t%-10s\t%-15s\t%10s\t%10s\t%10s\t%-10s\t\n",rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("과목시작일시"),rs.getString("과목종료일시"),rs.getString("강의실"),rs.getString("교재명"),rs.getString("출결배점"),rs.getString("필기배점"),rs.getString("실기배점"),rs.getString("성적등록여부"));
			
		}
		
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.print("1.특정 과목 전체 교육생 성적 조회\n");
			System.out.print("0. 뒤로가기\n");
			System.out.println();
			System.out.print("선택: ");
			String num = scan.nextLine();
			System.out.println();
			
			if (num.equals("1")) {
				System.out.println();
				System.out.print("개설 과목 번호: ");
				String openSubjectNum = scan.nextLine();
				teacherAllStdScoreCheck(teacherNum, openSubjectNum);
				loop = false;
			} else if (num.equals("0")) {
				TeacherScore.teacherCourseCheck(teacherNum);
			} else {
				System.out.println("잘 못 입력하셨습니다.");
			} 

		}
		
		rs.close();
		cstat.close();
		conn.close();
		
		
	} catch (Exception e) {
		System.out.println("TeacherScore.teacherSubjectCheck()");
		e.printStackTrace();
	}
	

	
}

/**
 * 교사가 특정 과정의 전체 교육생 성적을 조회할 수 있는 메소드입니다.
 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
 * @param openSubjectNum 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
 */
private static void teacherAllStdScoreCheck(String teacherNum, String openSubjectNum) {
	
	System.out.println();
	Connection conn = null;
	CallableStatement cstat = null;
	
	try {
		conn = DBUtil.open();
		
		String sql = "{ call procStudentCheck(?, ?, ?) }";
		
		cstat = conn.prepareCall(sql);
		
		System.out.println();
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println(                openSubjectNum+"과목 전체 교육생 성적 조회");
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println("[교육생번호]\t[수강신청번호]\t[이름]\t[전화번호]\t[수료상태]\t[수료및중도탈락날짜]\t[출결성적]\t[필기성적]\t[실기성적]\t\n");
		
		cstat.setString(1, teacherNum);
		cstat.setString(2, openSubjectNum);

		cstat.registerOutParameter(3, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		ResultSet rs = (ResultSet)cstat.getObject(3);
		
		
		while (rs.next()) {
			System.out.printf("%12s\t%12s\t%12s\t%-15s\t%-15s\t%-20s\t%12s\t%12s\t%12s\t\n",rs.getString("교육생번호"),rs.getString("수강신청번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료상태"),rs.getString("수료및중도탈락날짜"),rs.getString("출결성적"),rs.getString("필기성적"),rs.getString("실기성적"));
			
		}
		
		boolean loop = true;
		
		while (loop) {
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.print("1.교육생 성적 데이터 추가\n");
			System.out.print("2.특정 교육생 성적 조회\n");
			System.out.print("0. 뒤로가기\n");
			System.out.println();
			System.out.print("선택: ");
			String num = scan.nextLine();
			System.out.println();
			
			if (num.equals("1")) {
				System.out.println("[교육생 성적 데이터 추가]");
				addScore(teacherNum);
				loop = false;
			} else if (num.equals("2")) {
				System.out.println();
				System.out.print("교육생 번호: ");
				String studentNum = scan.nextLine();
				teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
				loop = false;
			} else if (num.equals("0")) {
				TeacherScore.teacherCourseCheck(teacherNum);
			} else {
				System.out.println("잘 못 입력하셨습니다.");
			} 
		}
		
		rs.close();
		cstat.close();
		conn.close();
		
		
		
		
		
	} catch (Exception e) {
		System.out.println("TeacherScore.teacherAllStdScoreCheck()");
		e.printStackTrace();
	}
	
}


/**
 * 교사가 특정 교육생의 성적을 관리할 수 있는 메소드입니다.
 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
 * @param studentNum 특정 교육생을 조회할 수 있는 교육생 번호를 알려주는 매개변수입니다.
 */
private static void teacherOneStdScoreCheck(String teacherNum, String openSubjectNum, String studentNum) {
	
	System.out.println();
	Connection conn = null;
	CallableStatement cstat = null;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "{ call procOneStudentCheck(?, ?, ?, ?) }";
		
		cstat = conn.prepareCall(sql);
		
		System.out.println();
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println(                			"교육생 성적 조회");
		System.out.print("------------------------------------------------------------------------------\n");
		System.out.println("[교육생번호]\t[수강신청번호]\t[이름]\t[전화번호]\t[수료상태]\t[수료및중도탈락날짜]\t[출결성적]\t[필기성적]\t[실기성적]\t\n");
		
		cstat.setString(1, teacherNum);
		cstat.setString(2, openSubjectNum);
		cstat.setString(3, studentNum);

		cstat.registerOutParameter(4, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		ResultSet rs = (ResultSet)cstat.getObject(4);
		
		
		while (rs.next()) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n",rs.getString("교육생번호"),rs.getString("수강신청번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료상태"),rs.getString("수료및중도탈락날짜"),rs.getString("출결성적"),rs.getString("필기성적"),rs.getString("실기성적"));
			
		}
		
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.print("------------------------------------------------------------------------------\n");

			System.out.print("1.출결 점수 관리\n");
			System.out.print("2.필기 점수 관리\n");
			System.out.print("3.실기 점수 관리\n");
			System.out.print("0. 뒤로가기\n");
			System.out.println();
			System.out.print("선택: ");
			String num = scan.nextLine();
			System.out.println();
			
			if (num.equals("1")) { 
				System.out.println();
				System.out.println("[출결 점수 관리]");
				System.out.println("1. 출결 점수 등록");
				System.out.println("2. 출결 점수 수정");
				System.out.println("3. 출결 점수 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println();
				System.out.print("선택: ");
				String atBbutton = scan.nextLine();
					if (atBbutton.equals("1")) {
						System.out.println();
						System.out.println("[출결 점수 등록]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("출결 점수: ");
						String atdScore = scan.nextLine();
						teacherAddAtdScore(teacherNum, openSubjectNum, classNum, atdScore, studentNum);
						loop = false;
					} else if (atBbutton.equals("2")) {
						System.out.println();
						System.out.println("[출결 점수 수정]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("수정할 출결 점수: ");
						String atdScore = scan.nextLine();
						teacherEditAtdScore(teacherNum, openSubjectNum, classNum, atdScore, studentNum);
						loop = false;
					} else if (atBbutton.equals("3")) {
						System.out.println();
						System.out.println("[출결 점수 삭제]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						teacherDeleteAtdScore(teacherNum, openSubjectNum, classNum, studentNum);
						loop = false;
					} else if (atBbutton.equals("0")){
//						TeacherMain.teacherMain(teacherNum); //수정해야함. 그냥 넣어논 것.
						TeacherScore.teacherAllStdScoreCheck(teacherNum, openSubjectNum);
					} else {
						System.out.println("잘 못 입력하셨습니다.");
					}
			} else if (num.equals("2")) {
				System.out.println();
				System.out.println("[필기 점수 관리]");
				System.out.println("1. 필기 점수 등록");
				System.out.println("2. 필기 점수 수정");
				System.out.println("3. 필기 점수 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println();
				System.out.print("선택: ");
				String writtingButton = scan.nextLine();
					if (writtingButton.equals("1")) {
						System.out.println("[필기 점수 등록]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("필기 점수: ");
						String hwScore = scan.nextLine();
						teacherAddHWScore(teacherNum, openSubjectNum, classNum, hwScore, studentNum);
						loop = false;
					} else if (writtingButton.equals("2")) {
						System.out.println();
						System.out.println("[필기 점수 수정]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("수정할 필기 점수: ");
						String hwScore = scan.nextLine();
						teacherEditHWScore(teacherNum, openSubjectNum, classNum, hwScore, studentNum);
						loop = false;
					} else if (writtingButton.equals("3")) {
						System.out.println();
						System.out.println("[필기 점수 삭제]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						teacherDeleteHWScore(teacherNum, openSubjectNum, classNum, studentNum);
						loop = false;
					} else if (writtingButton.equals("0")){
						TeacherScore.teacherAllStdScoreCheck(teacherNum, openSubjectNum);
						
					} else {
						System.out.println("잘 못 입력하셨습니다.");
					}
				
			} else if (num.equals("3")) {
				System.out.println();
				System.out.println("[실기 점수 관리]");
				System.out.println("1. 실기 점수 등록");
				System.out.println("2. 실기 점수 수정");
				System.out.println("3. 실기 점수 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println();
				System.out.print("선택: ");
				String practicalButton = scan.nextLine();
					if (practicalButton.equals("1")) {
						System.out.println("[실기 점수 등록]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("실기 점수: ");
						String prScore = scan.nextLine();
						teacherAddPrScore(teacherNum, openSubjectNum, classNum, prScore, studentNum);
						loop = false;
					} else if (practicalButton.equals("2")) {
						System.out.println();
						System.out.println("[실기 점수 수정]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						System.out.print("수정할 실기 점수: ");
						String prScore = scan.nextLine();
						teacherEditPrScore(teacherNum, openSubjectNum, classNum, prScore, studentNum);
						loop = false;
					} else if (practicalButton.equals("3")) {
						System.out.println();
						System.out.println("[실기 점수 삭제]");
						System.out.print("교육생의 수강신청 번호: ");
						String classNum = scan.nextLine();
						teacherDeletePrScore(teacherNum, openSubjectNum, classNum, studentNum);
						loop = false;
					} else if (practicalButton.equals("0")){
						TeacherScore.teacherAllStdScoreCheck(teacherNum, openSubjectNum);
						
					} else {
						System.out.println("잘 못 입력하셨습니다.");
					}
			} else if (num.equals("0")){
				TeacherScore.teacherAllStdScoreCheck(teacherNum, openSubjectNum);
				
			} else {
				System.out.println("잘 못 입력하셨습니다.");
			}
		}
		
		rs.close();
		cstat.close();
		conn.close();
		
		
		
	} catch (Exception e) {
		System.out.println("TeacherScore.teacherOneStdScoreCheck()");
		e.printStackTrace();
	}
	
}


	/**
	 * 교사가 실기점수를 삭제할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 */
	private static void teacherDeletePrScore(String teacherNum, String openSubjectNum, String classNum, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		
		int result = dao.DeletePrScore(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();

		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 실기 점수를 수정할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param prScore 실기 점수를 알려준느 매개변수입니다.
	 */
	private static void teacherEditPrScore(String teacherNum, String openSubjectNum, String classNum, String prScore, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScorePractical(prScore);
		
		int result = dao.EditPrScore(dto);
		System.out.println();
		System.out.println("※ 수정이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
	
}



	/**
	 * 교사가 실기 점수를 추가할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param prScore 실기점수를 알려주는 매개변수입니다.
	 */
	private static void teacherAddPrScore(String teacherNum, String openSubjectNum, String classNum, String prScore, String studentNum) {
		
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScorePractical(prScore);
		
		int result = dao.addPrScore(dto);
		System.out.println();
		System.out.println("※ 등록이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 필기 점수를 삭제할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 */
	private static void teacherDeleteHWScore(String teacherNum, String openSubjectNum, String classNum, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		
		int result = dao.DeleteHWScore(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 필기 점수를 수정할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param hwScore 필기 점수를 알려주는 매개변수입니다.
	 */
	private static void teacherEditHWScore(String teacherNum, String openSubjectNum, String classNum, String hwScore, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScoreHandWriting(hwScore);
		
		int result = dao.EditHWScore(dto);
		System.out.println();
		System.out.println("※ 수정이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 필기 점수를 추가할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param hwScore 필기점수를 알려주는 매개변수입니다.
	 */
	private static void teacherAddHWScore(String teacherNum, String openSubjectNum, String classNum, String hwScore, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScoreHandWriting(hwScore);
		
		int result = dao.addHWScore(dto);
		System.out.println();
		System.out.println("※ 등록이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 출결 점수를 삭제할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 */
	private static void teacherDeleteAtdScore(String teacherNum, String openSubjectNum, String classNum, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		
		int result = dao.DeleteAtdScore(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 출결 점수를 수정할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param atdScore 출결 점수를 알려주는 매개변수입니다.
	 */
	private static void teacherEditAtdScore(String teacherNum, String openSubjectNum, String classNum, String atdScore, String studentNum) {
		
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScoreAttendance(atdScore);
		
		int result = dao.EditAtdScore(dto);
		System.out.println();
		System.out.println("※ 수정이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
}



	/**
	 * 교사가 출결점수를 추가할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 * @param classNum 조회하는 교육생의 수강신청 번호을 알려주는 매개변수입니다.
	 * @param atdScore 출결점수를 알려주는 매개변수입니다.
	 */
	private static void teacherAddAtdScore(String teacherNum, String openSubjectNum, String classNum, String atdScore, String studentNum) {
	
		TeacherScoreDTO dto = new TeacherScoreDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setClassSeq(classNum);
		dto.setScoreAttendance(atdScore);
		
		int result = dao.addAtdScore(dto);
		System.out.println();
		System.out.println("※ 등록이 완료되었습니다.");
		pause();
		teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
	
}



	/**
	 * 교육생의 성적 등록을 위한 기본정보 입력을 위한 메소드입니다.
	 * @param teacherNum 
	 */
	private static void addScore(String teacherNum) {
		
		System.out.println();
		System.out.print("개설과목번호: ");
		String osNum = scan.nextLine();
		
		System.out.print("수강신청번호: ");
		String cNum = scan.nextLine();

		System.out.print("기본출결점수: 엔터를 누르면 넘어갑니다.");
		String atdNum = scan.nextLine();
		
		System.out.print("기본필기점수: 엔터를 누르면 넘어갑니다.");
		String writeNum = scan.nextLine();
		
		System.out.print("기본실기점수: 엔터를 누르면 넘어갑니다.");
		String practicalNum = scan.nextLine();
		
		TeacherScoreDTO sdto = new TeacherScoreDTO();
		sdto.setOpenSubjectSeq(osNum);
		sdto.setClassSeq(cNum);
		sdto.setScoreAttendance(atdNum);
		sdto.setScoreHandWriting(writeNum);
		sdto.setScorePractical(practicalNum);
		
		int result = dao.add(sdto);
		System.out.println();
		System.out.println("※ 등록이 완료되었습니다.");
		pause();
		TeacherMain.TeacherMain(teacherNum);
		
		
	}
	
	
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause
	
	

	

}//teacherscore
