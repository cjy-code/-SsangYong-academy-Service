package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.teacher.DBUtil;
import com.test.teacher.TeacherDTO;

import oracle.jdbc.OracleTypes;



/**
 * 관리자의 정보를 받아오는 클래스입니다.
 * @author 최진영
 *
 */
public class AdminDAO {

		
public ArrayList<AdminDTO> AdminList() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procAdmin(?) }";
			
			stat = conn.prepareCall(sql);
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			ResultSet rs = (ResultSet)stat.getObject(1);
			
			ArrayList<AdminDTO> adminList = new ArrayList<AdminDTO>();
			
			while (rs.next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setAdminName(rs.getString("adminName"));
				dto.setAdminSeq(rs.getString("adminSeq"));
				dto.setAdminSsn(rs.getString("adminSsn"));
				
				adminList.add(dto);
				
			}
			
			
			return adminList;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherlist()");
			e.printStackTrace();
		}
		
		return null;
	}
}
