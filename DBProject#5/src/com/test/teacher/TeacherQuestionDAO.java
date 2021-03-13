package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

public class TeacherQuestionDAO {

	
	
	private Connection conn;
	private Statement stat;
	private CallableStatement cstat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public TeacherQuestionDAO() {
		
		try {
			
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO");
			e.printStackTrace();
		}
		
		
	}
	


	/**
	 * 특정 선생님이 담당하는 과정을 수강하는 학생들의 질문 목록을 불러오는 기능 구현
	 * @param teacherNum 교사 번호(PK)
	 * @return
	 */
	public int question(String teacherNum) {
		
		try {

			String sql = String.format("select \"질문번호\", \"질문제목\", \"질문내용\", \"질문날짜\", \"답변여부\", \"답변내용\", \"답변날짜\" from vwQuestion where \"선생님번호\" = %s order by \"질문날짜\" DESC", teacherNum);
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery(sql);
			
			while(rs.next()) {
				int qnum = rs.getInt("질문번호");
				String title = rs.getString("질문제목");
				String content = rs.getString("질문내용");
				String qdate = (rs.getString("질문날짜")).substring(0,10);
				String yesOrNo = rs.getString("답변여부");
//				String rcontent = rs.getString("답변내용");
//				String rdate = (rs.getString("답변날짜")).substring(0,10);
				
				System.out.printf("질문번호[%d] : %s\n%s\n%s (%s)\n\n", qnum, title, content, qdate, yesOrNo );
			}
			
			System.out.println("답변작성/수정/삭제를 원하시면 해당 질문번호를 입력해주세요: ");
			Scanner scan = new Scanner(System.in);
			
			int qqnum = scan.nextInt();
			
			System.out.println("수행할 작업의 번호를 입력해주세요.");
			System.out.println("1. 답변 작성하기");
			System.out.println("2. 답변 수정하기");
			System.out.println("3. 답변 삭제하기");
			System.out.print("번호 선택: ");
			
			int qwork = scan.nextInt();
			
			if(qwork == 1) {
				reply(qqnum, teacherNum);
			} else if (qwork == 2) {
				edit(qqnum);
			} else if (qwork == 3) {
				delete(qqnum, teacherNum);
			}
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO");
			e.printStackTrace();
		}
		return 0;
		
		
	}



	/**
	 * 선택한 질문에 답변내용을 입력하는 기능 구현
	 * @param qqnum 질문번호(PK)
	 * @param teacherNum 교사번호(PK)
	 * @throws SQLException
	 */
	private void reply(int qqnum, String teacherNum) throws SQLException {
		
		System.out.println("답변내용을 입력하고 엔터를 눌러주세요.");
		
		Scanner scan = new Scanner(System.in);
		
		String reply = scan.nextLine();
		
		try {
		
		String sql = String.format("update tblAnswer set answercontents = '%s' where questionseq = '%d'",reply,qqnum);
		String sql2 = String.format("update tblAnswer set answercheck = '답변완료' where questionseq = '%d'", qqnum);
		
		stat = conn.createStatement();
		
		stat.executeUpdate(sql);
		stat.executeUpdate(sql2);
				
		int result = stat.executeUpdate(sql);
		int result2 = stat.executeUpdate(sql2);
		
			if (result >0 && result2 > 0) {
				System.out.println("※ 답변이 성공적으로 등록되었습니다.");
				System.out.println("질문 목록으로 돌아가시려면 1번, 종료하려면 2번을 입력해주세요.");
				
				int comeback = scan.nextInt();
				
				if(comeback == 1) {
					question(teacherNum);
				} else if (comeback == 2) {
					System.out.println("프로그램을 종료합니다.");
					
				}
				
		} 
		
		} catch (Exception e) {
			System.out.println("TeacherDAO");
			e.printStackTrace();
		}
		
	}




	/**
	 * 답변이 등록된 질문을 선택해 답변내용을 수정하는 기능 구현 
	 * @param qqnum 질문번호(PK)
	 */
	private void edit(int qqnum) {
	
		System.out.println("수정할 내용을 입력하세요: ");
		
		Scanner scan = new Scanner(System.in);
		
		String edit = scan.nextLine();
		
		try {
		
		String sql = String.format("update tblAnswer set answercontents = '%s' where questionseq = '%d'", edit, qqnum );
		
		stat = conn.createStatement();
		
		int result = stat.executeUpdate(sql);
		
		
			if (result >0) {
				System.out.println("※ 답변이 성공적으로 등록되었습니다.");
		} 
		
		
		} catch (Exception e) {
			System.out.println("TeacherDAO");
			e.printStackTrace();
		}
		
	}



	/**
	 * 선택한 질문에 등록한 답변내용을 삭제하는 기능 구현
	 * @param qqnum 질문번호(PK)
	 * @param teacherNum (PK)
	 */
	private void delete(int qqnum, String teacherNum) {
		
		try {
			
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.print("삭제는 1, 취소는 2을 입력해주세요: ");
			
			Scanner scan = new Scanner(System.in);
			int deleteOk = scan.nextInt();
			
			if (deleteOk == 1) {
				
				String sql = String.format("update tblAnswer set answercontents = null where questionSeq = '%d'", qqnum);
				String sql2 = String.format("update tblAnswer set answercheck = '미답변' where questionSeq = '%d'", qqnum);
			
				stat = conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				int result2 = stat.executeUpdate(sql2);
				
				if (result > 0 && result2 > 0) {
					System.out.println("※ 선택하신 답변이 삭제되었습니다.");
				}
				
			} else if (deleteOk == 2) {
				System.out.println("삭제가 취소되었습니다.");
				System.out.print("엔터를 누르면 질문목록으로 돌아갑니다.");
				scan.nextLine();
				scan.nextLine();
				question(teacherNum);
			}
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO");
			e.printStackTrace();
		}
	}
	
}
