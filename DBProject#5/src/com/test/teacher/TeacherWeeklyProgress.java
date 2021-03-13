package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

import oracle.jdbc.OracleTypes;
/**
 * 주간 진도표를 관리하는 메인입니다.
 * @author User
 *
 */
public class TeacherWeeklyProgress {

	private static Scanner scan;
	private static TeacherWeeklyProgressDAO dao;
	
	static {
		scan = new Scanner(System.in);
		dao = new TeacherWeeklyProgressDAO();
	}
	

	
	/**
	 * 주간 진도표를 관리하는 메서드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	public static void teacherWeeklyProgress(String teacherNum) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		
			
		boolean loop = true;
		
			while (loop) {
		
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("                          주간 진도표 관리");
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("1. 진행중인 과정 주간 진도 관리");
				System.out.println("2. 종료된 과정 주간 진도 조회");
				System.out.println("0. 뒤로가기");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					teacherWPManagement(teacherNum);
					loop = false;
				} else if (num.equals("2")) {
					teacherEndWPCheck(teacherNum);
					loop = false;
				} else if(num.equals("0")){
					TeacherMain.TeacherMain(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			
			}
			
			System.out.println();
	
		
		
		
	}




	/**
	 * 종료된 주간 진도표를 조회하는 메서드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	private static void teacherEndWPCheck(String teacherNum) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procEndWpCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
				
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("                          종료된 주간 진도표 조회");
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.println("[개설과정번호]\t[개설과목번호]\t[수업날짜]\t[수업내용]\t[과목별진행률]");
				
				cstat.setString(1, teacherNum); 
				cstat.registerOutParameter(2, OracleTypes.CURSOR);
				
				cstat.executeQuery();
				ResultSet rs = (ResultSet)cstat.getObject(2);

				
				while (rs.next()) {
					System.out.printf("%8s\t%8s\t%8s\t%-60s\t%8s\t\n",rs.getString("개설과정번호"),rs.getString("개설과목번호"),rs.getString("수업날짜"),rs.getString("수업내용"),rs.getString("과목별진행률"));
					
				}
				
				rs.close();
				cstat.close();
				conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgress.teacherEndWPCheck()");
			e.printStackTrace();
		}
		
		System.out.println();
		pause();
		TeacherWeeklyProgress.teacherWeeklyProgress(teacherNum);
		
		
	}




	/**
	 * 주간 진도표를 관리하는 메서드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	private static void teacherWPManagement(String teacherNum) {
		
		TeacherDTO dto = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procWpCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                          주간 진도표 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[개설과정번호]\t[개설과목번호]\t[수업날짜]\t[수업내용]\t                                                  [과목별진행률]");
			
			cstat.setString(1, teacherNum); 
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(2);

			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%8s\t%-60s\t%8s\t\n",rs.getString("개설과정번호"),rs.getString("개설과목번호"),rs.getString("수업날짜"),rs.getString("수업내용"),rs.getString("과목별진행률"));
				
			}
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print("1.주간 진도 등록\n");
				System.out.print("2.주간 진도 수정\n");
				System.out.print("3.주간 진도 삭제\n");
				System.out.print("0. 뒤로가기\n");
				System.out.println();
				System.out.print("선택: ");
				String num = scan.nextLine();
				System.out.println();
				
				if (num.equals("1")) {
					wpAdd(teacherNum);
//					loop = false;
				} else if (num.equals("2")) {
					wpEdit(teacherNum);
//					loop = false;
				} else if (num.equals("3")) {
					wpDelete(teacherNum);
//					loop = false;
				} else if (num.equals("0")) {
//					TeacherMain.teacherMain(teacherNum);
					TeacherWeeklyProgress.teacherWeeklyProgress(teacherNum);
				} else {
					System.out.println("잘 못 입력하셨습니다.");
				}
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgress.teacherWPManagement()");
			e.printStackTrace();
		}
		
		
		
	}




	/**
	 * 주간진도표를 삭제하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	private static void wpDelete(String teacherNum) {
		
		TeacherDTO dto2 = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procWpCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                          주간 진도표 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[주간진도표번호] [개설과정번호]\t[개설과목번호]\t[수업날짜]\t[수업내용]\t                                                  [과목별진행률]");
			
			cstat.setString(1, teacherNum); 
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(2);

			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%8s\t%8s\t%-60s\t%8s\t\n",rs.getString("주간진도표번호"), rs.getString("개설과정번호"),rs.getString("개설과목번호"),rs.getString("수업날짜"),rs.getString("수업내용"),rs.getString("과목별진행률"));
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgress.wpDelete()");
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("[주간진도 삭제]");
		
		System.out.print("삭제할 주간진도표 번호: ");
		String wpNum = scan.nextLine();
		
		
		TeacherWeeklyProgressDTO dto = new TeacherWeeklyProgressDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setWeeklyProgressSeq(wpNum);//
		
		
		int result = dao.delete(dto);
		System.out.println();
		System.out.println("※ 삭제가 완료되었습니다.");
		pause();
		TeacherWeeklyProgress.teacherWPManagement(teacherNum);
		
	}




	/**
	 * 주간 진도표를 수정하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	private static void wpEdit(String teacherNum) {
		
		TeacherDTO dto2 = new TeacherDTO();
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procWpCheck(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("                          주간 진도표 조회");
			System.out.print("------------------------------------------------------------------------------\n");
			System.out.println("[주간진도표번호] [개설과정번호]\t[개설과목번호]\t[수업날짜]\t[수업내용]\t                                                  [과목별진행률]");
			
			cstat.setString(1, teacherNum); 
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(2);

			
			while (rs.next()) {
				System.out.printf("%8s\t%8s\t%8s\t%8s\t%-60s\t%8s\t\n",rs.getString("주간진도표번호"), rs.getString("개설과정번호"),rs.getString("개설과목번호"),rs.getString("수업날짜"),rs.getString("수업내용"),rs.getString("과목별진행률"));
				
			}
			
			rs.close();
			cstat.close();
			conn.close();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherWeeklyProgress.teacherWPManagement()");
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("[주간진도 수정]");
		
		System.out.print("수정할 주간진도표 번호: ");
		String wpNum = scan.nextLine();
		System.out.print("변경할 수업내용: ");
		String content = scan.nextLine();
		
		
		TeacherWeeklyProgressDTO dto = new TeacherWeeklyProgressDTO();
		dto.setTeacherSeq(teacherNum);//
		dto.setWeeklyProgressSeq(wpNum);//
		dto.setLectureContent(content);
		
		int result = dao.edit(dto);
		System.out.println();
		System.out.println("※ 수정이 완료되었습니다.");
		pause();
		TeacherWeeklyProgress.teacherWPManagement(teacherNum);
		
	}




	/**
	 * 주간진도표를 추가하는 메소드입니다.
	 * @param teacherNum 교사 계정인지를 알려주는 매개변수입니다.
	 */
	private static void wpAdd(String teacherNum) {
		
		System.out.println();
		System.out.println("[주간진도 추가]");
		
		System.out.print("개설과목번호: ");
		String osNum = scan.nextLine();
		
		System.out.print("수업날짜: ");
		String date = scan.nextLine();

		System.out.print("수업내용: ");
		String contents = scan.nextLine();
		
		System.out.print("과목별진행률: ");
		String progress = scan.nextLine();
		
		TeacherWeeklyProgressDTO dto = new TeacherWeeklyProgressDTO();
		dto.setTeacherSeq(teacherNum);
		dto.setOpenSubjectSeq(osNum);
		dto.setWeeklyProgressDate(date);
		dto.setLectureContent(contents);
		dto.setSubjectProgress(progress);
		
		int result = dao.add(dto); 
		System.out.println();
		System.out.println("※ 추가가 완료되었습니다.");
		pause();
		TeacherWeeklyProgress.teacherWPManagement(teacherNum);
		
		
		
		
		
		
	}
	
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 엔터를 눌러주세요.");
		scan.nextLine();
		
	} //pause
	
	

}
