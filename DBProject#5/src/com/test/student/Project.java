package com.test.student;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * 
 * @author 최진영
 * 교육생 프로잭트 제출 및 프로젝트 피드백 조회
 */
public class Project {
	
	private static Scanner sc = new Scanner(System.in);
	private static ProjectDAO dao;
	private static String studentSeq = "34";// TEST용 교육생 번호
	
	static {
		sc = new Scanner(System.in);
		dao = new ProjectDAO();
	}




	
	/**
	 * 교육생 프로젝트 시작화면 
	 * @param seq -- 교육생 번호
	 */
	public static void start(String seq) {
		
		try {
			ArrayList<ProjectDTO> list = dao.subjectList(seq);
			boolean loop = true;
			while(loop) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("                        프로젝트");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[번호]               [과목명]");
			System.out.println("------------------------------------------------------------------------");

			for(ProjectDTO dto : list) {
				System.out.printf("  %-12s %s\n"
										   , dto.getOpensubjectseq()
										   , dto.getSubjectname());
					
										
				System.out.println(); 
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("1. 프로젝트 제출\n"
					         + "2. 피드백 조회\n"
					         + "0. 뒤로가기");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[번호를 선택해주세요]");
			System.out.print("입력: ");
			String input = sc.nextLine();
			
				if(input.equals("1")) {
					System.out.println("------------------------------------------------------------------------");
					System.out.println("[과목 번호를 선택해주세요]");
					System.out.print("입력: ");
					String  subjectSeq= sc.nextLine();
					projectSubmission(subjectSeq, seq);//과목 번호, 교육생 번호
				}else if (input.equals("2")){
					System.out.println("------------------------------------------------------------------------");
					System.out.println("[과목 번호를 선택해주세요]");
					System.out.print("입력: ");
					String  subjectSeq= sc.nextLine();
					projectFeedback(subjectSeq, seq);//과목 번호, 교육생 번호
				}else if (input.equals("0")) {
					StudentMain.studentMain(seq);
				}else {
					
				}
						
			}//while(loop)
		} catch (Exception e) {
			System.out.println("Understanding.start()");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	/**
	 * 프로젝트 피드백 출력
	 * @param Subjectseq - 과목 번호
	 * @param StudentSeq - 교육생 번호
	 */
	private static void projectFeedback(String subjectSeq, String seq) {
		try {
			ArrayList<ProjectDTO> list = dao.projectFeedback(subjectSeq);
			System.out.println("\n------------------------------------------------------------------------");
			for(ProjectDTO dto1 : list) {
				System.out.printf(" %s - %s\n", dto1.getCoursename(),dto1.getSubjectname());
				break;
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[ 팀번호]       [피드백]");
			System.out.println("------------------------------------------------------------------------");
			for(ProjectDTO dto : list) {
				System.out.printf("    %-10s %s"
										   , dto.getTeamSeq()
										   , dto.getFeedbackcontents());
				System.out.println(); 
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("------------------------------------------------------------------------");
			System.out.print("입력: ");
			String input = sc.nextLine();
			
			
			if(input.equals("0")) {
				start(seq);
			}else {
				System.out.println("잘못누르셨습니다.");
			}
			pause();
			
		} catch (Exception e) {
			System.out.println("Project.projectFeedback()");
			e.printStackTrace();
		}
		
	}


	
	
	


	/**
	 * 프로젝트 제출 목록 출력
	 * @param Subjectseq - 과목 번호
	 * @param StudentSeq - 교육생 번호
	 */
	private static void projectSubmission(String subjectSeq, String seq) {
		try {
			
			ArrayList<ProjectDTO> list = dao.projectList(subjectSeq);
//			boolean loop = true;
//			while(loop) {
			
			System.out.println("\n------------------------------------------------------------------------");
			System.out.println("                             프로젝트 리스트");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("  [번호]   [제출물 이름]         [마감일] ");
			System.out.println("------------------------------------------------------------------------");
			
			for(ProjectDTO dto : list) {
				System.out.printf("   %-7s %-10s\t %s"
										   , dto.getProjectListSeq()
										   , dto.getProjectName()
										   , dto.getProjectDeadLine());
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[[0번 입력 시 뒤로가기]]");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[제출물 번호를 선택해주세요]");
			System.out.print("입력: ");
			String projectSeq = sc.nextLine();
			if(projectSeq.equals("0")) {
				start(seq);
			}
			
			System.out.println("------------------------------------------------------------------------");
			System.out.println("[팀 번호 입력 1~6]");
			System.out.print("입력: ");
			String teamSeq = sc.nextLine();
			
			ProjectDTO dto2 = new ProjectDTO();
			dto2.setProjectListSeq(projectSeq);
			dto2.setTeamSeq(teamSeq);
			
			int result = dao.edit(dto2);
			
			if (result > 0) {
				System.out.println("제출 성공");
			} else {
				System.out.println("제출 실패");
			}
			
			pause();
		} catch (Exception e) {
			System.out.println("Project.projectSubmission()");
			e.printStackTrace();
		}
		
		
		
	}


	private static void pause() {
		System.out.println("계속하시려면 엔터를 눌러주세요");
		sc.nextLine();
	}


}
