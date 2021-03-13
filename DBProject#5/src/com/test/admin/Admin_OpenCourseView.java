package com.test.admin;

import java.util.ArrayList;
import java.util.Scanner;

//import dao.AvailableSubjectDAO;
//import dao.CourseDAO;
//import dao.OpenC_OpenSubjectDAO;
//import dao.StudentDAO;
//import dao.SubjectDAO;
//import dao.VwOpenCourseDAO;
//import dto.CourseDTO;
//import dto.OpenC_OpenSubjectDTO;
//import dto.StudentDTO;
//import dto.SubjectDTO;
//import dto.VwOpenCourseDTO;

/**
 * 관리자 모드의 개설 과정 관리 클래스이다.
 * 전체 개설과정 조회, 특정 개설 과정 조회, 특정 개설 과정의 개설 과목 정보 조회, 특정 개설 과정의 교육생 정보 조회, 개설 과정 등록, 개설 과정 수정, 개설 과정 삭제 기능을 포함한다.
 * @author 정경화
 */
public class Admin_OpenCourseView {

	private static Scanner scan;
	private static VwOpenCourseDAO dao;
	private static OpenC_OpenSubjectDAO dao2;
	private static AvailableSubjectDAO dao3;

	static {
			scan = new Scanner(System.in);
			dao = new VwOpenCourseDAO();
			dao2 = new OpenC_OpenSubjectDAO();
			dao3 = new AvailableSubjectDAO();	
	}
	
	
	
	public static void main(String[] args) {
		openCourseManagement();
	}
	
	

