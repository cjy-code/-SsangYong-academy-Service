package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import dto.QuestionDTO;

/**
 * 교육생 1:1 질의응답과 관련된 기능을 포함하는 클래스이다.
 * @author 정경화
 *
 */
public class QuestionDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 교육생 1:1 질의응답 DAO의 기본 생성자이다.
	 */
	public QuestionDAO() {
		
		try {			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("CourseDAO.CourseDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 교육생이 질문을 등록하는 메소드이다.
	 * 질문 등록 성공 여부를 반환한다.
	 * @param dto 질문 정보로 수강신청번호, 제목, 내용을 포함한다.
	 * @return 등록 성공 시 1, 실패 시 0
	 */
	public int addQuestion(QuestionDTO dto) {

		try {
			
			String sql = "insert into tblQuestion(questionSeq, classSeq, questionTitle, questionContents, questionDate)"
					+ " values (questionSeq.nextVal, ?, ?, ?, default)";
			 
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getClassSeq());
			pstat.setString(2, dto.getQuestionTitle());
			pstat.setString(3, dto.getQuestionContents());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("QuestionDAO.addQuestion()");
			e.printStackTrace();
		}

		return 0;
	}

	
	/**
	 * 등록된 질문을 조회하는 메소드이다.
	 * 교육생은 수강신청번호를 이용해 본인의 질문만 조회할 수 있다.
	 * @param classSeq 수강 신청 번호
	 * @return 질문 정보로 질문 번호, 질문 날짜, 제목, 내용, 답변 여부를 포함한다.
	 */
	public ArrayList<QuestionDTO> QuestionList(String classSeq) {
		
		try {
			
			ArrayList<QuestionDTO> list = new ArrayList();
			
			String sql = "select q.questionSeq as questionSeq, to_char(q.questionDate, 'yyyy-mm-dd') as questionDate, q.questionTitle as questionTitle,"
					+ " q.questionContents as questionContents, a.answerCheck as answerCheck"
					+ " from tblQuestion q inner join tblAnswer a on q.questionSeq = a.questionSeq where q.classSeq = ?";
	
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, classSeq);
			
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				
				QuestionDTO dto = new QuestionDTO();
				dto.setQuestionSeq(rs.getString("questionSeq"));
				dto.setQuestionDate(rs.getString("questionDate"));
				dto.setQuestionTitle(rs.getString("questionTitle"));
				dto.setQuestionContents(rs.getString("questionContents"));
				dto.setAnswerCheck(rs.getString("answerCheck"));
			
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("QuestionDAO.QuestionList()");
			e.printStackTrace();
		}

		return null;
	}


	
	/**
	 * 등록된 질문을 삭제하는 메소드이다.
	 * 삭제 성공 여부를 반환한다.
	 * @param sel 질문 번호
	 * @return 삭제 성공 시 1, 실패 시 0
	 */
	public int delete(String sel) {

		try {
			
			String sql = "{ call procDeleteQuestion(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sel);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("QuestionDAO.delete()");
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 질문 번호에 해당하는 질문 정보를 반환하는 메소드이다.
	 * @param sel 질문 번호
	 * @return 질문 정보로 질문 날짜, 제목, 내용, 답변여부를 포함한다.
	 */
	public QuestionDTO get(String sel) {

		try {
			
			String sql = "select to_char(q.questionDate, 'yyyy-mm-dd') as questionDate, q.questionTitle as questionTitle, q.questionContents as questionContents, a.answerCheck as answerCheck"
					+ " from tblQuestion q inner join tblAnswer a on q.questionSeq = a.questionSeq where q.questionSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			rs = pstat.executeQuery();
			
			QuestionDTO dto = new QuestionDTO();

			if (rs.next()) {
				
				dto.setQuestionDate(rs.getString("questionDate"));
				dto.setQuestionTitle(rs.getString("questionTitle"));
				dto.setQuestionContents(rs.getString("questionContents"));
				dto.setAnswerCheck(rs.getString("answerCheck"));
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("QuestionDAO.get()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	/**
	 * 등록된 질문을 수정하는 메소드이다.
	 * 질문 번호를 선택해 제목과 내용을 수정할 수 있다.
	 * 수정 성공 여부를 반환한다.
	 * @param dto2 질문 정보로 수정 후 제목, 내용, 질문 번호를 포함한다.
	 * @return 수정 성공 시 1, 실패 시 0
	 */
	public int update(QuestionDTO dto2) {
		
		try {
			
			String sql = "update tblQuestion set questionTitle = ?, questionContents = ? where questionSeq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getQuestionTitle());
			pstat.setString(2, dto2.getQuestionContents());
			pstat.setString(3, dto2.getQuestionSeq());
			
			return pstat.executeUpdate();						
			
		} catch (Exception e) {
			System.out.println("QuestionDAO.update()");
			e.printStackTrace();
		}

		return 0;
	}


	
}
