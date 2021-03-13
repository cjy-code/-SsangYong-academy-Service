package com.test.teacher;

/**
 * 시험 목록을 가져오는 DTO입니다.
 * @author User
 *
 */
public class TeacherExamDTO {
	
	private String Seq;
	private String courseSeq;
	private String courseName;
	private String subjectSeq;
	private String openSubjectSeq;
	private String subjectName;
	private String courseBegin;
	private String teacherSeq;
	private String examDate;
	private String examPaperSeq;
	private String examPaperDate;
	private String examQuestionSeq;
	private String question;
	private String answer;
	
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseBegin() {
		return courseBegin;
	}
	public void setCourseBegin(String courseBegin) {
		this.courseBegin = courseBegin;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamPaperSeq() {
		return examPaperSeq;
	}
	public void setExamPaperSeq(String examPaperSeq) {
		this.examPaperSeq = examPaperSeq;
	}
	public String getExamPaperDate() {
		return examPaperDate;
	}
	public void setExamPaperDate(String examPaperDate) {
		this.examPaperDate = examPaperDate;
	}
	public String getExamQuestionSeq() {
		return examQuestionSeq;
	}
	public void setExamQuestionSeq(String examQuestionSeq) {
		this.examQuestionSeq = examQuestionSeq;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
