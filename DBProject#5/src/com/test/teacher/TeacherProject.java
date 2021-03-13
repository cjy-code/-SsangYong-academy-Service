package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교사의 프로젝트 관리를 위한 메인입니다.
 * @author User
 *
 */
public class TeacherProject {
	
	private static Scanner scan;
	private static TeacherProjectDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherProjectDAO();
	}
	

	/**
	 * 교사가 프로젝트를 관리하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	public static void teacherProject(String teacherNum) {
		
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		Statement stat = null;
		CallableStatement cstat = null;
		
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procCourseCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
				System.out.println();
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("                                프로젝트 관리");
				System.out.println("                          담당 개설 과정 목록 조회");
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("[개설과정번호]\t[과정명]\t                                              [과정시작일시]  [과정종료일시]   [강의실]   [학생수]   [과정진행상태]");
				
				cstat.setString(1, teacherNum); 
				cstat.registerOutParameter(2, OracleTypes.CURSOR);
				
				cstat.executeQuery();
				ResultSet rs = (ResultSet)cstat.getObject(2);

				
				while (rs.next()) {
					System.out.printf("%8s\t%-40s\t%8s\t%8s\t%-8s\t%2s\t%-4s\t\n",rs.getString("개설과정번호"),rs.getString("과정명"),rs.getString("과정시작일시"),rs.getString("과정종료일시"),rs.getString("강의실"),rs.getString("학생수"),rs.getString("과정진행상태"));
					
				}
				
				
				boolean loop = true;
				
				while (loop) {
					System.out.print("------------------------------------------------------------------------------\n");
					System.out.print("1.담당 과정 프로젝트 제출 목록 관리\n");
					System.out.print("2.담당 과정 프로젝트 현황 조회\n");
					System.out.print("3.담당 과정 프로젝트 피드백\n");
					System.out.print("0. 뒤로가기\n");
					System.out.println();
					System.out.print("선택: ");
					String num = scan.nextLine();
					System.out.println();
					
					if (num.equals("1")) {
						System.out.println();
						System.out.print("개설 과정 번호: ");
						String openCourseSeq = scan.nextLine();
						teacherPList(teacherNum, openCourseSeq);
						loop = false;
					} else if (num.equals("2")) {
						System.out.println();
						System.out.print("개설 과정 번호: ");
						String openCourseSeq = scan.nextLine();
						teacherProjectCheck(teacherNum, openCourseSeq);
						loop = false;
					} else if (num.equals("3")) {
						System.out.println();
						System.out.print("개설 과정 번호: ");
						String openCourseSeq = scan.nextLine();
						teacherFeedback(teacherNum, openCourseSeq);
						loop = false;
					} else if (num.equals("0")){
						TeacherMain.TeacherMain(teacherNum);
						
					} else {
						System.out.println("잘 못 입력하셨습니다.");
					}
				}
				
			
			
			rs.close();
//			stat.close();
			conn.close();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherProject()");
			e.printStackTrace();
		}
		
		System.out.println();
		
		
		
	}



	/**
	 * 교사가 피드백을 관리하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherFeedback(String teacherNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procFeedBackCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                 " + openCourseSeq +  "과정 프로젝트 피드백 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[피드백번호] [개설과목번호]     [과목명]\t                            [조번호]     [피드백내용]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%-40s\t%-2s\t%-40s\t\n",rs.getString("피드백번호"),rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("조번호"), rs.getString("피드백내용"));
				
			}
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.println();
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1. 특정 과목 프로젝트 피드백 관리\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println();
					System.out.print("개설 과목 번호: ");
					String openSubjectNum = scan.nextLine();
					teacherSubFeedback(teacherNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if (num.equals("0")){
					TeacherProject.teacherProject(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherFeedback()");
			e.printStackTrace();
		}
		
	}



	/**
	 * 교사가 특정 과목의 피드백을 관리할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수 입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherSubFeedback(String teacherNum, String openSubjectNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procSubFeedBackCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                 " + openSubjectNum +  "과목 프로젝트 피드백 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[피드백번호]\t[개설과목번호]\t[제출물번호]\t[조번호]\t[피드백내용]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openSubjectNum);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%8s\t%8s\t%s\t\n",rs.getString("피드백번호"),rs.getString("개설과목번호"),rs.getString("제출물번호"),rs.getString("조번호"), rs.getString("피드백내용"));
				
			}
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.println();
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1. 프로젝트 피드백 입력\n");
				System.out.print("2. 프로젝트 피드백 수정\n");
				System.out.print("3. 프로젝트 피드백 삭제\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println();
					System.out.print("제출물 번호: ");
					String projectFileNum = scan.nextLine();
					System.out.print("조번호: ");
					String teamNum = scan.nextLine();
					teacherAddFeedback(teacherNum, projectFileNum, teamNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if(num.equals("2")) {
					System.out.println();
					System.out.print("수정할 피드백 번호: ");
					String feedbackNum = scan.nextLine();
					teacherEditFeedback(teacherNum, feedbackNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if(num.equals("3")) {
					System.out.println();
					System.out.print("삭제할 피드백 번호: ");
					String feedbackNum = scan.nextLine();
					teacherDeleteFeedback(teacherNum, feedbackNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if (num.equals("0")){
					TeacherProject.teacherFeedback(teacherNum, openCourseSeq);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherSubFeedback()");
			e.printStackTrace();
		}
		
	}



	/**
	 * 교사가 피드백을 삭제할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param feedbackNum 삭제하려는 피드백 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherDeleteFeedback(String teacherNum, String feedbackNum, String openSubjectNum, String openCourseSeq) {
		
		System.out.println();
		System.out.println("[피드백 삭제]");
		System.out.println();
		
		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setProjectFeedbackSeq(feedbackNum);//
		
		
		int result = dao.deleteFeedback(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();
		TeacherProject.teacherSubFeedback(teacherNum, openSubjectNum, openCourseSeq);
		
		
	}



	/**
	 * 교사가 피드백을 수정할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param feedbackNum 수정하려는 피드백 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherEditFeedback(String teacherNum, String feedbackNum, String openSubjectNum, String openCourseSeq) {
		
		System.out.println();
		System.out.println("[피드백 수정]");
		
		System.out.print("변경할 피드백 내용: ");
		String content = scan.nextLine();
		
		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setProjectFeedbackSeq(feedbackNum);
		dto.setFeedbackContents(content);
	
		
		int result = dao.editFeedback(dto);
		System.out.println();
		System.out.println("※ 수정이 완료되었습니다.");
		pause();
		TeacherProject.teacherSubFeedback(teacherNum, openSubjectNum, openCourseSeq);
		
		
		
		
		
	}



	/**
	 * 교사가 피드백을 추가할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param projectFileNum 추가하려는 피드백의 제출물 번호를 알려주는 매개변수입니다.
	 * @param teamNum 추가하려는 피드백의 조번호를 알려주는 매개변수입니다.
	 */
	private static void teacherAddFeedback(String teacherNum, String projectFileNum, String teamNum, String openSubjectNum, String openCourseSeq) {
		
		System.out.println();
		System.out.println("[피드백 추가]");
		
		System.out.print("피드백 내용: ");
		String contents = scan.nextLine();
		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setProjectFileSeq(projectFileNum);
		dto.setTeamSeq(teamNum);
		dto.setFeedbackContents(contents);
		
		int result = dao.addFeedback(dto); //이거필수
		System.out.println();
		System.out.println("※ 추가가 완료되었습니다.");
		pause();
		TeacherProject.teacherSubFeedback(teacherNum, openSubjectNum, openCourseSeq);
		
	}



	/**
	 * 교사가 프로젝트 제출물을 조회할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherProjectCheck(String teacherNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procPFileCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                 " + openCourseSeq +  "과정 프로젝트 제출물 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[제출물번호]\t[개설과목번호]\t[과목명]\t\t\t\t\t[조번호]\t[제출물이름]\t[제출일자]\t\n");

			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%-40s\t%8s\t%-8s\t%8s\t\n",rs.getString("제출물번호"),rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("조번호"), rs.getString("제출물이름"),rs.getString("제출일자"));
				
			}
			
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.println();
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1. 특정 과목 프로젝트 현황 조회\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println();
					System.out.print("개설 과목 번호: ");
					String openSubjectNum = scan.nextLine();
					teacherSubProjectCheck(teacherNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if(num.equals("0")) {
//					TeacherMain.teacherMain(teacherNum);
					TeacherProject.teacherProject(teacherNum);
					
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherProjectCheck()");
			e.printStackTrace();
		}
	
		
		
	}



	/**
	 * 교사가 한과목의 프로젝트 제출물을 조회할 수 있는 메소드입니다.
	 * @param teacherNum 교사 게정인지를 알려주는 매개변수입니다.
	 * @param openSubjectNum 조회하는 과목의 개설 과목 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherSubProjectCheck(String teacherNum, String openSubjectNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procSubPFileCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                 " + openSubjectNum +  "과목 프로젝트 제출물 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[제출물번호]\t[개설과목번호]\t[과목명]\t\t\t\t\t[조번호]\t[제출물이름]\t[제출일자]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openSubjectNum);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%-40s\t%8s\t%-8s\t%8s\t\n",rs.getString("제출물번호"),rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("조번호"), rs.getString("제출물이름"),rs.getString("제출일자").substring(0,10));
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherSubProjectCheck()");
			e.printStackTrace();
		}
		
		pause();
		TeacherProject.teacherProjectCheck(teacherNum, openCourseSeq);
		
	}


	

	/**
	 * 교사가 프로젝트 제출 목록을 관리할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매객변수입니다.
	 * @param openCourseSeq 조회하는 과정의 개설 과정 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherPList(String teacherNum, String openCourseSeq) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procPListCheck(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                 " + openCourseSeq +  "과정 프로젝트 제출 목록 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[제출물번호]\t[개설과목번호]\t[과목명]\t\t\t\t\t[제출물이름]\t[제출마감일]\t\n");
			
			cstat.setString(1, teacherNum); 
			cstat.setString(2, openCourseSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(3);
			
			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%-40s\t%-8s\t%8s\t\n",rs.getString("제출목록번호"),rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("제출물이름"),rs.getString("제출마감일"));
				
			}
			
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.println();
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1. 제출 목록 등록\n");
				System.out.print("2. 제출 목록 수정\n");
				System.out.print("3. 제출 목록 삭제\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					System.out.println();
					System.out.print("개설 과목 번호: ");
					String openSubjectNum = scan.nextLine();
					teacherAddPList(teacherNum, openSubjectNum, openCourseSeq);
					loop = false;
				} else if (num.equals("2")) {
					System.out.println();
					System.out.print("수정할 제출 목록 번호: ");
					String pListNum = scan.nextLine();
					teacherEditPList(teacherNum, pListNum, openCourseSeq);
					loop = false;
				} else if (num.equals("3")) {
					System.out.println();
					System.out.print("삭제할 제출 목록 번호: ");
					String pListNum = scan.nextLine();
					teacherDeletePList(teacherNum, pListNum, openCourseSeq);
					loop = false;
				} else if(num.equals("0")){
					TeacherProject.teacherProject(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProject.teacherPList()");
			e.printStackTrace();
		}
		
		
	}



	/**
	 * 교사가 프로젝트 제출목록을 삭제할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param pListNum 삭제하려는 프로젝트 제출목록번호를 알려주는 매개변수입니다.
	 */
	private static void teacherDeletePList(String teacherNum, String pListNum, String openCourseSeq) {
		
		System.out.println();
		
		
			
		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setProjectListSeq(pListNum);
		
		
		int result = dao.deleteProjectList(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();
		TeacherProject.teacherPList(teacherNum, openCourseSeq);
		
	}



	/**
	 * 교사가 프로젝트 제출목록을 수정할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 * @param pListNum 수정하려는 프로젝트 제출목록번호를 알려주는 매개변수입니다.
	 */
	private static void teacherEditPList(String teacherNum, String pListNum, String openCourseSeq) {
		
		System.out.println();
		System.out.println("[제출목록 수정]");
		System.out.print("변경할 제출물이름: ");
		String plName = scan.nextLine();
		System.out.print("변경할 제출 마감일: ");
		String deadLine = scan.nextLine();
		
		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setProjectListSeq(pListNum);
		dto.setProjectName(plName);
		dto.setProjectDeadLine(deadLine);
	
		
		int result = dao.editProjectList(dto);
		System.out.println();
		System.out.println("※ 변경이 완료되었습니다.");
		pause();
		TeacherProject.teacherPList(teacherNum, openCourseSeq);
		
	}



	/**
	 * 교사가 프로젝트 제출목록을 추가할 수 있는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매객변수입니다.
	 * @param openSubjectNum 추가하려는 제출목록의 개설 과목 번호를 알려주는 매개변수입니다.
	 */
	private static void teacherAddPList(String teacherNum, String openSubjectNum, String openCourseSeq) {
		
		
		System.out.println();
		System.out.println("[제출목록 추가]");

		
		System.out.print("제출물 이름: ");
		String listName = scan.nextLine();

		System.out.print("제출 마감일: ");
		String deadLine = scan.nextLine();

		
		TeacherProjectDTO dto = new TeacherProjectDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(openSubjectNum);
		dto.setProjectName(listName);
		dto.setProjectDeadLine(deadLine);
		
		int result = dao.add(dto);
		System.out.println();
		System.out.println("※ 추가가 완료되었습니다.");
		pause();
		TeacherProject.teacherPList(teacherNum, openCourseSeq);
		
	}

	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause
	
}
