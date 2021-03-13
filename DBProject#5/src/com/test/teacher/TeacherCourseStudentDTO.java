package com.test.teacher;

/**
 * 과정의 교육생을 나타내는 DTO입니다.
 * @author User
 *
 */
public class TeacherCourseStudentDTO {
	
	private String seq;
	private String courseSeq;
	private String courseName;
	private String studentSeq;
	private String studentName;
	private String studentTel;
	private String studentRegister;
	private String completionCheck;
	private String courseState;
	
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
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentTel() {
		return studentTel;
	}
	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}
	public String getStudentRegister() {
		return studentRegister;
	}
	public void setStudentRegister(String studentRegister) {
		this.studentRegister = studentRegister;
	}
	public String getCompletionCheck() {
		return completionCheck;
	}
	public void setCompletionCheck(String completionCheck) {
		this.completionCheck = completionCheck;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	

}
