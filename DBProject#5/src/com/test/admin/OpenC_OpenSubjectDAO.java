package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.OpenC_OpenSubjectDTO;
import oracle.jdbc.OracleTypes;

/**
 * 개설 과정의 개설 과목 관리 기능과 관련된 클래스이다.
 * 특정 개설과정 조회, 개설 과목 추가, 개설 과목 수정, 개설 과목 삭제 기능이 포함된다.
 * 
 * @author 정경화
 *
 */
public class OpenC_OpenSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 개설과정 개설과목 DAO의 기본 생성자이다.
	 */
	public OpenC_OpenSubjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 개설 과정 번호를 입력하면 해당 개설 과정의 개설 과목 정보를 반환하는 메소드이다.
	 * @param sel 개설 과정 번호
	 * @return 개설 과목 정보로 과정명, 개설 과목 번호, 과목명, 과목 시작일, 과목 종료일, 교재명, 교사명을 포함한다.
	 */
	public ArrayList<OpenC_OpenSubjectDTO> list(String sel) {

		try {
			
			ArrayList<OpenC_OpenSubjectDTO> list = new ArrayList<OpenC_OpenSubjectDTO>();
			
			String sql = "{ call procSreachCourse (?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sel);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				OpenC_OpenSubjectDTO dto = new OpenC_OpenSubjectDTO();
				
				dto.setCourseName(rs.getString("courseName"));
				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setOpenSubjectBegin(rs.getString("openSubjectBegin"));
				dto.setOpenSubjectEnd(rs.getString("openSubjectEnd"));
				dto.setBookTile(rs.getString("bookTitle"));
				dto.setTeacherName(rs.getString("teacherName"));
				
				list.add(dto);
	
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenC_OpenSubjectDAO.list()");
			e.printStackTrace();
		}
		
		
		return null;
	}


	/**
	 * 개설과정에 개설과목을 추가하는 메소드이다.
	 * 게설과목 정보를 입력하면 등록 성공 여부를 반환한다.
	 * @param dto 개설과목 정보로 개설과정 번호, 과목 번호, 과목 시작일, 과목 종료일, 교사명을 포함한다.
	 * @return 성공 1, 실패 0 
	 */
	public int addOpenSubject(OpenC_OpenSubjectDTO dto) {
		
		try {

			String sql = "{ call procAddOpenSubject(?, ?, ?, ?, ?) }";

			CallableStatement cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getOpenCourseSeq());
			cstat.setString(2, dto.getSubjectSeq());
			cstat.setString(3, dto.getOpenSubjectBegin());
			cstat.setString(4, dto.getOpenSubjectEnd());
			cstat.setString(5, dto.getTeacherName());
			
			return cstat.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("AvailableSubjectDAO.addOpenSubject()");
			e.printStackTrace();
		}

		
		return 0;
	}

	
	/**
	 * 개설 과목 번호를 입력하면 해당 개설과목 정보를 반환하는 메소드이다.
	 * @param seq 개설과목 번호
	 * @return 개설과목 정보로 수정 전 개설과목 번호, 수정 후 개설과목 번호, 과목 시작일, 과목 종료일, 교사명을 포함한다.
	 */
	public OpenC_OpenSubjectDTO get(String seq) {

		try {
			
			
			String sql = "select s.subjectSeq as subjectSeq, to_char(s.openSubjectBegin, 'yyyy-mm-dd') as openSubjectBegin, to_char(s.openSubjectEnd, 'yyyy-mm-dd') as openSubjectEnd, "
					+ " t.teacherName as teacherName, c.openCourseSeq as openCourseSeq, sb.subjectName as subjectName from tblOpenSubject s inner join tblOpenCourse c on c.openCourseSeq = s.openCourseSeq"
					+ "	inner join tblTeacher t on c.teacherSeq = t.teacherSeq inner join tblSubject sb on s.subjectSeq = sb.subjectSeq where openSubjectSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				OpenC_OpenSubjectDTO dto = new OpenC_OpenSubjectDTO();
				
				dto.setOpenSubjectSeq(seq);
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setSubjectSeq(rs.getString("subjectSeq"));
				dto.setOpenSubjectBegin(rs.getString("openSubjectBegin"));				
				dto.setOpenSubjectEnd(rs.getString("openSubjectEnd"));
				dto.setTeacherName(rs.getString("teacherName"));
				
				return dto;
			}
			

		} catch (Exception e) {
			System.out.println("OpenC_OpenSubjectDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 개설 과목을 수정하는 메소드이다.
	 * 개설 과목 수정 정보를 입력하면 수정 성공여부를 반환한다.
	 * @param dto2 개설과목 정보로 수정 전 개설과목 번호, 수정 후 개설과목 번호, 과목 시작일, 과목 종료일, 교사명을 포함한다.
	 * @return 성공 1, 실패 0
	 */
	public int update(OpenC_OpenSubjectDTO dto2) {

		try {
			
			String sql = "{ call procUpdateOpenSubject(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto2.getOpenSubjectSeq());
			cstat.setString(2, dto2.getSubjectSeq());
			cstat.setString(3, dto2.getOpenSubjectBegin());
			cstat.setString(4, dto2.getOpenSubjectEnd());
			cstat.setString(5, dto2.getTeacherName());
			
			return cstat.executeUpdate();
	
			
		} catch (Exception e) {
			System.out.println("OpenC_OpenSubjectDAO.update()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 개설과목을 삭제하는 메소드이다.
	 * 개설과목 번호를 입력하면 삭제 성공여부를 반환한다.
	 * @param seq 개설과목 번호
	 * @return 성공 1, 삭제 0
	 */
	public int delete(String subjectSeq) {
		
		try {
			
			String sql = "{ call procDeleteOpenSubject(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);

			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("OpenC_OpenSubjectDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}


	
}
