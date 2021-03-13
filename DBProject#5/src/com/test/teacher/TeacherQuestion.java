package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;


/**
 * 기본생성자
 * @author 다솔
 *
 */
public class TeacherQuestion {

	private static Scanner scan;
	private static TeacherQuestionDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherQuestionDAO();
	}
	
	/**
	 * 교사의 1:1질의응답 메뉴를 구현하는 클래스
	 * @param teacherNum 교사 번호(PK)
	 */
	public static void teacherQuestion(String teacherNum) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		Statement stat = null;
		CallableStatement cstat = null;
		
		
		try {
			
			
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                          1:1 질의 응답");
			System.out.print("------------------------------------------------------------------------------\n");
			
			int result = dao.question(teacherNum);
			
		} catch (Exception e) {
			System.out.println("TeacherQuestion.teacherQuestion()");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