	/**
	 * 관리자 모드의 개설 과정 관리 헤더를 출력하고 메뉴를 선택하는 메소드이다.
	 */
	public static void openCourseManagement() {
		
		boolean loop = true;
		
		while (loop) {	

		AdminView2.showHeader("개설 과정 관리");
		
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과정 조회\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 개설 과정 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t3. 개설 과정 수정\n");
		System.out.printf("\t\t\t\t\t\t\t\t4. 개설 과정 삭제\n");
		System.out.printf("\t\t\t\t\t\t\t\t0. 뒤로 가기\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		String sel = scan.nextLine();

		if (sel.equals("1")) {
			openCourseList(); //전체 개설 과정
			selectOpenCourse(); //특정 개설 과정 조회
			
		} else if (sel.equals("2")) {
			addOpenProcess();

		} else if (sel.equals("3")) {
			editOpenProcess();
		
		} else if (sel.equals("4")) {
			deleteOpenProcess();
		
		} else if (sel.equals("0")) {
			AdminController.start();
			
		} else if (sel.equals("9")) {
			loop = false;
		}
		
		}
	}
	
	
	/**
	 * 특정 개설 과정 조회 헤더를 출력하고 메뉴를 선택하는 메소드이다.
	 * 
	 */
	private static void selectOpenCourse() {
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t[특정 개설 과정 조회]\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 정보 조회\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 교육생 정보 조회\n");
		System.out.printf("\t\t\t\t\t\t\t\t0. 뒤로 가기\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.print("번호 선택: ");
		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			openSubjectList();
			
		} else if (sel.equals("2")) {
			StudentList();

		} else if (sel.equals("0")) {
			main(null);	
		}
		
	}


	/**
	 * 개설 과목 정보 조회 헤더를 출력하고 개설 과목 등록 메뉴를 선택할 수 있는 메소드이다.
	 * 개설 과목 정보 출력 후 개설 과목 등록을 선택할 수 있다.
	 */
	private static void openSubjectList() {
		
		System.out.println();
		System.out.print("조회할 개설 과정(번호): ");
		String coureSeq = scan.nextLine();	
		
		AdminView2.showHeader("개설 과목 정보 조회");
		
		openSubjectList(coureSeq);
		
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t0. 뒤로 가기\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		String sel = scan.nextLine();
		
		if (sel.equals("1")) {addOpenSubject(coureSeq);} 
		else if (sel.equals("0")) {selectOpenCourse();} 
		else if (sel.equals("9")) {openCourseManagement();}

	}

	
	/**
	 * 개설 과정 번호를 입력하면 해당 과정의 개설 과목 정보를 출력하는 메소드이다.
	 * 개설 과목 정보에는 과목번호, 개설 과정명, 과목명, 과목 시작일, 과목종료일, 교재명, 교사명이 포함된다.
	 * @param courseSeq 개설 과정 번호
	 */
	public static void openSubjectList(String courseSeq) {
		
		ArrayList<OpenC_OpenSubjectDTO> list = dao2.list(courseSeq);
		
		System.out.println();
		System.out.printf("\t\t\t\t\t\t\t\t[%s 과정의 개설 과목 조회]\n", courseSeq);
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과목번호\t\t개설과정명\t\t\t\t교사명\t과목시작일\t과목종료일\t\t과목명\t\t\t\t\t\t교재명\n");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		for (OpenC_OpenSubjectDTO dto : list) {

			System.out.printf("%s\t%s\t%s\t%s\t%s\t%-" + checkTitle(dto.getSubjectName(), 50) + "s\t%s\n"
			, dto.getOpenSubjectSeq()
			, dto.getCourseName()
			, dto.getTeacherName()
			, dto.getOpenSubjectBegin()
			, dto.getOpenSubjectEnd()
			, dto.getSubjectName()
			, dto.getBookTile());
//			, dto.getSubjectName().length()
//			, checkTitle(dto.getSubjectName(), 50));
		}
		
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		}		

		
	public static int checkTitle(String str, int length) {
		
		int result = length;
		
		for (int i = 0; i < str.length(); i++) {
			
			if (str.charAt(i) >= '가' && str.charAt(i) <= '힣') {
				
				result--;
				
			}
		}
		
		return result;
	
	}
		
		
	/**
	 * 특정 개설 과정의 개설 과목을 등록할 수 있는 메소드이다.
	 * @param courseSeq 선택한 개설 과정 번호
	 */
	private static void addOpenSubject(String courseSeq) {
		
		AdminView2.showHeader("개설 과목 등록");

		//기초 데이터 과목 목록 보여주기
		Admin_OpenSubjectView.subjectList();
		
		//수정할 과목 정보 입력
			System.out.println();
			System.out.println("등록할 개설 과목 정보를 입력해주세요.");
			System.out.println();
			
			System.out.print("과목번호(ex. 1): ");
			String subjectSeq = scan.nextLine();	
			System.out.print("과목시작일(ex. 2020-12-20): ");
			String openSubjectBegin = scan.nextLine();	
			System.out.print("과목종료일(ex. 2020-12-20): ");
			String openSubjectEnd = scan.nextLine();
		
		//강의가능한 교사 목록 보여주는 메소드
			Admin_OpenSubjectView.teacherList(subjectSeq);
	
			System.out.print("교사명: ");
			String teacherName = scan.nextLine();
	
			
		OpenC_OpenSubjectDTO dto = new OpenC_OpenSubjectDTO();
		
		//입력받은 개설 과목 정보를 DTO에 저장
		dto.setOpenCourseSeq(courseSeq);
		dto.setSubjectSeq(subjectSeq);
		dto.setOpenSubjectBegin(openSubjectBegin);
		dto.setOpenSubjectEnd(openSubjectEnd);
		dto.setTeacherName(teacherName);			
		
		//선택한 과목 번호의 과목명 가져오기
		VwOpenCourseDAO courseDao = new VwOpenCourseDAO();
		dto.setSubjectName(courseDao.getSubjectName(subjectSeq));		
		
		System.out.println("아래 개설 과목을 등록합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과정 번호\t\t개설과목명\t\t\t\t\t과목 시작일\t과목 종료일\t교사명\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.printf("%s\t\t%-45s\t%s\t%s\t%4s\n"
							, dto.getOpenCourseSeq()
							, dto.getSubjectName()						
							, dto.getOpenSubjectBegin()
							, dto.getOpenSubjectEnd()
							, dto.getTeacherName());

		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");

			String sel = scan.nextLine();
		
			if (sel.equals("1")) {
				
				int result = dao2.addOpenSubject(dto);	
				
				if (result > 0) {System.out.println("개설과목 등록이 완료되었습니다.");} 
				else {System.out.println("개설과목 등록에 실패했습니다.");}
				
			} else if (sel.equals("2")) {System.out.println("등록 메뉴로 돌아갑니다.");
				addOpenSubject(courseSeq);}
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 추가 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {addOpenSubject(courseSeq);} 
		else if (sel.equals("9")) {openCourseManagement();}
		
	}

	

