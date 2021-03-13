package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


//import dto.VwOpenCourseDTO;
import oracle.jdbc.OracleTypes;

/**
 * 개설 과정 관리 기능과 관련된 클래스이다.
 * 개설 과정 조회, 개설 과정 추가, 개설 과정 수정, 개설 과정 삭제 기능이 포함된다.
 * @author 정경화
 *
 */
public class VwOpenCourseDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 개설과정 DAO의 기본 생성자이다.
	 */
	public VwOpenCourseDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전체 개설 과정의 정보를 출력하는 메소드이다.
	 * 개설 과정 번호, 과정명, 과정 시작일, 과정 종료일, 강의실 명, 과목 등록 여부, 교육생 등록 인원을 포함한다.
	 * @return 과정 정보 배열
	 */
	public ArrayList<VwOpenCourseDTO> list() {
	
		try {
			
			ArrayList<VwOpenCourseDTO> list = new ArrayList<VwOpenCourseDTO>();
			
			String sql = "select distinct vw.openCourseSeq as vwOpenCourseSeq, vw.courseName as vwCourseName, to_char(vw.openCourseBegin, 'yyyy-mm-dd') as vwOpenCourseBegin, "				 
					+ "to_char(vw.openCourseEnd, 'yyyy-mm-dd') as vwOpenCourseEnd, vw.lectureRoomName vwLectureRoomName from vwOpenCourse vw "
					+ "left outer join tblOpenSubject os on os.openCourseSeq = vw.openCourseSeq order by vw.openCourseSeq asc";
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				VwOpenCourseDTO dto = new VwOpenCourseDTO();
				
				
				dto.setOpenCourseSeq(rs.getString("vwOpenCourseSeq"));
				dto.setCourseName(rs.getString("vwCourseName"));
				dto.setOpenCourseBegin(rs.getString("vwOpenCourseBegin"));
				dto.setOpenCourseEnd(rs.getString("vwOpenCourseEnd"));
				dto.setLectureRoomName(rs.getString("vwLectureRoomName"));
				dto.setRegister(subjectRegister(rs.getString("vwOpenCourseSeq")));
				dto.setStudent(studentRegister(rs.getString("vwOpenCourseSeq")));
				
				list.add(dto);
				
			}

			return list;
			
		} catch (Exception e) {
			System.out.println("VwOpenCourseDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 개설 과목 등록 여부를 반환하는 메소드이다.
	 * 과목 등록 시 '등록', 미등록 시 '미등록'을 반환한다.
	 * @param seq 개설 과정 번호
	 * @return 과목 등록 여부
	 */
	private String subjectRegister(String seq) {
		
		try {
			
			String sql = "{ ? = call fnSubjectEnrollment(?) }";
			
			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.VARCHAR);
			cstat.setString(2, seq);
			
			cstat.executeQuery();
			String result = cstat.getString(1);
			
			return result;
			
		} catch (Exception e) {
			System.out.println("VwOpenCourseDAO.subjectRegister()");
			e.printStackTrace();
		}
		
		return null;

	}

	/**
	 * 개설 과정의 교육생 등록 인원을 반환하는 메소드이다.
	 * @param seq 개설 과정 번호
	 * @return 등록 인원
	 */
	private String studentRegister(String seq) {
		
		try {
			
			String sql = "{ ? = call fnStudent(?) }";
			
			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.NUMBER);
			cstat.setString(2, seq);
			
			cstat.executeQuery();
			String result = cstat.getString(1);
			
			return result;
			
		} catch (Exception e) {
			System.out.println("VwOpenCourseDAO.studentRegister()");
			e.printStackTrace();
		}
		
		return null;

	}
	

	/**
	 * 개설 과정을 등록하는 메소드이다.
	 * 등록 성공 여부를 반환한다.
	 * @param dto 과정 정보로 과정 번호, 과정 시작일, 과정 종료일, 강의실 명을 포함한다.
	 * @return 성공 1, 실패 0
	 */
	public int addOpenCourse(VwOpenCourseDTO dto) {

		try {
			
			String sql = "{ call procAddOpenCourse(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getCourseSeq());
			cstat.setString(2, dto.getOpenCourseBegin());
			cstat.setString(3, dto.getOpenCourseEnd());
			cstat.setString(4, dto.getLectureRoomName());
			
			return cstat.executeUpdate();			

		} catch (Exception e) {
			System.out.println("OpenCourseDAO.addOpenCourse()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	/**
	 * 개설 과정 번호를 입력하면 선택한 개설 과정 정보를 반환하는 메소드이다.
	 * @param seq 수정할 개설 과정 번호
	 * @return 개설 과정 정보로 과정 번호, 과정 시작일, 과정 종료일, 강의실 명을 포함한다.
	 */
	public VwOpenCourseDTO get(String seq) {

		try {

			String sql = "select courseSeq, to_char(openCourseBegin, 'yyyy-mm-dd') as openCourseBegin, to_char(openCourseEnd, 'yyyy-mm-dd') as openCourseEnd, "
					+ "(select lectureRoomName from tblLectureRoom where lectureRoomSeq = tblOpenCourse.lectureRoomSeq) as lectureRoomName,"
					+ "(select courseName from tblCourse where courseSeq = tblOpenCourse.courseSeq) as courseName, openCourseSeq from tblOpenCourse where openCourseSeq = ?";
			
			
			cstat = conn.prepareCall(sql);			
			cstat.setString(1, seq);		
			rs = cstat.executeQuery();

			if (rs.next()) {

				VwOpenCourseDTO dto = new VwOpenCourseDTO();
				
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseSeq(rs.getString("courseSeq"));
				dto.setOpenCourseBegin(rs.getString("openCourseBegin"));
				dto.setOpenCourseEnd(rs.getString("openCourseEnd"));
				dto.setLectureRoomName(rs.getString("lectureRoomName"));
			
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 과정 번호를 입력하면 해당하는 과정명을 반환하는 메소드이다.
	 * @param courseSeq 과정 번호
	 * @return 과정명
	 */
	public String getCourseName(String courseSeq) {
		
		try {
			
			String sql = "select courseName from tblCourse where courseSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, courseSeq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
			
			
		} catch (Exception e) {
			System.out.println("CourseDAO.get()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	/**
	 * 과목 번호를 입력하면 해당하는 과목명을 반환하는 메소드이다.
	 * @param subjectSeq 과목 번호
	 * @return 과목명
	 */
	public String getSubjectName(String subjectSeq) {

		try {
			
			String sql = "select subjectName from tblSubject where subjectSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, subjectSeq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
			
			
		} catch (Exception e) {
			System.out.println("VwOpenCourseDAO.getSubjectName()");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 개설 과정을 수정하는 메소드이다.
	 * 수정 성공 여부를 반환한다.
	 * @param dto2 개설 과정 정보로 수정할 개설 과정 번호, 변경될 과정 번호, 강의실 명, 과정 시작일, 과정 종료일을 포함한다.
	 * @return 성공 1, 실패 0
	 */
	public int edit(VwOpenCourseDTO dto2) {

		try {
			
			String sql = "{ call procUpdateOpenCourse(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto2.getOpenCourseSeq());
			cstat.setString(2, dto2.getCourseSeq());
			cstat.setString(3, dto2.getLectureRoomName());
			cstat.setString(4, dto2.getOpenCourseBegin());
			cstat.setString(5, dto2.getOpenCourseEnd());		
		    
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.edit()");
			e.printStackTrace();
		}

		return 0;
	}


	/**
	 * 개설 과정을 삭제하는 메소드이다.
	 * 삭제 성공 여부를 반환한다.
	 * @param seq 개설 과정 번호
	 * @return 성공 1, 실패 0
	 */
	public int delete(String seq) {

		try {
			
			String sql = "{ call procDeleteOpenCourse(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.delete()");
			e.printStackTrace();
		}

		return 0;
	}



}

