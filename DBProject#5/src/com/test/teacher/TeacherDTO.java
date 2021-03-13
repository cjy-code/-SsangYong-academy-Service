package com.test.teacher;

/**
 * 교사의 정보를 나타내는 DTO입니다.
 * @author User
 *
 */
public class TeacherDTO {
	
	private String teacherSeq;
	private String teacherName;
	private String teacherTel;
	private String teacherSsn;
	
	
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherTel() {
		return teacherTel;
	}
	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}
	public String getTeacherSsn() {
		return teacherSsn;
	}
	public void setTeacherSsn(String teacherSsn) {
		this.teacherSsn = teacherSsn;
	}
	
	
	
}
