package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 학생의 세부진도표 메인을 담당하는 클래스입니다.
 * @author User
 *
 */
public class StudentWeeklyProgressMain {
	
	/**
	 * 세부 진도표 메인 화면을 담당하는 메소드입니다.
	 * @param studentSeq 로그인할때 받아오는 학생의 기본키입니다.
	 */
	public static void StudentWeeklyProgressMain(String studentSeq) {
		
		StudentWeeklyProgressDAO dao = new StudentWeeklyProgressDAO();
		
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t   나의 강의");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("  [날짜]\t[진행률]\t[수업내용]");
		
		ArrayList<StudentWeeklyProgressDTO> weeklyProgressList = dao.weeklyProgressList(studentSeq);
		
		for (StudentWeeklyProgressDTO weeklyProgressDto : weeklyProgressList) {
			
			System.out.printf("%s\t%s\t\t%s\n"
							, weeklyProgressDto.getWeeklyProgressDate()
							, weeklyProgressDto.getSubjectProgress() + "%"
							, weeklyProgressDto.getLectureContent());
			
		}
		
		System.out.println("------------------------------------------------------------------------------------");
		
		pause();
		
	}
	
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("메뉴로 돌아가시려면 엔터를 누르세요.");
		scan.nextLine();
	}

}
