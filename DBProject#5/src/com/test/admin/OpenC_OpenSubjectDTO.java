package com.test.admin;

import java.sql.Time;

/**
 * 개설 과정의 개설 과목과 관련된 정보를 저장하는 DTO이다.
 * openCourseSeq
 * courseName
 * openSubjectSeq
 * subjectSeq
 * subjectName
 * openSubjectBegin
 * openSubjectEnd
 * teacherSeq
 * teacherName
 * openCourseBegin
 * openCourseEnd
 * bookTile
 * 
 * @author 정경화
 *
 */
public class OpenC_OpenSubjectDTO {

	private String openCourseSeq;
	private String courseName;
	private String openSubjectSeq;
	private String subjectSeq;
	private String subjectName;
	private String openSubjectBegin;
	private String openSubjectEnd;
	private String teacherSeq;
	private String teacherName;
	private String openCourseBegin;
	private String openCourseEnd;
	private String bookTile;

//	private String courseTerm;
//	private String lectureRoomName;
//	private String register;
//	private String student;
	
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
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
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
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
	public String getBookTile() {
		return bookTile;
	}
	public void setBookTile(String bookTile) {
		this.bookTile = bookTile;
	}

	
	
}
