package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

public class TeacherProjectDAO {

	private Connection conn;
	private Statement stat; //매개변수x
	private PreparedStatement pstat; //매개변수o
	private CallableStatement cstat; //프로시저로 가져올 때
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public TeacherProjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.TeacherProjectDAO()");
			e.printStackTrace();
		}
		
	}

	public int add(TeacherProjectDTO dto) {
		
		try {
			
			String sql = "{ call procAddPList(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getProjectName());
			cstat.setString(4, dto.getProjectDeadLine());		
			cstat.executeQuery();
			
//			return cstat.executeUpdate();//
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.add()");
			e.printStackTrace();
		}
		
//		try {
//			
//			
//			String sql = "INSERT INTO tblProjectList (projectListSeq,openSubjectSeq,projectName,projectDeadLine) VALUES (projectListSeq.NEXTVAL, ?, ?, ?)";
//			
//			pstat = conn.prepareStatement(sql);
//			
//			pstat.setString(1, dto.getOpenSubjectSeq());
//			pstat.setString(2, dto.getProjectName());
//			pstat.setString(3, dto.getProjectDeadLine());
//			
//			
////			return pstat.executeUpdate(); //성공하면 1 실패하면 0
//			
//			
//			
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherProjectDAO.add()");
//			e.printStackTrace();
//		}
		
		
		
		
		return 0;
	}

	public int addFeedback(TeacherProjectDTO dto) {
		
		try {
			String sql = "{ call procAddFeedBack(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getProjectFileSeq());
			cstat.setString(3, dto.getTeamSeq());
			cstat.setString(4, dto.getFeedbackContents());			
			cstat.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.addFeedback()");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	public int editFeedback(TeacherProjectDTO dto) {
		
		try {
			
			String sql = "{ call procUpdateFeedBack(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getProjectFeedbackSeq());
			cstat.setString(3, dto.getFeedbackContents());		
			cstat.executeQuery();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.editFeedback()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int deleteFeedback(TeacherProjectDTO dto) {
		
		try {
			
			String sql = "{ call procDeleteFeedBack(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getProjectFeedbackSeq());	
			cstat.executeQuery();
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.deleteFeedback()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int editProjectList(TeacherProjectDTO dto) {
		
		try {
			String sql = "{ call procUpdatePList(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getProjectListSeq());
			cstat.setString(3, dto.getProjectName());		
			cstat.setString(4, dto.getProjectDeadLine());		
			cstat.executeQuery();
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.editProjectList()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int deleteProjectList(TeacherProjectDTO dto) {
		
		try {
			
			String sql = "{ call procDeletePList(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getProjectListSeq());	
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherProjectDAO.deleteProjectList()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
