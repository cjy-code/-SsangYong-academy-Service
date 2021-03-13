package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교육생 모드의 1:1 질의응답과 관련된 기능을 포함하는 클래스이다.
 * 질문 등록, 조회, 수정, 삭제 기능을 포함한다.
 * @author 정경화
 *
 */
public class Student_QuestionView {

	private static Scanner scan;
	private static QuestionDAO dao;
	
	/**
	 * 교육생 1:1 질의응답 DAO의 기본 생성자이다.
	 */
	static {
			scan = new Scanner(System.in);
			dao = new QuestionDAO();
	}
	
//	public static void main(String[] args) {
//		questionManagement(seq);
//	}
	
	/**
	 * 교육생 모드의 1:1 질의응답 헤더를 출력하고 메뉴를 선택하는 메소드이다.
	 */
	public static void questionManagement(String seq) {
		
		StudentDTO dto = StudentDAO.get(seq);
			
		boolean loop = true;
		
		while (loop) {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t\t[1:1 질의응답]\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t\t1. 질문 등록\n");
		System.out.printf("\t\t\t\t2. 질문 조회\n");
		System.out.printf("\t\t\t\t3. 질문 수정\n");
		System.out.printf("\t\t\t\t4. 질문 삭제\n");
		System.out.printf("\t\t\t\t0. 뒤로 가기\n");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────");
		System.out.print("번호 선택: ");
		String sel = scan.nextLine();
		
		switch (sel) {
		
		case "1" :
			addQuestion(dto.getClassSeq());
			break;
		case "2" :
			QuestionListHeader(dto.getClassSeq());
			break;
		case "3" :
			editQuestion(dto.getClassSeq());
			break;
		case "4" :
			deleteQuestion(dto.getClassSeq());
			break;
		case "0" :
			StudentMain.studentMain(seq);

		}	
	}

}

	/**
	 * 질문 등록 헤더를 출력하고 수강 신청 번호로 질문을 등록할 수 있는 메소드이다.
	 * 제목과 내용을 입력할 수 있다.
	 */
	private static void addQuestion(String classSeq) {

		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t\t[1. 질문 등록]\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("제목 : ");
		String questionTitle = scan.nextLine();
		
		System.out.print("내용 : ");
		String questionContents = scan.nextLine();
		
		QuestionDTO dto = new QuestionDTO();
		dto.setClassSeq(classSeq);
		dto.setQuestionTitle(questionTitle);
		dto.setQuestionContents(questionContents);
		
		int result = dao.addQuestion(dto);
		
		System.out.println();
		
		if (result > 0) {
			System.out.println("질문을 등록했습니다.");
		} else {
			System.out.println("질문 등록에 실패했습니다.");
		}
		
		pause();

	}

	

	/**
	 * 질문 조회 헤더를 출력하고 등록한 질문 목록을 조회할 수 있는 메소드이다. 
	 */
	private static void QuestionListHeader(String classSeq) {

		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("\t\t\t\t[2. 질문 조회]\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		QuestionList(classSeq);
		
		pause();
		
	}

	
	/**
	 * 질문 수정 헤더를 출력하고 등록한 질문을 수정할 수 있는 메소드이다.
	 * 질문 번호를 선택해 제목과 내용을 수정할 수 있다.
	 */
	private static void editQuestion(String classSeq) {
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t\t[3. 질문 수정]\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		QuestionList(classSeq);
		
		System.out.print("수정할 번호 입력 : ");
		String sel = scan.nextLine(); //questionSeq
		System.out.println();
		
		QuestionDTO dto = dao.get(sel);
		
		System.out.println("수정 정보 입력");
		System.out.println("(**수정하지 않는 정보에는 엔터를 입력하시오.)");
		System.out.println();
	
		System.out.print("제목 : ");
		String questionTitle = scan.nextLine();
		
		if (questionTitle.equals("")) {
			questionTitle = dto.getQuestionTitle();
		}
		
		System.out.print("내용 : ");
		String questionContents = scan.nextLine();
		
		if (questionContents.equals("")) {
			questionContents = dto.getQuestionContents();
		}
		
		QuestionDTO dto2 = new QuestionDTO();
		dto2.setQuestionSeq(sel);
		dto2.setQuestionTitle(questionTitle);
		dto2.setQuestionContents(questionContents);
		
		int result = dao.update(dto2);

		if (result > 0) {
			System.out.println("질문 수정이 완료되었습니다.");
		} else {
			System.out.println("질문 수정에 실패했습니다.");
		}
		
		pause();
		
	}

	
	/**
	 * 질문 삭제 헤더를 출력하고 등록한 질문을 삭제할 수 있는 메소드이다.
	 * 질문 번호를 선택해 질문을 삭제할 수 있다.
	 */
	private static void deleteQuestion(String classSeq) {
		
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("\t\t\t\t[4. 질문 삭제]\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		QuestionList(classSeq);
		
		System.out.print("삭제할 번호 입력 : ");
		String sel = scan.nextLine();
		
		int result = dao.delete(sel);
		
		if (result > 0) {
			System.out.println("질문을 삭제했습니다.");
		} else {
			System.out.println("질문 삭제에 실패했습니다.");
		}

		pause();

	}

	
	
		/**
		 * 질문 조회, 수정, 삭제 시 등록된 질문 목록을 보여주는 메소드이다.
		 * 질문 정보로 질문 번호, 날짜, 답변여부, 제목, 내용을 포함한다.
		 */
		public static void QuestionList(String classSeq) {
			
			ArrayList<QuestionDTO> list = dao.QuestionList(classSeq); //수강번호 전달(classSeq)
			
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────");			
			System.out.printf("[번호]\t[날짜]\t\t[답변 여부]\t[제목]\t\t[내용]\n");
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────");
			
			for (QuestionDTO dto : list) {
				
				System.out.printf("%s\t%s\t%-5s\t%-10s\t%s\n"
									, dto.getQuestionSeq()
									, dto.getQuestionDate()
									, dto.getAnswerCheck()
									, dto.getQuestionTitle()
									, dto.getQuestionContents());

			}
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println();


		}


		private static void pause() {
		System.out.println("계속 하시려면 엔터를 누르세요.");
		scan.nextLine();
	}
		
		
}
