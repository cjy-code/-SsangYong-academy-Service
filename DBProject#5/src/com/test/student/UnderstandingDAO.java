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
 * 교육생 이해도 입력 DAO
 */
public class UnderstandingDAO {
	
	private  Connection conn;
	private  PreparedStatement pstat;
	private  CallableStatement cstat; 
	private  Statement stat;			
	private  ResultSet rs;	

	
public UnderstandingDAO() {
		
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
 * @return ArrayList<UnderstandingDTO>
 */
public ArrayList<UnderstandingDTO> subjectList(String seq) {
	try {
		
		String sql = "call proSubject(?, ?) ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, seq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		rs = (ResultSet) cstat.getObject(2);
	
		ArrayList<UnderstandingDTO> list = new ArrayList<UnderstandingDTO>();
		
		while(rs.next()) {
			
			UnderstandingDTO dto = new UnderstandingDTO();
			
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
 * 주간 진도표 불러오기
 * @param seq - 과목번호
 * @return ArrayList<UnderstandingDTO>
 */
public ArrayList<UnderstandingDTO> understandingList(String seq) {
	
	try {
		
		String sql = "call procWeeklyProgressUst(?, ?) ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, seq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		rs = (ResultSet) cstat.getObject(2);
	
		ArrayList<UnderstandingDTO> list = new ArrayList<UnderstandingDTO>();
		
		while(rs.next()) {
			
			UnderstandingDTO dto = new UnderstandingDTO();
			
			dto.setWeeklyprogressseq(rs.getString("weeklyprogressseq"));
			dto.setWeeklyprogressdate(rs.getString("weeklyprogressdate"));
			dto.setLecturecontent(rs.getString("lecturecontent"));
			dto.setSubjectprogress(rs.getString("subjectprogress"));
			dto.setSubjectname(rs.getString("subjectname"));
			
			
			list.add(dto);
			
		}
		
			return list;
		
		
		
		
		
	} catch (Exception e) {
		System.out.println("UnderstandingDAO.understandingList()");
		e.printStackTrace();
	}
	
	
	return null;
}






/**
 * 이해도 입력
 * @param subjectseq -- 과목 번호
 * @param studentSeq -- 교육생 번호
 * @param uSeq       -- 이해도 수치
 * @return cstat.executeUpdate();
 */
public int insert(String subjectseq, String studentSeq, String uSeq) {
	try {
		
		String sql = "call procUdstandingInsert(?, ?, ?) ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, subjectseq);
		cstat.setString(2, studentSeq);
		cstat.setString(3, uSeq);
		
		
		return cstat.executeUpdate();
		
		
		
	} catch (Exception e) {
		System.out.println("UnderstandingDAO.insert()");
		e.printStackTrace();
	}
	
	
	
	return 0;
}








}
