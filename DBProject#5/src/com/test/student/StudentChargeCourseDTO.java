package com.test.student;

/**
 * 학생의 기본키를 이용하여 학생의 과정을 알아내는 DTO입니다.
 * @author User
 *
 */
public class StudentChargeCourseDTO {
	
	private String studentSeq;
	private String openCourseSeq;
	
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	
}
