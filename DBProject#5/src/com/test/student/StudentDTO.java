package com.test.student;

/**
 * 학생의 정보를 받아오는 DTO입니다.
 * @author User
 *
 */
public class StudentDTO {
	
	private String studentSeq;
	private String studentName;
	private String studentTel;
	private String studentRegister;
	private String studentSsn;
	private String classSeq;
	
	public String getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
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
	public String getStudentSsn() {
		return studentSsn;
	}
	public void setStudentSsn(String studentSsn) {
		this.studentSsn = studentSsn;
	}
	

}
