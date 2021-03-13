package com.test.admin;

public class AttendanceDTO {

	private String openCourseSeq;
	private String openSubjectSeq;
	private String courseName;
	private String subjectName;
	private String openCourseBegin;
	private String opencourseEnd;
	private String lectureroomName;
	private String lectureroomNumber;
	private String examDate;
	
	private String studentSeq;
	private String studentName;
	private String attendanceState;
	private String attendanceenterdate;
	private String attendanceExitDate;
	
	
	
	
	/**
	 * 학생 번호 GET
	 * @return getStudentSeq
	 */
	public String getStudentSeq() {
		return studentSeq;
	}
	
	/**
	 * 학생 번호 SET
	 * @param studentSeq
	 */
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	
	/**
	 * 학생 번호 GET
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * 학생 이름 SET
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 출석 상태 GET
	 * @return attendanceState
	 */
	public String getAttendanceState() {
		return attendanceState;
	}
	
	/**
	 * 출석 상태 SET
	 * @param attendanceState
	 */
	public void setAttendanceState(String attendanceState) {
		this.attendanceState = attendanceState;
	}
	
	/**
	 * 입실 날짜 GET
	 * @return attendanceenterdate
	 */
	public String getAttendanceenterdate() {
		return attendanceenterdate;
	}
	
	/**
	 * 입실 날짜 SET
	 * @param attendanceenterdate
	 */
	public void setAttendanceenterdate(String attendanceenterdate) {
		this.attendanceenterdate = attendanceenterdate;
	}
	
	/**
	 * 퇴실 날짜 GET
	 * @return attendanceExitDate
	 */
	public String getAttendanceExitDate() {
		return attendanceExitDate;
	}
	
	/**
	 * 퇴실 날짜 SET
	 * @param attendanceExitDate
	 */
	public void setAttendanceExitDate(String attendanceExitDate) {
		this.attendanceExitDate = attendanceExitDate;
	}
	
	/**
	 * 개설 과정 번호 GET 
	 * @return openCourseSeq
	 */
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	
	/**
	 * 개설 과정 번호 SET
	 * @param openCourseSeq
	 */
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	
	/**
	 * 개설 과목 번호 GET
	 * @return openSubjectSeq
	 */
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	
	/**
	 * 개설 과목 번호 SET
	 * @param openSubjectSeq
	 */
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	
	/**
	 * 과정명 GET
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * 과정명 SET
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * 과목명 GET
	 * @return subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * 과목명 SET
	 * @param subjectName
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * 개설 과정 시작일 GET
	 * @return openCourseBegin
	 */
	public String getOpenCourseBegin() {
		return openCourseBegin;
	}
	
	/** 
	 * 개설 과정 시작일 SET
	 * @param openCourseBegin
	 */
	public void setOpenCourseBegin(String openCourseBegin) {
		this.openCourseBegin = openCourseBegin;
	}
	
	/**
	 * 개설 과정 종료일 GET
	 * @return opencourseEnd
	 */
	public String getOpencourseEnd() {
		return opencourseEnd;
	}
	
	/**
	 * 개설 과정 종료일 SET
	 * @param opencourseEnd
	 */
	public void setOpencourseEnd(String opencourseEnd) {
		this.opencourseEnd = opencourseEnd;
	}
	
	/**
	 * 강의실 명 GET
	 * @return lectureroomName
	 */
	public String getLectureroomName() {
		return lectureroomName;
	}
	
	/**
	 * 강의실 명 SET
	 * @param lectureroomName
	 */
	public void setLectureroomName(String lectureroomName) {
		this.lectureroomName = lectureroomName;
	}
	
	/**
	 * 강의실 번호 GET
	 * @return lectureroomNumber
	 */
	public String getLectureroomNumber() {
		return lectureroomNumber;
	}
	
	/** 
	 * 강의실 번호 SET
	 * @param lectureroomNumber
	 */
	public void setLectureroomNumber(String lectureroomNumber) {
		this.lectureroomNumber = lectureroomNumber;
	}
	
	/**
	 * 시험 날짜 GET
	 * @return
	 */
	public String getExamDate() {
		return examDate;
	}
	
	/**
	 * 시험 날짜 SET
	 * @param examDate
	 */
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	
}
