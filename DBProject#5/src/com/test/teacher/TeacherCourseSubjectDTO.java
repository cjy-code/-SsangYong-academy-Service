package com.test.teacher;

/**
 * 과정의 과목을 나타내는 DTO입니다.
 * @author User
 *
 */
public class TeacherCourseSubjectDTO {
	
	private String Seq;
	private String courseSeq;
	private String courseName;
	private String subjectSeq;
	private String subjectName;
	private String courseBegin;
	private String openSubjectBegin;
	private String openSubjectEnd;
	private String courseStudent;
	private String bookTitle;
	private String courseState;
	
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
	public String getOpenSubjectBegin() {
		return openSubjectBegin;
	}
	public void setOpenSubjectBegin(String openSubjectBegin) {
		this.openSubjectBegin = openSubjectBegin;
	}
	public String getOpenSubjectEnd() {
		return openSubjectEnd;
	}
	public void setOpenSubjectEnd(String openSubjectEnd) {
		this.openSubjectEnd = openSubjectEnd;
	}
	public String getCourseStudent() {
		return courseStudent;
	}
	public void setCourseStudent(String courseStudent) {
		this.courseStudent = courseStudent;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}	
	
}
