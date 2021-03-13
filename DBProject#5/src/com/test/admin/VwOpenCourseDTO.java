package com.test.admin;

/**
 * 개설 과정과 관련된 정보를 저장하는 DTO이다.
 * openCourseSeq //개설 과정 번호
 * courseName //과정명
 * lectureRoomName //강의실명
 * courseSeq //과정 번호
 * teacherSeq //교사 번호
 * openCourseBegin //과정 시작일
 * openCourseEnd //과정 종료일
 * @author 정경화
 */
public class VwOpenCourseDTO {

	private String openCourseSeq;
	private String courseName;
	private String lectureRoomName;
	private String courseSeq;
	private String teacherSeq;
	private String openCourseBegin;
	private String openCourseEnd;
	
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getLectureRoomName() {
		return lectureRoomName;
	}
	public void setLectureRoomName(String lectureRoomName) {
		this.lectureRoomName = lectureRoomName;
	}
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getOpenCourseBegin() {
		return openCourseBegin;
	}
	public void setOpenCourseBegin(String openCourseBegin) {
		this.openCourseBegin = openCourseBegin;
	}
	public String getOpenCourseEnd() {
		return openCourseEnd;
	}
	public void setOpenCourseEnd(String openCourseEnd) {
		this.openCourseEnd = openCourseEnd;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	private String register;
	private String student;


	
	
}