	/**
	 * 교육생 정보 조회 헤더를 출력하고 특정 개설 과정의 교육생 정보를 조회할 수 있는 메소드이다.
	 * 교육생 정보에는 이름, 주민번호 뒷자리, 전화번호, 등록일, 수료 및 중도 탈락 여부가 포함된다.
	 */
	private static void StudentList() {
		
		System.out.print("조회할 개설 과정(번호): ");
		String courseSeq = scan.nextLine();
		
		AdminView2.showHeader("교육생 정보 조회");
		
		StudentDAO2 dao = new StudentDAO2();
		
		ArrayList<StudentDTO2> list = dao.list(courseSeq);
		
		System.out.printf("[이름]\t\t[주민번호 뒷자리]\t\t[전화번호]\t\t[등록일]\t[수료 및 중도 탈락 여부]\n");
		
		for (StudentDTO2 dto : list) {
			
			System.out.printf("%s\t\t   %s\t\t\t%s\t\t%s\t\t%s\n"
								, dto.getStudentname()
								, dto.getStudentssn()
								, dto.getStudenttel()
								, dto.getStudentregister()
								, dto.getCompletioncheck());			
		}
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t0. 뒤로 가기\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		String sel = scan.nextLine();
		
		if (sel.equals("0")) {selectOpenCourse();} 
		else if (sel.equals("9")) {openCourseManagement();}
	}


