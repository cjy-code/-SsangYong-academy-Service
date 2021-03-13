package com.test.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;






/**
 * 
 * @author 최진영
 * 시험조회 및 성적조회
 */
public class ExamRecord {
	
	private static Scanner sc = new Scanner(System.in);
	private static ExamRecordDAO dao;
	
	static {
		sc = new Scanner(System.in);
		dao = new ExamRecordDAO();
	}
	

	
	
	
	
	
	/**
	 * 시험 조회 및 성적,배점 조회 시작 메소드
	 */
	public static void start() {
		
		try {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                                    시험조회 및 성적조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-22s%-61s%-16s%-16s%-8s%s\n","[번호]","[과정명]", "[시작날짜]", "[종료날짜]", "[강의실]", "[등록인원]");
			ArrayList<ExamRecordDTO> list = dao.Courselist(null);    
			
			for(ExamRecordDTO dto : list) {
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
			System.out.println("1.시험조회");
			System.out.println("2.성적조회");
			System.out.println("3.배점조회");
			System.out.println("0.뒤로가기\n");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

			input();
			
			
		} catch (Exception e) {
			System.out.println("ExamRecord.Exam()");
			e.printStackTrace();
		}
	
	}
	
	
	
	
	/**
	 * 특정 과목 시험 조회
	 * @param num -- 과목 번호 입력
	 */
	public static void examSearch(String num) {
				
		try {
			ArrayList<ExamRecordDTO> list = dao.subjectList(num);
			boolean loop = true;
			while(loop) {
			
			
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ExamRecordDTO dto1 : list) {
				System.out.printf("%70s\n", dto1.getCourseName());
				break;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-13s %-12s %-12s %-12s %-12s %s \n", "[번호]"
													  , "[시작날짜]"
													  , "[종료날짜]" 
													  , "[개설상태]"
													  , "[시험날짜]"
													  , "[과목명]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

			for(ExamRecordDTO dto : list) {
				System.out.printf("  %-14s%-17s%-19s %-11s %-16s %s"
					
											,dto.getSubjectSeq()   
											,dto.getOpensubjectbegin()
											,dto.getOpensubjectend()
											,dto.getSubjectState()
											,dto.getExamdate()
											,dto.getSubjectname());
				System.out.println(); 
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
 
			System.out.print("입력: ");
			String input = sc.nextLine();
			
			if(input.equals("0")) {
				start();
			}else {
				System.out.println("잘못입력하셨습니다.\n");	
			}
			
		  }//while(loop)
		} catch (Exception e) {
			System.out.println("ExamRecord.examSearch()");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 성적을 보기위한 과정선택 메소드
	 * @param num
	 */
	private static void ScoreSubject(String seq) {
		try {
			ArrayList<ExamRecordDTO> list = dao.subjectList(seq);
			
			
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                                  성적 조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ExamRecordDTO dto1 : list) {
				System.out.printf("%70s\n", dto1.getCourseName());
				break;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-13s %-12s %-12s %-12s %-12s %s \n", "[번호]"
													  , "[시작날짜]"
													  , "[종료날짜]" 
													  , "[개설상태]"
													  , "[시험날짜]"
													  , "[과목명]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

			for(ExamRecordDTO dto : list) {
				System.out.printf("  %-14s%-17s%-19s %-11s %-16s %s"
					
											,dto.getSubjectSeq()   
											,dto.getOpensubjectbegin()
											,dto.getOpensubjectend()
											,dto.getSubjectState()
											,dto.getExamdate()
											,dto.getSubjectname());
				System.out.println(); 
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[[과목 번호 입력]]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
 
			System.out.print("입력: ");
			String input = sc.nextLine();
			score(input);
			
		  
		
		} catch (Exception e) {
			System.out.println("ExamRecord.ScoreSearch()");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 특정 과목에 해당되는 교육생 점수 출력
	 * @param seq -- 과목번호
	 */
	private static void score(String seq) {
		try {
			
			
			boolean loop = true;
			while(loop) {
			ArrayList<ExamRecordDTO> list = dao.subjectScore(seq);
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ExamRecordDTO dto1 : list) {
				System.out.printf("                               [%s] - %s\n", dto1.getCourseName(), dto1.getSubjectname());
				break;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("  %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s \n"
																	,"[교육생 번호]"
																	,"[이름]"
																	,"[과정기간]"
																	,"[강의실]"
																	,"[출결점수]"
																	,"[필기점수]"
																	,"[실기점수]"
																	,"[교사명]"
																	,"[교재명]"
																	,"[시험날짜]");
			
			for(ExamRecordDTO dto : list) {
				System.out.printf("       %-10s %-11s %-11s %-14s %-14s %-14s %-11s %-6s %-10s %s"
					
											,dto.getStudentSeq()
											,dto.getStudentName()
											,dto.getCourseTerm()
											,dto.getLectureroomName()
											,dto.getScoreAttendance()
											,dto.getScoreHandWriting()
											,dto.getScorePractical()
											,dto.getTeachername()
											,dto.getBooktitle()
											,dto.getExamdate()
										);
			
				System.out.println(); 
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("입력: ");
			String input = sc.nextLine();
			
			if(input.equals("0")) {
				start();
			}else {
				System.out.println("잘못입력하셨습니다.\n");	
			}
		  }//while(loop)
			
		} catch (Exception e) {
			System.out.println("ExamRecord.scoreSubject()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	/**
	 * 특정 교육생의 성적정보 출력 메소드
	 * @param seq - 특정 교육생 번호
	 */
	private static void ScoreStudent(String seq) {
		
		try {
			
			ArrayList<ExamRecordDTO> list = dao.studentScore(seq);			
			System.out.println(list.size());
			
			boolean loop = true;
			while(loop) {
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                                  성적 조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ExamRecordDTO dto1 : list) {
				System.out.printf("%78s\n", dto1.getCourseName());
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("이름: %s\n", dto1.getStudentName());
				System.out.printf("과정기간: %s\n", dto1.getCourseTerm());
				System.out.printf("교사명: %s\n", dto1.getTeachername());
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%-18s %-25s %s %s %-20s %-34s %s\n"
														, "[과목번호]"
														, "[과목명]"
														, "[출결점수]"
														, "[필기점수]"//여기 폰트 자리맞추기 
														, "[실기점수]"
														, "[교재명]"
														, "[시험날짜]");
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				for(ExamRecordDTO dto : list) {
					System.out.printf("    %-10s %-33s\t %-8s %-10s %-10s %-39s\t %s\n"
						
												,dto.getSubjectSeq()
												,dto.getSubjectname()
												,dto.getScoreAttendance()
												,dto.getScoreHandWriting()
												,dto.getScorePractical()
												,dto.getBooktitle()
												,dto.getExamdate());
				
					System.out.println(); 
				}
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("[[0번 입력 시 뒤로가기]]");
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("입력: ");
				String input = sc.nextLine();
				
				if(input.equals("0")) {
					start();
				}else {
					System.out.println("잘못입력하셨습니다.\n");	
				}
			  }//while(loop)
								
				break;
			}
						
		} catch (Exception e) {
			System.out.println("ExamRecord.ScoreStudent()");
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	/**
	 * 특정 과정에 해당하는 과목들의 배점 출력
	 * @param seq --과정번호
	 */
	public static void point(String seq) {
		
		try {
			ArrayList<ExamRecordDTO> list = dao.point(seq);
			
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                                  배점 조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ExamRecordDTO dto1 : list) {
				System.out.printf("%75s\n",  dto1.getSubjectname());
				break;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-10s %-10s %-10s %-12s %-30s %s", "[과목번호]"
													, "[출결배점]"
													, "[필기배점]"
													, "[실기배점]"
													, "[시험날짜]"
													, "[과목명]"
													, "[교재명]\n");
			
			for(ExamRecordDTO dto : list) {
				System.out.printf("    %-14s %-13s %-14s %-12s %-12s %-31s\t %s \n" 
						, dto.getOpenSubjectSeq()
						, dto.getPointattendance()
						, dto.getPointhandwriting()
						, dto.getPointpractical()
						, dto.getExamdate()
						, dto.getSubjectname()
						, dto.getBooktitle());
				
				System.out.println(); 
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("입력: ");
			String input = sc.nextLine();
			
			if(input.equals("0")) {
				start();
			}else {
				System.out.println("잘못입력하셨습니다.\n");	
			}
			
			
			
		} catch (Exception e) {
			System.out.println("ExamRecord.point()");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void input() {
		
		System.out.print("입력: ");
		String input = sc.nextLine();
		
		if (input.equals("1") || input.trim().equals("시험조회")) {
			System.out.println("[과정 번호르 선택해주세요]");
			System.out.print("입력: ");
			input = sc.nextLine();
			
			examSearch(input);
		} else if (input.equals("2") || input.trim().equals("성적조회")) {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                          성적조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("1.특정 과정 선택");
			System.out.println("2.특정 교육생 선택");
			System.out.println("0.뒤로가기");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("입력: ");
			input = sc.nextLine();
			if(input.equals("1")) {
				System.out.println("[과정 번호를 선택해주세요]");
				System.out.print("입력: ");
				input = sc.nextLine();
				ScoreSubject(input);
			}else if(input.equals("2")) {
				System.out.println("[교육생 번호를 선택해주세요]");
				System.out.print("입력: ");
				input = sc.nextLine();
				ScoreStudent(input);
			}else if(input.equals("0")) {
				start();
			}else {
				
			}
			
		} else if (input.equals("3") || input.trim().equals("배점조회")) {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                          배점조회");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[과정 번호를 선택해주세요]");
			System.out.print("입력: ");
			input = sc.nextLine();
			point(input);
				
		} else if (input.equals("0") || input.trim().equals("뒤로가기")) {
						
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
