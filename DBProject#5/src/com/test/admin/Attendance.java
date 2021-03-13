package com.test.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 최진영
 * 출결조회
 */
public class Attendance {
	private static Scanner sc = new Scanner(System.in);
	private static AttendanceDAO dao;
	
	static {
		sc = new Scanner(System.in);
		dao = new AttendanceDAO();
	}
	

	
	
	/**
	 * Attendance 시작부분
	 */
	public static void AttendanceMain() {
		
		try {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                                       출결조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-22s%-61s%-16s%-16s%-8s%s\n","[번호]","[과정명]", "[시작날짜]", "[종료날짜]", "[강의실]", "[등록인원]");
			ArrayList<AttendanceDTO> list = dao.AttendaceList();
			
			for(AttendanceDTO dto : list) {
				System.out.printf("  %-7s %-59s\t%-20s%-20s%-12s%s"
						,dto.getOpenCourseSeq()
						,dto.getCourseName()
						,dto.getOpenCourseBegin()
						,dto.getOpencourseEnd()
						,dto.getLectureroomName()
						,dto.getLectureroomNumber());
		       System.out.println();
			
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[특정 과정번호 입력]");
			System.out.print("입력: ");
			String input = sc.nextLine();
			if(input.equals("0")) {// 뒤로가기
				AdminController.start();
			}
			subjectAttendanceList(input);// 특정 과정번호 입력
			
		} catch (Exception e) {
			System.out.println("Attendance.AttendanceMain()");
			e.printStackTrace();
		}
		
	}//AttendanceMain
	
	
	
	
	
	
	/**
	 * 특정과정의 모든 교육생 출결정보 출력
	 * @param - 특정과정 번호 
	 */
	public static void subjectAttendanceList(String seq) {
		
		try {
			ArrayList<AttendanceDTO> list = dao.courseAttendace(seq);
			
			System.out.println("\n\n------------------------------------------------------------------------------------");
			for(AttendanceDTO dto1 : list) {
				System.out.printf("%45s\n", dto1.getCourseName());
				break;
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("%s  %-7s %-12s %-20s %s\n", "[교육생 번호]"
													  , "[이름]"
													  , "[출결상태]"
													  , "[입실날짜]"
													  , "[퇴실날짜]");
			System.out.println("------------------------------------------------------------------------------------");
			for(AttendanceDTO dto : list) {
				System.out.printf("     %-9s %-8s %-8s %-23s %s"
													, dto.getStudentSeq()
													, dto.getStudentName()
													, dto.getAttendanceState()
													, dto.getAttendanceenterdate()
													, dto.getAttendanceExitDate());
				System.out.println();
				
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println(" 1.특정 교육생 조회");
			System.out.println(" 2.특정 기간 조회");
			System.out.println(" 0.뒤로가기");
			System.out.println("------------------------------------------------------------------------------------");
			input(seq);
		} catch (Exception e) {
			System.out.println("Attendance.subjectAttendanceList()");
			e.printStackTrace();
		}
	}
	
	
	
	
	

	
	/**
	 * 과목별 출결 정보
	 * @param seq  - 특정 과정 번호 
	 * @param studentSeq - 특정 교육생 번호
	 */
	public static void subjectAttendanceList(String seq, String studentSeq) {
		
		try {
			ArrayList<AttendanceDTO> list = dao.courseAttendace(seq);
			System.out.println("\n\n------------------------------------------------------------------------------------");
			for(AttendanceDTO dto1 : list) {
				System.out.printf("%45s\n", dto1.getCourseName());
				break;
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("%s  %-7s %-12s %-20s %s\n", "[교육생 번호]"
													  , "[이름]"
													  , "[출결상태]"
													  , "[입실날짜]"
													  , "[퇴실날짜]");
			System.out.println("------------------------------------------------------------------------------------");

			for(AttendanceDTO dto : list) {
				
				if(dto.getStudentSeq().equals(studentSeq)) {
				System.out.printf("     %-9s %-8s %-8s %-23s %s"
													, dto.getStudentSeq()
													, dto.getStudentName()
													, dto.getAttendanceState()
													, dto.getAttendanceenterdate()
													, dto.getAttendanceExitDate());
				System.out.println();
				}
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("------------------------------------------------------------------------------------");
			
			Boolean loop = true;
			while(loop) {
				System.out.print("입력: ");
				String input = sc.nextLine();
				
				if(input.equals("0")) {
					AttendanceMain();
				}else {
					System.out.println("잘못입력하셨습니다.\n");
					pause(2,seq);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Attendance.subjectAttendanceList()");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	/**
	 * 특정과정의 특정 기간 출석정보 조회
	 * @param seq - 특정 과정 번호
	 * @param start - 시작 기준 날짜
	 * @param end   - 종료 기준 날짜
	 */
public static void subjectAttendanceDate(String seq, String start, String end) {
		
		try {
			ArrayList<AttendanceDTO> list = dao.courseAttendace(seq,start,end);
			System.out.println("\n\n------------------------------------------------------------------------------------");
			for(AttendanceDTO dto1 : list) {
				System.out.printf("%45s\n", dto1.getCourseName());
				break;
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("%s  %-7s %-12s %-20s %s\n", "[교육생 번호]"
													  , "[이름]"
													  , "[출결상태]"
													  , "[입실날짜]"
													  , "[퇴실날짜]");
			System.out.println("------------------------------------------------------------------------------------");

			for(AttendanceDTO dto : list) {
//				String strDate = dto.getAttendanceenterdate();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//				Date date2 = sdf.parse(strDate);

				System.out.printf("     %-9s %-8s %-8s %-23s %s"
													, dto.getStudentSeq()
													, dto.getStudentName()
													, dto.getAttendanceState()
													, dto.getAttendanceenterdate()
													, dto.getAttendanceExitDate());
				System.out.println();
				
			}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("------------------------------------------------------------------------------------");
			
			Boolean loop = true;
			while(loop) {
				System.out.print("입력: ");
				String input = sc.nextLine();
				
				if(input.equals("0")) {
					AttendanceMain();
				}else {
					System.out.println("잘못입력하셨습니다.\n");
					pause(2,seq);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Attendance.subjectAttendanceList()");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * subjectAttendanceList()메소드의 입력 메소드
	 * @param seq 과정번호
	 */
	public static void input(String seq) {
		
		System.out.print("입력: ");
		String input = sc.nextLine();
		
		if (input.equals("1")) {// 특정 교육생 출결정보 출력
			System.out.println("\n[교육생 번호를 입력]");
			System.out.print("입력: ");
			input = sc.nextLine();
			subjectAttendanceList(seq, input);	
			
		} else if (input.equals("2")) {//특정 과정의 기간 
			System.out.println("\n[과정 기간 입력]");
			System.out.print("시작날짜: ");
			String start = sc.nextLine();
			System.out.print("종료날짜: ");
			String end = sc.nextLine();
			
			subjectAttendanceDate(seq, start, end);
			
		} else if (input.equals("0")) {
			AttendanceMain();
		} else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			pause(0,"0");
		}
	}
	
	
	
	 
	
	
	
	/**
	 * 
	 * @param num - Switch선택
	 * @param seq - 과정번호
	 */
	public static void pause(int num, String seq) {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("계속하시려면 엔터를 눌러주세요.");
		
		try {
			input.readLine();
			switch (num) {
			case 0:
				AttendanceMain();
			case 1:
				

			case 2:
				
			case 3:
				
			default:
				
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // pause
	
	
	

}
