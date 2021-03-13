package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교사의 정보를 받아오는 클래스입니다.
 * @author User
 *
 */
public class TeacherDAO {

	/**
	 * 교사의 정보를 리스트로 정리하는 메소드입니다.
	 * @return 로그인에 교사의 정보를 리턴합니다.
	 */
	public ArrayList<TeacherDTO> teacherList() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {

			conn = DBUtil.open();
			
			String sql = "{ call procTeacher(?) }";
			
			stat = conn.prepareCall(sql);
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			ArrayList<TeacherDTO> teacherList = new ArrayList<TeacherDTO>();
			
			while (rs.next()) {
				
				TeacherDTO teacherDto = new TeacherDTO();
				
				teacherDto.setTeacherSeq(rs.getString("teacherSeq"));
				teacherDto.setTeacherName(rs.getString("teacherName"));
				teacherDto.setTeacherTel(rs.getString("teacherTel"));
				teacherDto.setTeacherSsn(rs.getString("teacherSsn"));
				
				teacherList.add(teacherDto);
				
			}
			
			return teacherList;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherlist()");
			e.printStackTrace();
		}
		
		return null;
	}

}

















