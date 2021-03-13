package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교사의 배점 목록과 시험을 담당하는 메인입니다.
 * @author User
 *
 */
public class TeacherPointExamMain {
	
	/**
	 * 배점 목록이나 시험 목록중에 선택하여 조회하는 메소드입니다.
	 * @param teacherNumber
	 */
	public static void TeacherPointExamMain(String teacherNumber) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("----------------------");
			System.out.println("  배점 및 시험 관리");
			System.out.println("----------------------");
			System.out.println("1. 배점 목록");
			System.out.println("2. 시험 목록");
			System.out.println("0. 뒤로 가기");
			System.out.println("----------------------");
			
			System.out.print("선택 : ");
			String num = scan.nextLine();
			
			String string = null;
			
			if (num.equals("1")) {
				string = "배점";
				chargeCourse(teacherNumber, string);	
			} else if (num.equals("2")) {
				string = "시험";
				chargeCourse(teacherNumber, string);
			} else {
				loop = false;
			}
			
		}
		
	}

	/**
	 * 과정을 담당한 교사가 자신의 과정만 조회할 수 있는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 가져오는 매개변수입니다.
	 * @param string 배점으로 조회할지, 시험으로 조회할지 결정하는 매개변수입니다.
	 */
	private static void chargeCourse(String teacherNumber, String string) {
		
		System.out.println();

		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t과정 조회");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[번호]\t[과정명]\t\t\t\t\t\t\t\t[과정시작날짜]\t[과정종료날]\t[강의실]\t[인원수]\t[진행상태]");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<TeacherChargeCourseDTO> chargeCourseList = dao.chargeCourseList(teacherNumber);
		
		for (TeacherChargeCourseDTO chargeCourseDto : chargeCourseList) {
			
			System.out.printf("%4s\t%-45s\t\t%s\t%s\t%s\t\t%5s\t\t%s\n"
								, chargeCourseDto.getSeq()
								, chargeCourseDto.getCourseName()
								, chargeCourseDto.getCourseBegin()
								, chargeCourseDto.getCourseEnd()
								, chargeCourseDto.getLectureRoomName()
								, chargeCourseDto.getCourseStudent()
								, chargeCourseDto.getCourseState());
			
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
		chargeSubject(teacherNumber, string);
		
	}
	
	/**
	 * 과정을 담당한 교사가 자신의 과정만 조회하고 그 과정의 과목 목록을 조회하는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 가져오는 매개변수입니다. 
	 * @param string 배점으로 조회할지, 시험으로 조회할지 결정하는 매개변수입니다.
	 */
	private static void chargeSubject(String teacherNumber, String string) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		System.out.print("선택 : ");
		String num = scan.nextLine();
		
		ArrayList<TeacherChargeCourseSubjectDTO> chargeSubjectList = dao.subjectList(teacherNumber, num);
		
		String courseName = chargeSubjectList.get(0).getCourseName();
		
		System.out.println();
		
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t 과목 조회");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.printf("\t\t\t%s\n", courseName);
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		
		
		for (TeacherChargeCourseSubjectDTO chargeSubjectDto : chargeSubjectList) {
			
			System.out.println("[번호]\t[과정시작날짜]\t[과정종료날]\t[인원수]\t[진행상태]");
			
			System.out.printf("%4s\t%s\t%s\t%s\t\t%s\n"
							, chargeSubjectDto.getSeq()
							, chargeSubjectDto.getOpenSubjectBegin()
							, chargeSubjectDto.getOpenSubjectEnd()
							, chargeSubjectDto.getCourseStudent()
							, chargeSubjectDto.getCourseState());
			
			System.out.printf("[과목명] : %s\n", chargeSubjectDto.getSubjectName());
			
			System.out.println("------------------------------------------------------------------------------------------------------------------");
		}
		
		
		System.out.println();
		
		if (string.equals("배점")) {
			point(teacherNumber, num);
		} else if (string.equals("시험")) {
			exam(teacherNumber, num);
		}
	
	}

	/**
	 * 과정의 과목을 선택하여 그 과목의 배점을 조회하는 메소드입니다.
	 * @param teacherNumber 교사의 기본키를 가져오는 매개변수입니다. 
	 * @param courseNumber 과정의 기본키를 가져오는 매개변수입니다.
	 */
	private static void point(String teacherNumber, String courseNumber) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		System.out.print("선택 : ");
		String num = scan.nextLine();
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("\t\t\t배점정보 및 수정");
		System.out.println("-------------------------------------------------------");
		
		ArrayList<TeacherPointDTO> pointList = dao.pointList(teacherNumber, courseNumber, num);
		
		String pointNumber = null;
		String pointOpenSubjectNumber = null;
		
		for (TeacherPointDTO pointDto : pointList) {
			
			System.out.printf("과 정 명 : %s\n", pointDto.getCourseName());
			System.out.printf("과 목 명 : %s\n", pointDto.getSubjectName());
			System.out.printf("출결 배점 : %s\n", pointDto.getPointAttendance());
			System.out.printf("필기 배점 : %s\n", pointDto.getPointHandWriting());
			System.out.printf("실기 배점 : %s\n", pointDto.getPointPractical());
			pointNumber = pointDto.getPointSeq();
			pointOpenSubjectNumber = pointDto.getOpenSubjectSeq();
			
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("1. 수정\t2. 삭제\t0. 뒤로 가기");
		System.out.println("-------------------------------------------------------");
		System.out.print("선택 : ");
		String number = scan.nextLine();
		System.out.println();
		
		if (number.equals("1")) {
			pointUpdateDelete(pointNumber, pointOpenSubjectNumber , pointList, number);
		} else if (number.equals("2")) {
			pointUpdateDelete(pointNumber, pointOpenSubjectNumber , pointList, number);
		} else {
			return;
		}
		
	}

	/**
	 * 과목의 배점을 수정하는 메소드입니다.
	 * @param pointNumber 배점의 기본키를 가져오는 매개변수입니다.
	 * @param pointOpenSubjectNumber 배점의 개설과목 외래키를 가져오는 매개변수입니다.
	 * @param pointList 기존의 조회한 배점 리스트를 가져오는 매개변수입니다.
	 * @param number 
	 */
	private static void pointUpdateDelete(String pointNumber, String pointOpenSubjectNumber, ArrayList<TeacherPointDTO> pointList, String number) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		String Attendance = null;
		String HandWriting = null;
		String Practical = null;
		
		for (TeacherPointDTO pointDto : pointList) {
			
			Attendance = pointDto.getPointAttendance();
			HandWriting = pointDto.getPointHandWriting();
			Practical = pointDto.getPointPractical();
			
		}
		
		if (number.equals("2")) {
			
			System.out.println("삭제를 원하시면 엔터만 눌러주십시오.");
			
		}
		
		System.out.println("-----------------------");
		
		System.out.print("수정할 출결 배점 : ");
		String pointAttendance = scan.nextLine();

		
		System.out.print("수정할 필기 배점 : ");
		String pointHandWriting = scan.nextLine();
		
		
		System.out.print("수정할 실기 배점 : ");
		String pointPractical = scan.nextLine();
		
		System.out.println("-----------------------");
		
		if (number.equals("1")) {
			
			int numAttendance = Integer.parseInt(pointAttendance);
			
			if (pointAttendance.equals("")){
				
				pointAttendance = Attendance;
				
			} else if (numAttendance < 20) {
				
				System.out.println("출결 배점은 20점 이상이어야 합니다.");
				pointAttendance = Attendance;
				pointHandWriting = HandWriting;
				pointPractical = Practical;
				return;
				
			}
			
			int numHandWriting = Integer.parseInt(pointHandWriting);
			
			if (pointHandWriting.equals("")) {
				
				pointHandWriting = HandWriting;
				
			}
			
			int numPractical = Integer.parseInt(pointPractical);
			
			if (pointPractical.equals("")) {
				
				pointPractical = Practical;
				
			} 
			
			if (numAttendance + numHandWriting + numPractical != 100) {
				pointAttendance = Attendance;
				pointHandWriting = HandWriting;
				pointPractical = Practical;
				System.out.println("출결과 필기와 실기 배점의 합은 100이여야 합니다.");
				return;
			}
			
		}
		
		if (number.equals("2")) {
			
			if (!pointAttendance.equals("") || !pointHandWriting.equals("") || !pointPractical.equals("")) {
				
				System.out.println("삭제는 엔터만 눌러주십시오.");
				
				return;
				
			}
			
		}
		
		
		TeacherPointDTO pointUpdateDto = new TeacherPointDTO();
		
		pointUpdateDto.setPointAttendance(pointAttendance);
		pointUpdateDto.setPointHandWriting(pointHandWriting);
		pointUpdateDto.setPointPractical(pointPractical);
		
		int pointUpdateResult = dao.pointUpdateDelete(pointNumber, pointOpenSubjectNumber, pointUpdateDto, number);
		
		
		if (pointUpdateResult == 1) {
			
			if (number.equals("1")) {
			
				System.out.println("배점 수정 완료");
			
			} else if (number.equals("2")) {
				
				System.out.println("배점 삭제 완료");
				
			}
			
		} else {
			
			System.out.println("배점 수정 실패");
			
		}
		
		pause();
		
	}
	
	private static void exam(String teacherNumber, String courseNumber) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		System.out.print("선택 : ");
		String subjectNumber = scan.nextLine();
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t시험정보");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		ArrayList<TeacherExamDTO> examList = dao.examList(teacherNumber, courseNumber, subjectNumber);
		
		for (TeacherExamDTO examDto : examList) {
			
			System.out.println("[번호]\t[시험일]\t[등록일]");
			System.out.printf("%3s\t%s\t%s\n"
							, examDto.getSeq()
							, examDto.getExamDate()
							, examDto.getExamPaperDate());
			System.out.printf("문제 : %s\n"
							, examDto.getQuestion());
			System.out.printf(" 답  : %s\n"
							, examDto.getAnswer());
			
			System.out.println("-------------------------------------------------------------------------------------------");
			
		}
		
		examQuestion(teacherNumber, courseNumber, subjectNumber);
		
	}

	private static void examQuestion(String teacherNumber, String courseNumber, String subjectNumber) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		System.out.println("자세히 보기 원하시는 문제 번호를 입력해주세요.");
		System.out.print("선택 : ");
		String questionNumber = scan.nextLine();
		
		ArrayList<TeacherExamDTO> examList = dao.examNumberList(teacherNumber, courseNumber, subjectNumber, questionNumber);
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t시험정보 및 수정");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		String examQuestionSeq = null;
		String examPaperSeq = null;
		
		for (TeacherExamDTO examDto : examList) {
			
			System.out.printf("과 정 명 : %s\n", examDto.getCourseName());
			System.out.printf("과 목 명 : %s\n", examDto.getSubjectName());
			System.out.printf("문제 번호 : %s번\n", examDto.getSeq());
			System.out.printf("문    제 : %s\n", examDto.getQuestion());
			System.out.printf("   답    : %s\n", examDto.getAnswer());
			examQuestionSeq = examDto.getExamQuestionSeq();
			examPaperSeq = examDto.getExamPaperSeq();
			
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("1. 수정\t2. 삭제\t0. 뒤로 가기");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		String number = scan.nextLine();
		System.out.println();
		
		if (number.equals("1")) {
			examUpdateDelete(examQuestionSeq, examPaperSeq, examList, number);
		} else if (number.equals("2")) {
			examUpdateDelete(examQuestionSeq, examPaperSeq, examList, number);
		} else {
			return;
		}
		
	}

	private static void examUpdateDelete(String examQuestionSeq, String examPaperSeq, ArrayList<TeacherExamDTO> examList, String number) {
		
		Scanner scan = new Scanner(System.in);
		TeacherPointExamDAO dao = new TeacherPointExamDAO();
		
		String examQuestion = null;
		String examAnswer = null;
		
		for (TeacherExamDTO examDto : examList) {
			
			examQuestion = examDto.getQuestion();
			examAnswer = examDto.getAnswer();
			
		}
		
		if (number.equals("2")) {
		
			System.out.println("삭제를 원하시면 엔터만 눌러주십시오.");
			
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		System.out.print("수정할 문제 : ");
		String updateQuestion = scan.nextLine();
		
		
		System.out.print("수정할 답 : ");
		String updateAnswer = scan.nextLine();

		
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		if (number.equals("1")) {
			
			if (updateQuestion.equals("")) {
				
				updateQuestion = examQuestion;
				
			} 
			
			if (updateAnswer.equals("")) {
				
				updateAnswer = examAnswer;
				
			} 
			
		} else if (number.equals("2")) {
			
			if (!updateQuestion.equals("") && !updateAnswer.equals("")) {
				
				System.out.println("삭제는 엔터만 눌러주십시오.");
				
				return;
				
			}
			
		}
		
		
		TeacherExamDTO examUpdateDto = new TeacherExamDTO();
		examUpdateDto.setQuestion(updateQuestion);
		examUpdateDto.setAnswer(updateAnswer);
		
		int pointUpdateResult = dao.examUpdate(examQuestionSeq, examPaperSeq, examUpdateDto, number);
		
		if (pointUpdateResult == 1) {
			
			if (number.equals("1")) {
				
				System.out.println("시험 문제 수정 성공");
				
			} else if (number.equals("2")) {
				
				System.out.println("시험 문제 삭제 성공");
				
			}
			
		} else {
			System.out.println("시험 문제 수정 실패");
		}
		
		pause();
		
	}

	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause
	
}
