package com.test.student;

/**
 * 교육생 1:1 질의응답 정보를 저장하는 DTO이다.
 * questionSeq // 질문 번호
 * classSeq //수강신청 번호
 * questionTitle //질문 제목
 * questionContents //질문 내용
 * questionDate //질문 등록일
 * answerCheck //질문 답변 여부
 * @author 정경화
 *
 */

public class QuestionDTO {

	private String questionSeq;
	private String classSeq;
	private String studentSeq;
	private String questionTitle;
	private String questionContents;
	private String questionDate;
	private String answerCheck;
	
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getQuestionSeq() {
		return questionSeq;
	}
	public String getAnswerCheck() {
		return answerCheck;
	}
	public void setAnswerCheck(String answerCheck) {
		this.answerCheck = answerCheck;
	}
	public void setQuestionSeq(String questionSeq) {
		this.questionSeq = questionSeq;
	}
	public String getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionContents() {
		return questionContents;
	}
	public void setQuestionContents(String questionContents) {
		this.questionContents = questionContents;
	}
	public String getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}

	
	
	
	
	
}
