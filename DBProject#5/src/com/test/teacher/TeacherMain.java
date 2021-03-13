package com.test.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.main.Main;


/**
 * 교사 메인을 담당하는 클래스입니다.
 * @author User
 *
 */
public class TeacherMain {
	
	/**
	 * 교사의 메인 화면을 보여주는 메소드입니다.
	 * @param seq 교사의 기본키를 받아오는 매개변수입니다.
	 */
	public static void TeacherMain(String teacherNum) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			System.out.println("--------------------------------");
			System.out.println("\t   교사메인");
			System.out.println("--------------------------------");
			System.out.println("1. 강의 스케줄 조회");
			System.out.println("2. 배점 및 시험 관리");
			System.out.println("3. 성적 관리");
			System.out.println("4. 출결 조회");
			System.out.println("5. 세부 진도 관리");
			System.out.println("6. 수업 이해도");
			System.out.println("7. 1:1 질의 응답");
			System.out.println("8. 프로젝트 관리");
			System.out.println("0. 로그아웃");
			System.out.println("--------------------------------");
			
			System.out.print("번호 선택 : ");
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				TeacherLectureScheduleMain.TeacherLectureScheduleMain();
			} else if (num.equals("2")) {
				TeacherPointExamMain.TeacherPointExamMain(teacherNum);
			} else if (num.equals("3")) {
				TeacherScore.teacherScore(teacherNum);
			} else if (num.equals("4")) {
				TeacherAttendance.teacherAttendance(teacherNum);
			} else if (num.equals("5")) {
				TeacherWeeklyProgress.teacherWeeklyProgress(teacherNum);
			} else if (num.equals("6")) {
				ArrayList<UnderstandingDTO> result = new ArrayList<UnderstandingDTO>();
				TeacherUnderstanding.teacherList(result);
			} else if (num.equals("7")) {
				TeacherQuestion.teacherQuestion(teacherNum);
			} else if (num.equals("8")) {
				TeacherProject.teacherProject(teacherNum);
			} else if (num.equals("0")){
				System.out.println("프로그램이 종료됩니다.");
				Main.main(null);
				System.out.println();
				loop = false;
			} else {
				System.out.println("잘 못 입력하셨습니다.");
			}
			
		}
		
	}

	
}






















