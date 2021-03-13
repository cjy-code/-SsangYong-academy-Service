package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.SubjectDTO;
//import dto.UnderstandingDTO;

/**
 * 이해도조회와 관련한 기능을 담당하는 클래스이다.
 * 이해도조회
 * @author 이은규
 *
 */

public class UnderstandingDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	
	/**
	 * 과목 DAO의 기본 생성자이다.
	 */
	public UnderstandingDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("UnderstandingDAO.UnderstandingDAO() : " + e.toString());
		}
		
	}
	
	/**
	 * 전체 이해도조회를 배열로 반환하는 메소드이다.
	 * 이해도조회의 정보에는 이해도 조회번호, 주간진도표번호, 이해도수치가 포함된다.
	 * @return 이해도조회 정보 배열
	 */
	//전체 과목 조회
	public ArrayList<UnderstandingDTO> understandingList() {
		
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			String sql = "select * from tblUnderstanding order by understandingSeq asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<UnderstandingDTO> result = new ArrayList<UnderstandingDTO>();
			
			while(rs.next()) {
				
				UnderstandingDTO udto = new UnderstandingDTO();
				udto.setUnderstandingCheckSeq(rs.getString("understandingCheckSeq"));
				udto.setWeeklyProgressSeq(rs.getString("weeklyProgressSeq"));
				udto.setClassSeq(rs.getString("classSeq"));
				udto.setUnderstandingNum(rs.getString("understandingNum"));
				
				result.add(udto);
			}
			rs.close();
			
			return result;

		} catch (Exception e) {
			System.out.println("AddressDAO.list() : " + e.toString());
		}
		
		return null;
	}

}