	/**
	 * 전체 개설 과정 조회 헤더를 출력하고 개설 과정 정보를 조회하는 메소드이다.
	 * 개설 과정 정보에는 개설 과정 번호, 개설 과정명, 과정 시작일, 과정 종료일, 강의실, 과목 등록 여부, 등록인원이 포함된다.
	 */
	private static void openCourseList() {

		System.out.println();
		
		AdminView2.showHeader("전체 개설 과정 조회");
		
		System.out.printf("번호\t\t\t개설과정명\t\t\t\t\t\t과정 시작일\t과정 종료일\t강의실\t   과목 등록 여부  등록 인원\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		ArrayList<VwOpenCourseDTO> list = dao.list();
		
		for (VwOpenCourseDTO dto : list) {
			
			System.out.printf("%s\t%-45s\t\t%s\t%s\t%4s\t\t%s\t\t%2s\n"
								, dto.getOpenCourseSeq()
								, dto.getCourseName()
								, dto.getOpenCourseBegin()
								, dto.getOpenCourseEnd()
								, dto.getLectureRoomName()
								, dto.getRegister()
								, dto.getStudent());			
		}
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
	}
	

	/**
	 * 개설 과정 등록 헤더를 출력하고 개설 과정을 등록할 수 있는 메소드이다.
	 * 과정 등록 후 개설 과정 추가 등록 메뉴를 선택할 수 있다. 
	 */
	private static void addOpenProcess() {
		
		AdminView2.showHeader("개설 과정 등록");
		
		//기초 데이터 과정 목록 보여주기
		courseList();
		System.out.println("등록 가능한 과정 목록입니다.");
		Admin_OpenSubjectView.pause();
		System.out.println();

		//추가할 개설 과정 정보 입력
		System.out.println("등록할 개설 과정 정보를 입력해주세요.");
		System.out.println();
		System.out.print("과정번호(ex. 1): "); //1~12번까지
		String courseSeq = scan.nextLine();
		
		System.out.print("과정시작일(ex. 2020-12-20): ");//날짜 지켜야 함
		String openCourseBegin = scan.nextLine();
		
		System.out.print("과정종료일(ex. 2020-12-20): ");
		String openCourseEnd = scan.nextLine();
		
		System.out.print("강의실명(ex. 1강의실): ");
		String lectureRoomName = scan.nextLine();
	
		//등록할 개설 과정 정보
		VwOpenCourseDTO dto = new VwOpenCourseDTO();
		dto.setCourseSeq(courseSeq);
		dto.setOpenCourseBegin(openCourseBegin);
		dto.setOpenCourseEnd(openCourseEnd);
		dto.setLectureRoomName(lectureRoomName);
		
		//선택한 개설 과정 번호의 과정명 가져오기
		VwOpenCourseDAO courseDao = new VwOpenCourseDAO();
		dto.setCourseName(courseDao.getCourseName(courseSeq));
	
	
		System.out.println("아래 개설 과정을 등록합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("번호\t\t\t개설과정명\t\t\t\t\t과정 시작일\t과정 종료일\t강의실\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.printf("%s\t%-45s\t%s\t%s\t%4s\n"
							, dto.getCourseSeq()
							, dto.getCourseName()						
							, dto.getOpenCourseBegin()
							, dto.getOpenCourseEnd()
							, dto.getLectureRoomName());

		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");

		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			
			int result = dao.addOpenCourse(dto);
			
			if (result > 0) {System.out.println("개설과정 등록이 완료되었습니다.");} 
			else {System.out.println("개설과정 등록에 실패했습니다.");}
			
		} else if (sel.equals("2")) {System.out.println("등록 메뉴로 돌아갑니다.");
			deleteOpenProcess();
		}
		
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과정 추가 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {addOpenProcess();} 
		else if (sel.equals("9")) {openCourseManagement();}
		
	}

	/**
	 * 과정 개설 시 참고할 과정 목록을 조회하는 메소드이다.
	 * 과정명, 과정기간이 포함된다.
	 */
	private static void courseList() {
		
		CourseDAO dao = new CourseDAO();
		
		ArrayList<CourseDTO> list = dao.courseList();
		
		System.out.println();
		System.out.printf("[과정목록]\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과정번호\t과정기간\t과정명\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		for (CourseDTO dto : list) {
			System.out.printf("%s\t\t%4s\t\t%s\n"
								, dto.getCourseSeq()
								, dto.getCourseTerm()
								, dto.getCourseName());
		}
		
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		
	}
	

	/**
	 * 개설 과정 수정 헤더를 출력하고 개설 과정 정보를 수정할 수 있는 메소드이다.
	 * 과정 수정 후 개설 과정 추가 수정 메뉴를 선택할 수 있다. 
	 */
	private static void editOpenProcess() {
		
		AdminView2.showHeader("개설 과정 수정");
		
		//개설과정 목록 불러오는 메소드
		showOpenCourse();

		System.out.print("수정할 개설 과정(번호): ");
		String seq = scan.nextLine();
		System.out.println();		
		
		//수정 전 개설 과정의 정보
		VwOpenCourseDTO dto = dao.get(seq);
		
		//참고할 과정 목록 보여주기
		System.out.println("등록 가능한 과정 목록을 출력합니다.");
		courseList();
		System.out.println();
			
			//수정 후 개설 과정의 정보 입력
			System.out.println("해당 개설 과정의 수정 정보 입력");
			System.out.println("(**수정하지 않는 정보에는 엔터를 입력하시오.)");
			System.out.println();
			
			System.out.print("과정번호: ");
			String courseSeq = scan.nextLine();		
			if (courseSeq.equals("")) {courseSeq = dto.getCourseSeq();}
			
			System.out.print("과정시작일(ex. 2020-12-20): ");
			String openCourseBegin = scan.nextLine();		
			if (openCourseBegin.equals("")) {openCourseBegin = dto.getOpenCourseBegin();}
			
			System.out.print("과정종료일(ex. 2020-12-20): ");
			String openCourseEnd = scan.nextLine();
			if (openCourseEnd.equals("")) {openCourseEnd = dto.getOpenCourseEnd();}
			
			System.out.print("강의실명(ex. 1강의실): ");
			String lectureRoomName = scan.nextLine();
			if (lectureRoomName.equals("")) {lectureRoomName = dto.getLectureRoomName();}
	
			
		//수정 후 개설 과정 정보
		VwOpenCourseDTO dto2 = new VwOpenCourseDTO();
		dto2.setOpenCourseSeq(seq); //수정할 개설 과정 번호
		dto2.setCourseSeq(courseSeq); //선택한 개설 과정 번호
		dto2.setOpenCourseBegin(openCourseBegin);
		dto2.setOpenCourseEnd(openCourseEnd);
		dto2.setLectureRoomName(lectureRoomName);
		
		//입력한 개설 과정의 과정명 가져오기
		VwOpenCourseDAO courseDao = new VwOpenCourseDAO();		
		dto2.setCourseName(courseDao.getCourseName(courseSeq));		

		System.out.println("아래와 같이 개설 과정을 수정합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과정 번호\t\t\t개설과정명\t\t\t\t\t과정 시작일\t과정 종료일\t강의실\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.printf("%s\t\t%-45s\t%s\t%s\t%4s\n"
							, dto2.getCourseSeq()
							, dto2.getCourseName()						
							, dto2.getOpenCourseBegin()
							, dto2.getOpenCourseEnd()
							, dto2.getLectureRoomName());

		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 수정\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");

		String sel = scan.nextLine();
		
		if (sel.equals("1")) {		
			int result = dao.edit(dto2);
			
		if (result > 0) {System.out.println("개설 과정 수정이 완료되었습니다.");}
		else {System.out.println("개설 과정 수정을 실패했습니다.");}
			
		} else if (sel.equals("2")) {System.out.println("수정 메뉴로 돌아갑니다.");
			editOpenProcess();}

		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과정 추가 수정\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {editOpenProcess();} 
		else if (sel.equals("9")) {openCourseManagement();}
		
	}
	

	
	
	/**
	 * 개설 과정 삭제 헤더를 출력하고 개설 과정을 삭제할 수 있는 메소드이다.
	 * 과정 수정 후 개설 과정 추가 삭제 메뉴를 선택할 수 있다. 
	 */
	private static void deleteOpenProcess() {

		AdminView2.showHeader("개설 과정 삭제");
		
		//개설과정 목록 보여주기
		showOpenCourse();
		System.out.println();
		
		System.out.print("삭제할 개설 과정(번호): ");
		String seq = scan.nextLine();
		
		//(삭제할)개설과정의 정보
		VwOpenCourseDTO dto = dao.get(seq);
		
		System.out.println("아래 개설 과정을 삭제합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("번호\t\t\t개설과정명\t\t\t\t\t과정 시작일\t과정 종료일\t강의실\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.printf("%s\t%-45s\t%s\t%s\t%4s\n"
							, dto.getOpenCourseSeq()
							, dto.getCourseName()						
							, dto.getOpenCourseBegin()
							, dto.getOpenCourseEnd()
							, dto.getLectureRoomName());

		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 삭제\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");

		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			
			int result = dao.delete(seq);
			
			if (result > 0) {System.out.println("개설과정 삭제가 완료되었습니다.");} 
			else {System.out.println("개설과정 삭제를 실패했습니다.");}
			
		} else if (sel.equals("2")) {System.out.println("삭제 메뉴로 돌아갑니다.");
			deleteOpenProcess();}
		
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 추가 삭제\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {deleteOpenProcess();} 
		else if (sel.equals("9")) {openCourseManagement();}
		
	}


	/**
	 * 개설 과정 수정, 삭제 시 개설 과정 목록을 출력하는 메소드이다.
	 * 개설 과정 번호, 개설 과정명, 과정 시작일, 과정 종료일, 강의실을 포함한다.
	 */
	private static void showOpenCourse() {
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t[개설과정 목록]\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

		ArrayList<VwOpenCourseDTO> list = dao.list();
		
		System.out.printf("번호\t\t\t개설과정명\t\t\t\t\t\t과정 시작일\t과정 종료일\t강의실\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for (VwOpenCourseDTO dto : list) {
			
			System.out.printf("%s\t%-45s\t\t%s\t%s\t%s\n"
								, dto.getOpenCourseSeq()
								, dto.getCourseName()
								, dto.getOpenCourseBegin()
								, dto.getOpenCourseEnd()
								, dto.getLectureRoomName());
		}		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

	}


	
}
