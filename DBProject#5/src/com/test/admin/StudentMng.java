package com.test.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.main.*;


import oracle.jdbc.driver.DBConversion;

/**
 * 
 * @author 최진영
 * 학생관리
 */
public class StudentMng {
	

	private static Scanner sc = new Scanner(System.in);
	private static StudentMngDAO dao;
	
	static {
		sc = new Scanner(System.in);
		dao = new StudentMngDAO();
	}
	
	

	
	
	
	
	/**
	 * 학생관리 시작 메서드
	 */
	public static void start()	{
		try {
			
			System.out.println("------------------------------------------------------------------------");
			System.out.println("                             교육생 리스트");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[번호]\t[이름]\t  [전화번호]\t[주민번호 뒷자리]\t[등록일]");
			ArrayList<StudentMngDTO> list = dao.list(null);
			
			for(StudentMngDTO dto : list) {
				System.out.printf("%4s\t%-5s %-17s %-16s %s\n"
						,dto.getSeq()
						,dto.getName()
						,dto.getTel()
						,dto.getSsn()
						,dto.getRegdate());
			}
		
			System.out.println("------------------------------------------------------------------------");
			System.out.println("1. 등록하기\n"
					         + "2. 수정하기\n"
					         + "3. 삭제하기\n"
					         + "4. 특정 교육생 조회\n"
					         + "0. 뒤로가기");
			System.out.println("------------------------------------------------------------------------");
			input();

			
		} catch (Exception e) {
			System.out.println("StudentMange.ManageList()");
			e.printStackTrace();
		}
		
		
	}//manageList
	
	
	
	
	
	
	/**
	 * 교육생 정보 등록
	 */
	private static void add() { 
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("                             교육생 등록");
		System.out.println("------------------------------------------------------------------------");
	
		System.out.print("이름: ");
		String name = sc.nextLine();
		
		System.out.print("전화번호: ");
		String tel = sc.nextLine();
		
		System.out.print("주민번호 뒷자리: ");
		String ssn = sc.nextLine();
		
		StudentMngDTO dto = new StudentMngDTO();
		dto.setName(name);
		dto.setTel(tel);
		dto.setSsn(ssn);
		
		int result = dao.add(dto);
		
		if(result ==0) {
			System.out.println("교육생 등록 성공");
			pause(0);
		}else {
			System.out.println("교육생 등록 실패");
			pause(0);
		}
	}
	
	
	
	
	/**
	 * 특정 교육생 정보 수정
	 */
	public static void edit() { 
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("                             교육생 리스트");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[번호]\t[이름]\t  [전화번호]\t[주민번호 뒷자리]\t[등록일]");
		ArrayList<StudentMngDTO> list = dao.list(null);
		
		
		for(StudentMngDTO dto : list) {
			System.out.printf("%4s\t%-5s %-17s %-16s %s\n"
					,dto.getSeq()
					,dto.getName()
					,dto.getTel()
					,dto.getSsn()
					,dto.getRegdate());
		}
	
		System.out.println("------------------------------------------------------------------------");
		System.out.print("수정할 교육생 번호: ");
		String seq = sc.nextLine();
		
		boolean loop = true; 
		while(loop) {
		StudentMngDTO dto = dao.get(seq);
			
		
			System.out.println("------------------------------------------------------------------------");
			System.out.println("                             교육생 수정");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("번호    : " + dto.getSeq());
			System.out.println("이름    : " + dto.getName());
			System.out.println("전화번호: " + dto.getTel());
			System.out.println("주민번호: "  + dto.getSsn());
			System.out.println("등록일  : "  + dto.getRegdate());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" 1.이름   2.전화번호   3.주민번호   0.뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("입력:");
			String input = sc.nextLine();
			String regdate = dto.getRegdate();
			String tel ="";
			String name ="";
			String ssn = "";
			if(input.equals("1")) {
				System.out.print("이름 : ");
				name = sc.nextLine();
				tel = dto.getTel();
				ssn = dto.getSsn();
			}else if(input.equals("2")) {
				System.out.print("전화번호 : ");
				tel = sc.nextLine();
				name = dto.getName();
				ssn = dto.getSsn();
			}else if(input.equals("3")) {
				System.out.print("주민번호 : ");
				ssn = sc.nextLine();
				tel = dto.getTel();
				name = dto.getName();
			}else if(input.equals("0")) {
				pause(0);
			}
			
			StudentMngDTO dto2 = new StudentMngDTO();
			
			dto2.setSeq(seq);
			dto2.setName(name);
			dto2.setTel(tel);
			dto2.setSsn(ssn);
			dto2.setRegdate(regdate);
			
			int result = dao.edit(dto2);

			if (result > 0) {
				System.out.println("교육생 수정 성공");
			} else {
				System.out.println("교육생 수정 실패");
			}
		}//while(loop)
	}
	
	
	
