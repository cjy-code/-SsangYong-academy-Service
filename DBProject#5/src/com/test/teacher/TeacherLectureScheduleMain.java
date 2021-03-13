package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 강의 스케줄을 조회하는 메인입니다.
 * @author User
 *
 */
public class TeacherLectureScheduleMain {
	
	/**
	 * 강의 정보를 확인할 수 있는 메소드입니다.
	 */
	public static void TeacherLectureScheduleMain() {
		
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("----------------------");
			System.out.println("    강의 상태 목록");
			System.out.println("----------------------");
			System.out.println("1. 강의 진행중 목록");
			System.out.println("2. 강의 예정 목록");
			System.out.println("3. 강의 종료 목록");
			System.out.println("0. 뒤로가기");
			System.out.println("----------------------");
			
			System.out.print("선택 : ");
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				String progress = "진행중";
				lectureSchedule(progress);
			} else if (num.equals("2")) {
				String expected = "진행예정";
				lectureSchedule(expected);
			} else if (num.equals("3")) {
				String end = "종료";
				lectureSchedule(end);
			} else {
				loop = false;
			} //if
			
		} //while
		
	} //main

	/**
	 * 과정의 과목을 볼 것인지 과정의 교육생을 볼 것인지 결정하는 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 */
	private static void lectureSchedule(String state) {
		
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("---------------------");
			System.out.printf("\t%s\n", state);
			System.out.println("---------------------");
			System.out.println("1. 과정-과목 정보");
			System.out.println("2. 과정-교육생 정보");
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------");
			
			String string = "";
			
			System.out.print("선택 : ");
			String num = scan.nextLine();
			if (num.equals("1")) {
				string = "과목";
				course(string, state);
			} else if (num.equals("2")) {
				string = "교육생";
				course(string, state);
			} else {
				loop = false;
			}
			
		}
		
	}

	/**
	 * 과정의 목록을 보여주는 메소드입니다.
	 * @param string 과목인지 교육생인지 알려주는 매개변수입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 */
	private static void course(String string, String state) {
		
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		TeacherLectureScheduleDAO dao = new TeacherLectureScheduleDAO();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("\t\t\t\t\t\t\t[%s] 과정 조회\n", state);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[번호]\t[과정명]\t\t\t\t\t\t\t\t[과정시작날짜]\t[과정종료날]\t[강의실]\t[인원수]\t[진행상태]");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<TeacherCourseDTO> courseList = dao.courseList(state);
		
		for (TeacherCourseDTO courseDto : courseList) {
			System.out.printf("%4s\t%-45s\t\t%s\t%s\t%s\t\t%5s\t\t%s\n"
								, courseDto.getSeq()
								, courseDto.getCourseName()
								, courseDto.getCourseBegin()
								, courseDto.getCourseEnd()
								, courseDto.getLectureRoomName()
								, courseDto.getcourseStudent()
								, courseDto.getCourseState());
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		if (string.equals("과목")) {
			
			subject(state);
		
		} else if (string.equals("교육생")) {
			
			student(state);
			
		}
		
	} //courseSubject

	/**
	 * 지정한 과정의 과목 목록을 조회하는 메소드입니다.
	 * @param state 과정의 상태를 알려주는 매개변수입니다.
	 */
	private static void subject(String state) {
		
		Scanner scan = new Scanner(System.in);
		TeacherLectureScheduleDAO dao = new TeacherLectureScheduleDAO();
		
		System.out.print("선택 : ");
		String num = scan.nextLine();
		
		ArrayList<TeacherCourseSubjectDTO> subjectList = dao.subjectList(state, num);
		
		String courseName = subjectList.get(0).getCourseName();
		
		System.out.println();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.printf("\t\t\t\t\t[%s]과목 조회\n", state);
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.printf("\t\t\t\t%s\n", courseName);
		System.out.println("-------------------------------------------------------------------------------------------------------------");

		for (TeacherCourseSubjectDTO subjectDto : subjectList) {
			
			System.out.println("[번호]\t[과정시작날짜]\t[과정종료날]\t[인원수]");
			
			System.out.printf("%4s\t%s\t%s\t%s\n"
							, subjectDto.getSeq()
							, subjectDto.getOpenSubjectBegin()
							, subjectDto.getOpenSubjectEnd()
							, subjectDto.getCourseStudent());
			
			System.out.printf("[과정명] : %s\n", subjectDto.getSubjectName());
			System.out.printf("[교재명] : %s\n", subjectDto.getBookTitle());
		
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			
			courseName = subjectDto.getCourseName();
			
		}
		
		
		
		System.out.println();
		
		pause();
		
	}

	private static void student(String state) {
		
		Scanner scan = new Scanner(System.in);
		TeacherLectureScheduleDAO dao = new TeacherLectureScheduleDAO();
		
		System.out.print("선택 : ");
		String num = scan.nextLine();
		
		System.out.println("-------------------------------------------------------------------");
		System.out.printf("\t\t\t[%s] 교육생 조회\n", state);
		System.out.println("-------------------------------------------------------------------");
		System.out.println("[번호]\t[교육생명]\t[전화번호]\t[회원날짜]\t[수료여부]");
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println();
		
		ArrayList<TeacherCourseStudentDTO> studentList = dao.studentList(state, num);
		
		for (TeacherCourseStudentDTO studentDto : studentList) {
			System.out.printf("%s\t%s\t\t%s\t%s\t%s\n"
							, studentDto.getSeq()
							, studentDto.getStudentName()
							, studentDto.getStudentTel()
							, studentDto.getStudentRegister()
							, studentDto.getCompletionCheck());
			
		}
		
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println();
		
		pause();
		
		
	} //courseStudent

	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause

}


























