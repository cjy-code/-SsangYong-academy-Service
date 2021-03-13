package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.SubjectDTO;


/**
 * 과목과 관련한 기능을 담당하는 클래스이다.
 * 전체 과목 조회, 새 과목 등록, 과목 정보 수정, 과목 삭제 기능을 포함한다.
 * @author 이은규
 *
 */
public class SubjectDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	
	/**
	 * 과목 DAO의 기본 생성자이다.
	 */
	public SubjectDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}
	
	/**
	 * 전체 과목 정보를 배열로 반환하는 메소드이다.
	 * 과목의 정보에는 과목 번호, 책번호, 과목 이름, 과목 기간(일)이 포함된다.
	 * @return 과목 정보 배열
	 */
	//전체 과목 조회
	public ArrayList<SubjectDTO> subjectList() {
		
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			String sql = "select subjectSeq, bookSeq, subjectName, subjectTerm from tblSubject order by subjectSeq asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<SubjectDTO> result = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				
				SubjectDTO sdto = new SubjectDTO();
				sdto.setSubjectSeq(rs.getString("subjectSeq"));
				sdto.setBookSeq(rs.getString("bookSeq"));
				sdto.setSubjectName(rs.getString("subjectName"));
				sdto.setSubjectTerm(rs.getString("subjectTerm"));
				
				result.add(sdto);
			}
			rs.close();
			
			return result;

		} catch (Exception e) {
			System.out.println("AddressDAO.list() : " + e.toString());
		}
		
		return null;
	}
	
	/**
	 * 새 과목을 등록하는 메소드이다. 
	 * 과목 이름과 기간(일)이 담긴 과목 데이터 정보를 받아 과목을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param sdto 과목 데이터 정보
	 * @return 성공 여부
	 */
	//과목 등록
	public int addSubject(SubjectDTO sdto) {
		
		try {

			String sql = "{ call procaddSubject(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, sdto.getBookSeq());
			cstat.setString(2, sdto.getSubjectName());
			cstat.setString(3, sdto.getSubjectTerm());
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SubjectDAO.addSubject() : " + e.toString());
		}

		return 0;
	}	
	
	
	/**
	 * 수정할 과목 번호와 수정할 과목 이름 또는 교재번호 또는 과목 기간(일)을 받아와 기존 과목의 정보를 수정하는 메소드이다. 
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param subjectSeq 과목 번호 
	 * @param bookSeq 교재 번호 
	 * @param subjectName 과목 이름
	 * @param subjectTerm 과목 기간(일)
	 * @return 수정된 과목의 수 
	 */
	//과목 수정
	public int updateSubject(String subjectSeq, String getBookSeq, String getSubjectName, String getSubjectTerm) {
		
		//원래 값
		String BookSeq = "";
		String SubjectName = "";
		String SubjectTerm = "";
		
		try {
			
			String sql = "select subjectSeq, bookSeq , subjectName,  subjectTerm from tblSubject where subjectSeq = " + subjectSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				
				BookSeq = rs.getString("bookSeq");
				SubjectName = rs.getString("subjectName");
				SubjectTerm = rs.getString("subjectTerm");
			}
			
			//책번호 수정
			if (!getBookSeq.equals("")) {
				BookSeq = getBookSeq;
			}
			//과목명 수정
			if (!getSubjectName.equals("")) {
				SubjectName = getSubjectName;
			}
			
			//기간(일) 수정
			if (!getSubjectTerm.equals("")) {
				SubjectTerm = getSubjectTerm;
			}
			
			sql = "{ call procupdateSubject(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);
			cstat.setString(2, BookSeq);
			cstat.setString(3, SubjectName);
			cstat.setString(4, SubjectTerm);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.updateSubject() : " + e.toString());
		}
		
		
		return 0;
	}	
	
	
	/**
	 * 과목을 삭제하는 메소드이다.
	 * 삭제할 과목 번호를 받아 과목을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param subjectSeq 과목 번호 
	 * @return 성공 여부
	 */
	//과목 삭제
	public int deleteSubject(String subjectSeq) {
		
		try {

			String sql = "{ call procdeleteSubject(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SubjectDAO.deleteSubject() : " + e.toString());
		}
		return 0;
	}


}
