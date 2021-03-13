package com.test.admin;

public class AvailableSubjectDTO {

	/**
	 * 강의 가능 과목 기능과 관련된 정보를 저장하는 DTO이다.
	 * availableSubjectSeq // 강의 가능 과목 번호
	 * teacherSeq //교사 번호
	 * teacherName //교사명
	 * subjectSeq //과목 번호
	 */
	private String availableSubjectSeq;
	private String teacherSeq;
	private String teacherName;
	private String subjectSeq;
	
	public String getAvailableSubjectSeq() {
		return availableSubjectSeq;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setAvailableSubjectSeq(String availableSubjectSeq) {
		this.availableSubjectSeq = availableSubjectSeq;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	
	

	
	
	
}
