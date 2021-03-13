package com.test.teacher;

/**
 * 과정의 courseSeq 값을 알아내기 위한 DTO입니다.
 * @author User
 *
 */
public class TeacherCourseNumberDTO {
	
	private String seq;
	private String courseSeq;
	private String courseName;
	private String courseState;
	private String courseBegin;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	public String getCourseBegin() {
		return courseBegin;
	}
	public void setCourseBegin(String courseBegin) {
		this.courseBegin = courseBegin;
	}

}
