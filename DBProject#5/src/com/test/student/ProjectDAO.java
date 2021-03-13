package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 
 * @author 최진영
 * 프로젝트 제출 및 피드백 조회 DAO 
 */
public class ProjectDAO {

	private  Connection conn;
	private  PreparedStatement pstat;
	private  CallableStatement cstat; 
	private  Statement stat;			
	private  ResultSet rs;	

	
public ProjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * 교육생에 해당되는 과목 리스트 호출
	 * @param seq 교육생 번호
	 * @return ArrayList<ProjectDTO>
	 */
public ArrayList<ProjectDTO> subjectList(String seq) {
		
try {
		
		String sql = "call proSubject(?, ?) ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, seq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		rs = (ResultSet) cstat.getObject(2);
	
		ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
		
		while(rs.next()) {
			
			ProjectDTO dto = new ProjectDTO();
			
			dto.setOpensubjectseq(rs.getString("opensubjectseq"));
			dto.setSubjectname(rs.getString("subjectname"));
			list.add(dto);
			
		}
		
			return list;
		
	} catch (Exception e) {
		System.out.println("ExamRecordDAO.subjectList()");
		e.printStackTrace();
	}
	
	
		
		return null;
	}



	/**
	 * 특정 과목에 해당하는 프로젝트 리스트
	 * @param subjectSeq - 과목번호
	 * @return ArrayList<ProjectDTO>
	 */
	public ArrayList<ProjectDTO> projectList(String subjectSeq) {
		try {
			
			String sql = String.format("select * from tblProjectList WHERE opensubjectseq = %s ORDER BY projectlistseq", subjectSeq);
			
			rs = stat.executeQuery(sql);
			
		
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			
			while(rs.next()) {
				
				ProjectDTO dto = new ProjectDTO();
				
				dto.setProjectListSeq(rs.getString("projectListSeq"));
				dto.setOpensubjectseq(rs.getString("opensubjectseq"));
				dto.setProjectName(rs.getString("projectName"));
				dto.setProjectDeadLine(rs.getString("projectDeadLine"));
				
				list.add(dto);
				
			}
			
				return list;
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("ProjectDAO.projectList()");
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 프로젝트 제출
	 * @param dto2 
	 * @return pstat.executeUpdate();
	 */
	public int edit(ProjectDTO dto2) {
		try {
			
			String sql = "update tblProjectFile set submissionDate = sysdate, submissionState = '제출'   where projectListSeq = ? and teamSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getProjectListSeq());
			pstat.setString(2, dto2.getTeamSeq());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ProjectDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}



	/**
	 * 프로젝트 피트백 조회
	 * @param subjectSeq - 과목번호
	 * @return ArrayList<ProjectDTO>
	 */
	public ArrayList<ProjectDTO> projectFeedback(String subjectSeq) {
		
		try {
			String sql = "call procFeedBack(?, ?) ";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, subjectSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);
		
			ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
			
			while(rs.next()) {
				
				ProjectDTO dto = new ProjectDTO();
				
				dto.setCoursename(rs.getString("coursename"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setTeamSeq(rs.getString("teamSeq"));
				dto.setFeedbackcontents(rs.getString("feedbackcontents"));
				
				
				list.add(dto);
				
			}
			
				return list;
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("ProjectDAO.projectFeedback()");
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

}
