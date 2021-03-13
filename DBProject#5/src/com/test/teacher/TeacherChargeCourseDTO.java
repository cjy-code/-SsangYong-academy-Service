package com.test.teacher;

/**
 * 교사가 담당한 과정의 목록을 가져오는 DTO입니다.
 * @author User
 *
 */
public class TeacherChargeCourseDTO {
	
	private String seq;
	private String courseName;
	private String courseBegin;
	private String courseEnd;
	private String lectureRoomName;
	private String courseStudent;
	private String courseState;
	private String teacherSeq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseBegin() {
		return courseBegin;
	}
	public void setCourseBegin(String courseBegin) {
		this.courseBegin = courseBegin;
	}
	public String getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(String courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getLectureRoomName() {
		return lectureRoomName;
	}
	public void setLectureRoomName(String lectureRoomName) {
		this.lectureRoomName = lectureRoomName;
	}
	public String getCourseStudent() {
		return courseStudent;
	}
	public void setCourseStudent(String courseStudent) {
		this.courseStudent = courseStudent;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}

}
