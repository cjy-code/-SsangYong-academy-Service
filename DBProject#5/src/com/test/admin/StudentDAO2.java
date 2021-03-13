package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.StudentDTO;
import oracle.jdbc.OracleTypes;

/**
 * 교육생 정보 조회 기능과 관련된 클래스이다.
 * @author 정경화
 *
 */
public class StudentDAO2 {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 학생 DAO의 기본 생성자이다.
	 */
	public StudentDAO2() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 특정 개설 과정의 교육생 정보를 조회할 수 있는 메소드이다.
	 * @param sel 개설 과정 번호
	 * @return 교육생 정보로 이름, 주민번호 뒷자리, 전화번호, 등록일, 수료 및 중도 탈락 여부를 포함한다.
	 */
	public ArrayList<StudentDTO2> list(String sel) {

		try {
			
			ArrayList<StudentDTO2> list = new ArrayList<StudentDTO2>();
			
			String sql = "{ call procSearchStudent (?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sel);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				StudentDTO2 dto = new StudentDTO2();
				
				dto.setStudentname(rs.getString("studentname"));
				dto.setStudentssn(rs.getString("studentssn"));
				dto.setStudenttel(rs.getString("studenttel"));
				dto.setStudentregister(rs.getString("studentregister"));
				dto.setCompletioncheck(rs.getString("completioncheck"));
				
				list.add(dto);
				
			}
			
				return list;		            
						            
		} catch (Exception e) {
			System.out.println("StudentDAO.list()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	
	
	
}