		/**
		 * 교육생 정보 삭제
		 */
	public static void delete() {
			
		System.out.println("\n[삭제할 교육생 번호]");
		System.out.print("입력: ");
		String input = sc.nextLine();
		
		int result = dao.delete(input);
		
		if (result == 0) {
			System.out.println("교육생 삭제 완료");
		}else {
			System.out.println("교육생 삭제 실패");
		}
		
		pause(0);
	}

	
	
	
	
	
	/**
	 * 특정 교육생 정보보기
	 */
	public static void certainStudent() {
		System.out.println("------------------------------------------------------------------------");
		System.out.println("                             교육생 리스트");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[번호]\t[이름]\t  [전화번호]\t[주민번호 뒷자리]\t[등록일]");
		ArrayList<StudentMngDTO> list = dao.list(null);
		
		
		for(StudentMngDTO dto : list) {
			System.out.printf("%4s\t%-5s %-17s %-16s %s\n"
					,dto.getSeq()
					,dto.getName()
					,dto.getTel()
					,dto.getSsn()
					,dto.getRegdate());
		}
	
		System.out.println("------------------------------------------------------------------------");
		System.out.print("교육생 번호: ");
		String seq = sc.nextLine();
		
		boolean loop = true;
		while(loop) {
		StudentMngDTO dto = dao.getCertain(seq);
		System.out.println("------------------------------------------------------------------------");
		System.out.println("                             교육생 정보");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("번호: " + dto.getStudentSeq());
		System.out.println("이름: " + dto.getStudentName());
		System.out.println("전화번호: " + dto.getStudentTel());
		System.out.println("주민번호: " + dto.getSsn());
		System.out.println("가입일: " + dto.getStudentRegister());
		System.out.println("과정명: " + dto.getCoursename());
		System.out.println("진행상태: " + dto.getCoursestate());
		System.out.println("과정시작: " + dto.getOpencoursebegin());
		System.out.println("과정종료: " + dto.getOpencourseend());
		System.out.println("수료 및 중도탈락 여부: " + dto.getCompletioncheck());
		System.out.println("수료 및 중도탈락 날짜: " + dto.getCompletiondate());
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[[0번 입력 시 뒤로가기]]");
		System.out.println("------------------------------------------------------------------------");

		System.out.print("입력: ");
		String input = sc.nextLine();
		
			if(input.equals("0")) {
				start();
			}else {
			System.out.println("잘못입력하셨습니다.\n");	
			}
		}//while(loop)
	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 특정 교육생 정보 조회
	 */
	private static void search() {
		System.out.println("\n[교육생 검색하기]");
		
		
		System.out.print("검색어: ");
		String word = sc.nextLine();
		
		ArrayList<StudentMngDTO> list =  dao.list(word);
		
		for (StudentMngDTO dto : list)	{
			System.out.printf("%s, %s, %s, %s\n"
					         ,dto.getSeq()
					         ,dto.getName()
					         ,dto.getTel()
					         ,dto.getSsn()
					         ,dto.getRegdate());
			
		}
		
		pause(0);
	}
	
	
	
	
	
	
	private static void input() {
		System.out.print("입력: ");
		String input = sc.nextLine();
		
		if (input.equals("1") || input.trim().equals("등록하기")) {
			add();
		} else if (input.equals("2") || input.trim().equals("수정하기")) {
			edit();
		} else if (input.equals("3") || input.trim().equals("삭제하기")) {
			delete();
		} else if (input.equals("4") || input.trim().equals("특정교육생조회")) {
			certainStudent();
		} else if (input.equals("0") || input.trim().equals("뒤로가기")) {
			AdminController.start();	
		} else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			pause(0);
		}
	}//input
	
	
	
	
	
	
	
	
	public static void pause(int num) {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("계속하시려면 엔터를 눌러주세요.");
		
		try {
			input.readLine();
			switch (num) {
			case 0:
				start();
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
