package com.test.admin;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/**
 * 
 * @author 최진영
 *시험관리 및 성적,배점 조회 DAO
 */
public class ExamRecordDAO {
	
	private  Connection conn;
	private  PreparedStatement pstat;
	private  CallableStatement cstat; 
	private  Statement stat;			
	private  ResultSet rs;	
	private  Scanner sc = new Scanner(System.in);

public ExamRecordDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	



	/**
	 * 과정 출력
	 * @param  검색값 
	 * @return ArrayList<ExamRecordDTO>
	 */
	ArrayList<ExamRecordDTO> Courselist(String word){ //--- 성적 리스트
		
		try {
			String where ="";
			 
			 if(word != null)	{
				 where = String.format("where name like '%%%s%%'", word);
			}
			 String sql = String.format("select * from vwOpenCourseExam %s order by OpenCourseSeq asc", where); 
			 rs = stat.executeQuery(sql);
			 
			 ArrayList<ExamRecordDTO> list = new ArrayList<ExamRecordDTO>();
			 
			 while(rs.next()) {
				 
				 ExamRecordDTO dto = new ExamRecordDTO();
				 
				 dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				 dto.setCourseName(rs.getString("courseName"));
				 dto.setOpenCourseBegin(rs.getString("OpenCourseBegin"));
				 dto.setOpencourseEnd(rs.getString("OpenCourseBegin"));
				 dto.setLectureroomName(rs.getString("lectureroomname"));
				 dto.setLectureroomNumber(rs.getString("lectureroomNumber"));
				 list.add(dto);
			 }
			return list;
			
			
		} catch (Exception e) {
			System.out.println("ExamRecordDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	/**
	 * 특정과정의 과목별 시험 정보 출력
	 * @param num --특정 과목 선택
	 * @return ArrayList<ExamRecordDTO>
	 */
	ArrayList<ExamRecordDTO>  subjectList(String num)	{
		
		try {
			
			String sql = "call procexamSearch(?, ?) ";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);
		
			ArrayList<ExamRecordDTO> list = new ArrayList<ExamRecordDTO>();
			
			while(rs.next()) {
				
				ExamRecordDTO dto = new ExamRecordDTO();
				
				dto.setSubjectSeq(rs.getString("subjectSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setSubjectState(rs.getString("subjectState"));
				dto.setOpensubjectbegin(rs.getString("opensubjectbegin"));
				dto.setOpensubjectend(rs.getString("opensubjectend"));
				dto.setExamdate(rs.getString("examdate"));
				
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
	 * 특정 과목의 모든 교육생 성적 정보 출력 메소드
	 * @param seq -- 특정 과목 번호
	 * @return ArrayList<ExamRecordDTO>
	 */
	public ArrayList<ExamRecordDTO> subjectScore(String seq) {
		try {
			String sql = "call procOpenSubjectScore(?, ?) ";			
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);
		
			ArrayList<ExamRecordDTO> list = new ArrayList<ExamRecordDTO>();
			
			while(rs.next()) {
				
				ExamRecordDTO dto = new ExamRecordDTO();
				
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setLectureroomName(rs.getString("lectureroomName"));
				dto.setScoreAttendance(rs.getString("scoreAttendance"));
				dto.setScoreHandWriting(rs.getString("scoreHandWriting"));
				dto.setScorePractical(rs.getString("scorePractical"));
				dto.setTeachername(rs.getString("teachername"));
				dto.setBooktitle(rs.getString("booktitle"));
				dto.setCourseTerm(rs.getString("courseTerm"));
				dto.setExamdate(rs.getString("examdate"));
				
				list.add(dto);
				
			}
			
				return list;

		} catch (Exception e) {
			System.out.println("ExamRecordDAO.subjectScore()");
			e.printStackTrace();
		}
		return null;
	}





	/**
	 * 특정 교육생의 성적 정보 출력 메소드
	 * @param seq -- 특정 교육생 번호
	 * @return ArrayList<ExamRecordDTO>
	 */
	public ArrayList<ExamRecordDTO> studentScore(String seq) {
		try {
			
			String sql = "call procStdScore(?, ?) ";			
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);
		
			ArrayList<ExamRecordDTO> list = new ArrayList<ExamRecordDTO>();
			
			while(rs.next()) {
				ExamRecordDTO dto = new ExamRecordDTO();
				
				dto.setSubjectSeq(rs.getString("subjectSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseTerm(rs.getString("courseterm"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setBooktitle(rs.getString("booktitle"));
				dto.setScoreAttendance(rs.getString("scoreAttendance"));
				dto.setScoreHandWriting(rs.getString("scoreHandWriting"));
				dto.setScorePractical(rs.getString("scorePractical"));
				dto.setExamdate(rs.getString("examdate"));
				dto.setTeachername(rs.getString("teachername"));
				dto.setExamErCheck(rs.getString("examErCheck"));
				
				list.add(dto);				
			}
			
				return list;
			
		} catch (Exception e) {
			System.out.println("ExamRecordDAO.studentScore()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}





	/**
	 * 배점 조회
	 * @param seq 과정번호
	 * @return ArrayList<ExamRecordDTO>
	 */
	public ArrayList<ExamRecordDTO> point(String seq) {
		
		try {
			String sql = "call procPoint(?, ?) ";			
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);
		
			ArrayList<ExamRecordDTO> list = new ArrayList<ExamRecordDTO>();
			
			while(rs.next()) {
				ExamRecordDTO dto = new ExamRecordDTO();
				
				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setPointattendance(rs.getString("pointattendance"));
				dto.setPointhandwriting(rs.getString("pointhandwriting"));
				dto.setPointpractical(rs.getString("pointpractical"));
				dto.setBooktitle(rs.getString("booktitle"));
				dto.setExamdate(rs.getString("examdate"));
				
				
				list.add(dto);				
			}
			
				return list;
		} catch (Exception e) {
			System.out.println("ExamRecordDAO.point()");
			e.printStackTrace();
		}
		
		
		return null;
	}



}
