package com.test.admin;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 관리자 모드의 개설 과목 관리 클래스이다.
 * 개설 과목 등록, 개설 과목 수정, 개설 과목 삭제 기능을 포함한다. 
 * @author 정경화
 */
public class Admin_OpenSubjectView {

	private static Scanner scan;
	private static VwOpenCourseDAO dao;
	private static OpenC_OpenSubjectDAO dao2;
	private static AvailableSubjectDAO dao3;
	private static SubjectDAO sdao;
	
	static { 
			scan = new Scanner(System.in);
			dao = new VwOpenCourseDAO();
			dao2 = new OpenC_OpenSubjectDAO();	
			dao3 = new AvailableSubjectDAO();	
			sdao = new SubjectDAO();
			
	}

	
	public static void main(String[] args) {
		openSubjectManagement();
	}
	
	
	
	/**
	 * 관리자 모드의 개설 과목 관리 헤더를 출력하고 메뉴를 선택하는 메소드이다.
	 */
	public static void openSubjectManagement() {
		
		boolean loop = true;
		
		while (loop) {
			
			//개설과목관리 헤더를 출력한다.
			AdminView2.showHeader("개설 과목 관리");
			
			System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 등록\n");
			System.out.printf("\t\t\t\t\t\t\t\t2. 개설 과목 수정\n");
			System.out.printf("\t\t\t\t\t\t\t\t3. 개설 과목 삭제\n");
			System.out.printf("\t\t\t\t\t\t\t\t0. 뒤로 가기\n");
			System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.print("번호 선택: ");
			String sel = scan.nextLine();
		
			
		if (sel.equals("1")) {			
			addOpenSubject();
			
		} else if (sel.equals("2")) {			
			editOpenSubject();
			
		} else if (sel.equals("3")) {			
			deleteOpenSubject();
			
		} else if (sel.equals("0")) {			
			AdminController.start();
			
		} else if (sel.equals("9")) {
			loop = false;
			
			
		} else {
			System.out.println("잘못된 번호입니다.");
		}

	}
		
	}

	
	/**
	 * 개설 과정에 개설 과목을 등록할 수 있는 메소드이다.
	 * 과정 정보는 과목번호, 과목 시작일, 과목 종료일, 교사명을 포함한다.
	 * 과목 등록 후 개설 과목 추가 등록 메뉴를 선택할 수 있다. 
	 */
	private static void addOpenSubject() {

		AdminView2.showHeader("개설 과목 등록");
		
		//전체 개설과정 출력(등록인원 포함)
		openCourseList();
		System.out.println();
		System.out.print("개설 과목을 등록할 과정 번호(ex. 1): ");
		String courseSeq = scan.nextLine();
			
		//해당 개설 과정의 개설 과목 출력
		System.out.printf("%s과정의 개설 과목 목록을 출력합니다.\n", courseSeq);
		Admin_OpenCourseView.openSubjectList(courseSeq);
		
		//기초 데이터 과목 목록 보여주기
		subjectList();
		
		
		//등록할 개설 과목 정보 입력
			System.out.println();
			System.out.println("등록할 개설 과목 정보를 입력해주세요.(과목 목록 참고)");
			System.out.println();
	
			System.out.print("과목번호(ex. 1): ");
				String subjectSeq = scan.nextLine();	
			System.out.print("과목시작일(ex. 2020-12-20): ");
				String openSubjectBegin = scan.nextLine();		
			System.out.print("과목종료일(ex. 2020-12-20): ");
				String openSubjectEnd = scan.nextLine();
		
		//강의가능한 교사 목록
		teacherList(subjectSeq);

			System.out.print("교사명: ");
				String teacherName = scan.nextLine();
		
			
		//입력받은 개설 과목 정보를 DTO에 저장
		OpenC_OpenSubjectDTO dto = new OpenC_OpenSubjectDTO();	
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
		System.out.printf("과정 번호\t\t\t개설과목명\t\t\t\t\t과목 시작일\t과목 종료일\t교사명\n");
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
				
				if (result > 0) {System.out.println("개설 과목 등록이 완료되었습니다.");} 
				else {System.out.println("개설 과목 등록에 실패했습니다.");}
				
			} else if (sel.equals("2")) {System.out.println("등록 메뉴로 돌아갑니다.");
				addOpenSubject();}
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 추가 등록\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {addOpenSubject();} 
		else if (sel.equals("9")) {openSubjectManagement();}
		
	}

	
	
	/**
	 * 개설 과목 수정 헤더를 출력하고 특정 개설 과정의 개설 과목 정보를 수정할 수 있는 메소드이다.
	 * 수정 정보는 과목 번호, 과목 시작일, 과목 종료일, 교사명을 포함한다.
	 * 과정 수정 후 개설 과정 추가 수정 메뉴를 선택할 수 있다. 
	 */
	private static void editOpenSubject() {
		
		AdminView2.showHeader("개설 과목 수정");
		
			//전체 개설과정 출력(등록인원 포함)
			openCourseList();
			System.out.println();
			
			//개설 과정을 선택
			System.out.print("과정 선택(ex. 1): ");
			String courseSeq = scan.nextLine();
		
			//해당 개설 과정의 개설 과목 출력
			System.out.println("해당 개설 과정의 개설 과목 목록을 출력합니다.");
			Admin_OpenCourseView.openSubjectList(courseSeq);
			System.out.println();
		
			
		System.out.print("수정할 과목 번호(ex. 1): ");
		String seq = scan.nextLine();
		
		
		//수정 전 개설 과목의 정보
		OpenC_OpenSubjectDTO dto = dao2.get(seq);

		
		//참고할 과목 목록 보여주기
		System.out.println();	
		System.out.println("등록 가능한 과목 목록을 출력합니다.");
		subjectList();

		//수정할 개설 과목의 정보 입력
		System.out.println();
		System.out.println("해당 개설 과목의 수정 정보를 입력해주세요.(과목 목록 참고)");
		System.out.println("(**수정하지 않는 정보에는 엔터를 입력하시오.)");
		System.out.println();		
		
		System.out.print("과목번호(ex. 1): ");
		String subjectSeq = scan.nextLine();
		if (subjectSeq.equals("")) {subjectSeq = dto.getSubjectSeq();}	
		
		System.out.print("과목시작일(ex. 2020-12-20): ");
		String openSubjectBegin = scan.nextLine();
		if (subjectSeq.equals("")) {openSubjectBegin = dto.getOpenCourseBegin();}		
		
		System.out.print("과목종료일(ex. 2020-12-20): ");
		String openSubjectEnd = scan.nextLine();
		if (openSubjectEnd.equals("")) {openSubjectEnd = dto.getOpenSubjectEnd();}
		
		
		//강의가능한 교사 목록 보여주는 메소드
		teacherList(subjectSeq);

		System.out.print("교사명: ");
		String teacherName = scan.nextLine();		
		if (teacherName.equals("")) {teacherName = dto.getTeacherName();}
		
		//수정 후 개설 과목의 정보
		OpenC_OpenSubjectDTO dto2 = dao2.get(seq);
		dto2.setOpenSubjectSeq(seq); //수정할 개설과목 번호
		dto2.setSubjectSeq(subjectSeq);
		dto2.setOpenSubjectBegin(openSubjectBegin);
		dto2.setOpenSubjectEnd(openSubjectEnd);
		dto2.setTeacherName(teacherName);
		
		
		//선택한 과목 번호의 과목명 가져오기
		VwOpenCourseDAO courseDao = new VwOpenCourseDAO();
		dto2.setSubjectName(courseDao.getSubjectName(subjectSeq));
		
		System.out.println("아래와 같이 개설 과목을 수정합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과정 번호\t\t\t개설과목명\t\t\t\t과목 시작일\t과목 종료일\t교사명\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		System.out.printf("%s\t\t%-" + Admin_OpenCourseView.checkTitle(dto.getSubjectName(), 50) + "s\t%s\t%s\t%4s\n"
							, courseSeq
							, dto2.getSubjectName()						
							, dto2.getOpenSubjectBegin()
							, dto2.getOpenSubjectEnd()
							, dto2.getTeacherName());

		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 수정\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");

		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			
		int result = dao2.update(dto2);		
		
			if (result > 0) {System.out.println("개설 과목 수정이 완료되었습니다.");} 
			else {System.out.println("개설 과목 수정을 실패했습니다.");}
			
		} else if (sel.equals("2")) {System.out.println("수정 메뉴로 돌아갑니다.");
			editOpenSubject();}

		
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 추가 수정\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {editOpenSubject();} 
		else if (sel.equals("9")) {openSubjectManagement();}
		
	}

	
	/**
	 * 개설 과목 삭제 헤더를 출력하고 특정 개설 과정을 삭제할 수 있는 메소드이다.
	 * 과정 수정 후 개설 과정 추가 삭제 메뉴를 선택할 수 있다. 
	 */
	private static void deleteOpenSubject() {

		AdminView2.showHeader("개설 과목 삭제");
		
		//전체 개설과정 출력(등록인원 포함)
		openCourseList();
		System.out.println();
		System.out.print("개설 과정 번호(ex. 1): ");
		String courseSeq = scan.nextLine();
		
			//해당 개설 과정의 개설 과목 출력
			System.out.println("해당 개설 과정의 개설 과목 목록을 출력합니다.");
			Admin_OpenCourseView.openSubjectList(courseSeq);
		
		System.out.println();
		System.out.print("삭제할 과목번호(ex. 1): ");
		String subjectSeq = scan.nextLine();
		
		//(삭제할)개설과목의 정보
		OpenC_OpenSubjectDTO dto = dao2.get(subjectSeq);
		
		System.out.println("아래 개설 과목을 삭제합니다.");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("개설과정번호\t과목 번호\t\t과목명\t\t\t과목시작일\t\t과목종료일\t\t교사명\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("%s\t\t    %s\t\t%s\t%s\t\t%s\t\t%s\n"
							, dto.getOpenCourseSeq()
							, dto.getOpenSubjectSeq()
							, dto.getSubjectName()
							, dto.getOpenSubjectBegin()
							, dto.getOpenSubjectEnd()
							, dto.getTeacherName());
		
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 삭제\n");
		System.out.printf("\t\t\t\t\t\t\t\t2. 취소\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			
			int result = dao2.delete(subjectSeq);
			
			if (result > 0) {System.out.println("개설 과목 삭제가 완료되었습니다.");} 
			else {System.out.println("개설 과목 삭제에 실패했습니다.");}
			
		} else if (sel.equals("2")) {System.out.println("삭제 메뉴로 돌아갑니다.");
			deleteOpenSubject();}
	
		
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t1. 개설 과목 추가 삭제\n");
		System.out.printf("\t\t\t\t\t\t\t\t9. 종료\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		
		sel = scan.nextLine();
		
		if (sel.equals("1")) {deleteOpenSubject();} 
		else if (sel.equals("9")) {openSubjectManagement();}
		
	}
	

	private static void openCourseList() {

		AdminView2.showHeader("전체 개설 과정 조회");
		System.out.println();
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
	 * 개설 과목 등록, 수정 시 참고할 과목 목록을 조회하는 메소드이다.
	 * 과목번호, 교재번호, 기간, 과목명이 포함된다.
	 */
	public static void subjectList() {

		SubjectDAO dao = new SubjectDAO();
		
		ArrayList<SubjectDTO> list = dao.subjectList();
		
		System.out.println("등록 가능한 과목 목록을 출력합니다.");
		System.out.println();
		System.out.printf("[과목 목록]\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("과목번호\t교재번호\t기간\t과목명\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		for (SubjectDTO dto : list) {
			System.out.printf("%s\t\t%s\t\t%s\t%s\n"
								, dto.getSubjectSeq()
								, dto.getBookSeq()
								, dto.getSubjectTerm()
								, dto.getSubjectName());
		}
		
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();
	}
	
	
	
	/**
	 * 개설 과목 등록, 수정 시 해당 과목의 강의 가능한 교사를 보여주는 메소드이다.
	 * @param subjectSeq 과목 번호
	 */
	public static void teacherList(String subjectSeq) {

		ArrayList<AvailableSubjectDTO> teacherList = dao3.availableTeacher(subjectSeq);
		
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t\t\t\t\t\t\t[강의 가능한 교사 목록]\n");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

		for (AvailableSubjectDTO dto : teacherList) {	
			System.out.printf("%s\t\t", dto.getTeacherName());
		}
		System.out.println();	
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println();	
	}
	

	public static void pause() {
		
		System.out.println("계속 하시려면 엔터를 누르세요.");
		scan.nextLine();
		
	}
	

}
