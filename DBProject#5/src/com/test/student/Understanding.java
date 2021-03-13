package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;




/**
 * 
 * @author 최진영
 * 교육생 이해도 입력
 */
public class Understanding {

	
	private static Scanner sc = new Scanner(System.in);
	private static UnderstandingDAO dao;
	
	static {
		sc = new Scanner(System.in);
		dao = new UnderstandingDAO();
	}
	
	
	
	
	/**
	 * 교육생의 신청한 과정의 과목 출력
	 * @param seq --교육생의 번호
	 */
	public static void start(String seq) {
		
		try {
			ArrayList<UnderstandingDTO> list = dao.subjectList(seq);
			boolean loop = true;
			while(loop) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("                        수업 이해도 입력");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[번호]               [과목명]");
			System.out.println("------------------------------------------------------------------------");

			for(UnderstandingDTO dto : list) {
				System.out.printf("  %-12s %s\n"
										   , dto.getOpensubjectseq()
										   , dto.getSubjectname());
					
										
				System.out.println(); 
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[과목 번호를 선택해주세요]");
			System.out.print("입력: ");
			String input = sc.nextLine();
			
			if(input.equals("0")) {
				StudentMain.studentMain(seq);
			}else {
				insertUD(input, seq);
			}
			
			
			}//while(loop)
		} catch (Exception e) {
			System.out.println("Understanding.start()");
			e.printStackTrace();
		}
				
	}
	
	
	
	
	
	/**
	 * 주간 진도표 수업선택 후 이해도 입력
	 * @param Subjectseq -과목 번호
	 * @param StudentSeq -교육생 번호 
	 */
	public static void insertUD(String Subjectseq, String StudentSeq) {
		
		ArrayList<UnderstandingDTO> list = dao.understandingList(Subjectseq);
		boolean loop = true;
		while(loop) {
		System.out.println("\n------------------------------------------------------------------------");

		for(UnderstandingDTO dto1 : list) {
			System.out.printf("%35s\n", dto1.getSubjectname());
			break;
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[번호]    [등록날짜]  [진행률]   [수업내용]");
		System.out.println("------------------------------------------------------------------------");

		for(UnderstandingDTO dto : list) {
			System.out.printf("   %-5s  %-13s  %-6s  %s"
									   , dto.getWeeklyprogressseq()
									   , dto.getWeeklyprogressdate()
									   , dto.getSubjectprogress()
									   , dto.getLecturecontent());
			System.out.println(); 
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[[0번 입력 시 뒤로가기]]");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[입력할 번호를 선택해주세요]");
		System.out.print("입력: ");
		String input = sc.nextLine();
		
		
		if(input.equals("0")) {
			start(StudentSeq);
		}else {// 이해도 입력
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[이해도 1~10 입력]");
			System.out.print("입력: ");
			String uSeq = sc.nextLine();//이해 수치
			int result = dao.insert(Subjectseq, StudentSeq, uSeq);
			
	 		if (result == 0) {
				System.out.println("이해도 입력 완료");
			}else {
				System.out.println("이해도 입력 실패");
			}
			pause();
		}
	  }//while(loop)
		
	}
	
	
	private static void pause() {
		System.out.println("계속하시려면 엔터를 눌러주세요");
		sc.nextLine();
	}
}
