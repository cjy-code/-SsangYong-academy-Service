package com.test.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.admin.AdminDTO;
import com.test.admin.AdminDAO;
import com.test.admin.AdminController;

import com.test.teacher.TeacherDAO;
import com.test.teacher.TeacherDTO;
import com.test.teacher.TeacherMain;

import com.test.teacher.*;

import com.test.student.*;

/**
 * 로그인 메인 클래스
 * @author 최진영
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("------------------------");
			System.out.println("      로그인 화면");
			System.out.println("------------------------");
			System.out.println("1. 관리자 로그인");
			System.out.println("2. 교사 로그인");
			System.out.println("3. 교육생 로그인");
			System.out.println("------------------------");
			
			System.out.print("선택 : ");
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				loginAdmin();
				
				
			} else if (num.equals("2")) {
				
				loginTeacher();
				
			} else if (num.equals("3")) {
				
				loginStudent();
				
			} else {
				
				loop = false;
				
			}
			
		}
	
	
	}
	
	/**
	 * 관리자 로그인 메소드
	 */
	private static void loginAdmin() {
		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		//홍길동, 1234567
		while (loop) {
		System.out.println();
		System.out.println("------------------");
		System.out.print("아 이 디 : ");
		String id = scan.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		System.out.println("------------------");
		
		AdminDAO adminDao = new AdminDAO();
		
		
		ArrayList<AdminDTO> adminList = adminDao.AdminList();
		for (AdminDTO adminDto : adminList) {
			
			String seq = adminDto.getAdminSeq();
			String idCheck = adminDto.getAdminName();
			String pwCheck = adminDto.getAdminSsn();
			
			if (id.equals(idCheck) && pw.equals(pwCheck)) {
				AdminController.start();
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
			main(null);
			}
		}
	}
	
	
	/**
	 * 교육생 로그인 메소드
	 */
	private static void loginTeacher() {
		
		Scanner scan = new Scanner(System.in);
		
		// 김민준, 2248567
		System.out.println();
		System.out.println("------------------");
		System.out.print("아 이 디 : ");
		String id = scan.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		System.out.println("------------------");
		
		TeacherDAO teacherDao = new TeacherDAO();
		
		ArrayList<TeacherDTO> teacherList = teacherDao.teacherList();
		
		for (TeacherDTO teacherDto : teacherList) {
			
			String seq = teacherDto.getTeacherSeq();
			String idCheck = teacherDto.getTeacherName();
			String pwCheck = teacherDto.getTeacherSsn();
			
			if (id.equals(idCheck) && pw.equals(pwCheck)) {
				TeacherMain.TeacherMain(seq);
			}
			
		}
		
	}
	
	/**
	 * 교육생 메인 메소드
	 */
	private static void loginStudent() {
		
		Scanner scan = new Scanner(System.in);
		//고결수 - 1122345.
		//전가준 - 2661407
		System.out.println();
		System.out.println("------------------");
		System.out.print("아 이 디 : ");
		String id = scan.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		System.out.println("------------------");
		
		StudentDAO studentDao = new StudentDAO();
		
		ArrayList<StudentDTO> studentList = studentDao.studentList();
		
		for (StudentDTO studentDto : studentList) {
			
			String seq = studentDto.getStudentSeq();
			String idCheck = studentDto.getStudentName();
			String pwCheck = studentDto.getStudentSsn();
			
			if (id.equals(idCheck) && pw.equals(pwCheck)) {
				
				StudentMain.studentMain(seq);
			}
			
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	

}

















