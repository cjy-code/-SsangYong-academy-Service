package com.test.student;

import java.util.Scanner;

/**
 * 학생 메인 화면 클래스입니다.
 * @author User
 *
 */
public class StudentMain {
	
	/**
	 * 메인 화면을 담당하는 메소드입니다.
	 * @param seq
	 */
	public static void studentMain(String seq) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("-------------------------------");
			System.out.println("\t   학생 메인");
			System.out.println("-------------------------------");
			System.out.println("1. 성적 조회");
			System.out.println("2. 출결 관리 및 출결 조회");
			System.out.println("3. 세부 진도 조회");
			System.out.println("4. 수업 이해도 입력");
			System.out.println("5. 1:1 질의 응답");
			System.out.println("6. 프로젝트 관리");
			System.out.println("-------------------------------");
			System.out.print("선택 : ");
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				int number = Integer.parseInt(seq);
				StudentElseDAO.checkingGrade(number);
				
			} else if(num.equals("2")) {
				int number = Integer.parseInt(seq);
				StudentElseDAO.attendance(number);
			} else if(num.equals("3")) {
				StudentWeeklyProgressMain.StudentWeeklyProgressMain(seq);
			} else if(num.equals("4")) {
				Understanding.start(seq);
			} else if(num.equals("5")) {
				Student_QuestionView.questionManagement(seq);
			} else if(num.equals("6")) {
				Project.start(seq);
			} else {
				loop = false;
			}
			
		}
		
		
		
		
	}

}
