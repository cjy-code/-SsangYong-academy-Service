package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.AvailableSubjectDTO;
//import dto.OpenCourseDTO;
//import dto.TeacherDTO;
import oracle.jdbc.internal.OracleTypes;



/**
 * 교사 계정과 관련한 기능을 담당하는 클래스이다.
 * @author 이은규
 *
 */
public class TeacherDAO {
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교사 DAO의 기본 생성자이다.
	 * DB 연결을 담당한다.
	 */
	public TeacherDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAO() : " + e.toString());
		}
		
	}

	/**
	 * 가지고 있는 전체 교사 목록을 출력하는 메소드이다.
	 * 교사번호, 교사명, 주민번호 뒷자리, 핸드폰번호
	 * @return 모든 교사 목록 배열
	 */
	public ArrayList<TeacherDTO> allTeacherList() {
		
		ArrayList<TeacherDTO> result = new ArrayList<TeacherDTO>();
		
		try {

			String sql = "select teacherSeq, teacherName, teacherTel, teacherSsn from tblTeacher order by teacherSeq asc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				
				TeacherDTO tcdto = new TeacherDTO();
				
				tcdto.setTeacherSeq(rs.getString("teacherSeq"));
				tcdto.setTeacherName(rs.getString("teacherName"));
				tcdto.setTeacherTel(rs.getString("teacherTel"));
				tcdto.setTeacherSsn(rs.getString("teacherSsn"));
				
				result.add(tcdto);
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.allTeacherList() : " + e.toString());
		}
		
		return null;
	}

	/**
	 * 새로운 교사 정보를 입력받아 새로 등록하는 메소드이다.
	 * @param tcdto TeacherDTO에 있는 등록할 교사 정보를 담은 배열
	 * @param teacherAvailableSubjects 과목목록에서 선택한 강의 가능한 과목 배열
	 * @return 등록 성공시 1 반환, 실패시 0 반환
	 */
	public int addTeacher(TeacherDTO tcdto, String[] availableSubject) {
		
			
			//등록한 교사의 teacherSeq
			String teacherSeq = ""; 

			try {
				
				//자동 Commit 끄기
				// - 교사 추가 후 강의 가능 과목까지 추가한 다음 commit
				conn.setAutoCommit(false);

				//교사 추가
				String sql = "{ call procaddTeacher(?, ?, ?, ?) }";
				cstat = conn.prepareCall(sql);
				cstat.setString(1, tcdto.getTeacherName());
				cstat.setString(2, tcdto.getTeacherTel());
				cstat.setString(3, tcdto.getTeacherSsn());
				cstat.registerOutParameter(4, OracleTypes.NUMBER); //교사번호

				
				cstat.executeUpdate();
				teacherSeq = cstat.getString(4);
				
				
				//교사 강의 가능 과목 추가
				boolean loop = true;
				for (int i=0; i<availableSubject.length; i++) {
					sql = "{ call procaddAvailableSubject(?, ?) }";
					
					cstat = conn.prepareCall(sql);
					
					cstat.setString(1, teacherSeq);
					cstat.setString(2, availableSubject[i]);
					int result = cstat.executeUpdate();
					
					if (result != 1) {
						loop = false;
						break;
					}
				}
				
				if (loop) {
					conn.commit();
					return 1;
				}
				
				
			} catch (Exception e) {
				
				try {
					conn.rollback();
				} catch (Exception e2) {
					System.out.println("TeacherDAO.addTeacher() : " + e2.toString());
				}
				
				System.out.println("TeacherDAO.addTeacher() : " + e.toString());
			}
			
			return 0;
		}
		

	
	
	/**
	 * 기존의 교사 정보 중 수정할 정보를 선택 후 수정하기위한 메소드이다.
	 * 교사명, 주민번호뒷자리, 핸드폰번호
	 * @param teacherSeq 수정할 교사 번호
	 * @param teacherName 수정할 교사 이름
	 * @param teacherTel 수정할 교사 핸드폰 번호
	 * @param teacherSsn 수정할 교사 주민번호 뒷자리
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateTeacher(String teacherSeq, String teacherName, String teacherTel, String teacherSsn) {
		
		//원래 값
		String name = "";
		String tel = "";
		String ssn = "";
		
		try {
			
			String sql = "select teacherName, teacherTel, teacherSsn from tblTeacher where teacherSeq = " + teacherSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("teacherName");
				tel = rs.getString("teacherTel");
				ssn = rs.getString("teacherSsn");
			}
			
			//이름 수정
			if (!teacherName.equals("")) {
				name = teacherName;
			}
			
			//전화번호 수정
			if (!teacherTel.equals("")) {
				tel = teacherTel;
			}
			
			//주민번호 뒷자리(비밀번호) 수정
			if (!teacherSsn.equals("")) {
				ssn = teacherSsn;
			}
			
			
			
			
			sql = "{ call procupdateTeacher(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.setString(2, name);
			cstat.setString(3, tel);
			cstat.setString(4, ssn);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherDAO.updateTeacher() : " + e.toString());
		}
		
		return 0;
	}
	

	
	/**
	 * 기존의 교사를 교사목록에서 삭제하기위한 메소드이다.
	 * @param teacherSeq 삭제할 교사 번호
	 * @return 삭제 성공시 1 반환, 실패시 0 반환
	 */
	public int deleteTeacher(String teacherSeq) {
		
		try {

			String sql = "{ call procdeleteTeacher(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherDAO.deleteTeacher() : " + e.toString());
		}
		return 0;
	}


	/**
	 * 특정 교사를 선택하여 해당 교사가 담당하는 개설 과정 목록을 출력하는 메소드이다.
	 * 과정번호, 과정명, 과정 시작 날짜, 과정 종료 날짜, 강의실, 수강상태
	 * @param teacherSeq 조회할 특정 교사 번호
	 * @return 선택한 특정 교사가 담당하는 개설 과정 목록 배열
	 */
	public ArrayList<OpenCourseDTO> openCourseList(String teacherSeq) {
		
		ArrayList<OpenCourseDTO> result = new ArrayList<OpenCourseDTO>();
		try {

			String sql = "{ call procopenCourse(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			while (rs.next()) {
				
				OpenCourseDTO occdto = new OpenCourseDTO();
				
				occdto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				occdto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				occdto.setCourseSeq(rs.getString("courseSeq"));
				occdto.setOpenCourseBegin(rs.getString("openCourseBegin"));
				occdto.setOpenCourseEnd(rs.getString("openCourseEnd"));
	
				
				result.add(occdto);
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherCourseList() : " + e.toString());
		}
		return null;
		
	}

}
