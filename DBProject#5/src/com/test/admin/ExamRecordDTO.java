package com.test.admin;

/**
 * 관리자 시험 조회 DTO
 * @author 최진영
 *
 */
public class ExamRecordDTO {
	
	private String openCourseSeq;
	private String openSubjectSeq;
//	private String coursename;
	
	private String openCourseBegin;
	private String opencourseEnd;
	
	private String lectureroomName;
	private String lectureroomNumber;
	private String examdate;
	
	
	private String subjectSeq;
	private String courseName;
	private String subjectname;
	private String opensubjectbegin;
	private String opensubjectend;
	private String subjectState;
	
	//과목별 모든 교육생 성적 조회DTO
	private String studentSeq;
	private String studentName;
	private String scoreAttendance;
	private String scoreHandWriting;
	private String scorePractical;
	private String teachername;
	private String booktitle;
	private String courseTerm;
	
	//특정 교육생 모든 성적 정보
	private String courseterm; // 과정 기간
	private String examErCheck; //시험지 등록 여부 
	
	//특정 과정내 모든 배점 정보
	private String pointattendance;
	private String pointhandwriting;
	private String pointpractical;
	
	
	
	
	
	
	
	
	public String getPointattendance() {
		return pointattendance;
	}
	public void setPointattendance(String pointattendance) {
		this.pointattendance = pointattendance;
	}
	public String getPointhandwriting() {
		return pointhandwriting;
	}
	public void setPointhandwriting(String pointhandwriting) {
		this.pointhandwriting = pointhandwriting;
	}
	public String getPointpractical() {
		return pointpractical;
	}
	public void setPointpractical(String pointpractical) {
		this.pointpractical = pointpractical;
	}
	public String getCourseterm() {
		return courseterm;
	}
	public void setCourseterm(String courseterm) {
		this.courseterm = courseterm;
	}
	public String getExamErCheck() {
		return examErCheck;
	}
	public void setExamErCheck(String examErCheck) {
		this.examErCheck = examErCheck;
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
	public String getScoreAttendance() {
		return scoreAttendance;
	}
	public void setScoreAttendance(String scoreAttendance) {
		this.scoreAttendance = scoreAttendance;
	}
	public String getScoreHandWriting() {
		return scoreHandWriting;
	}
	public void setScoreHandWriting(String scoreHandWriting) {
		this.scoreHandWriting = scoreHandWriting;
	}
	public String getScorePractical() {
		return scorePractical;
	}
	public void setScorePractical(String scorePractical) {
		this.scorePractical = scorePractical;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getCourseTerm() {
		return courseTerm;
	}
	
	
	
	
	
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	public String getSubjectState() {
		return subjectState;
	}
	public void setSubjectState(String subjectState) {
		this.subjectState = subjectState;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getOpensubjectbegin() {
		return opensubjectbegin;
	}
	public void setOpensubjectbegin(String opensubjectbegin) {
		this.opensubjectbegin = opensubjectbegin;
	}
	public String getOpensubjectend() {
		return opensubjectend;
	}
	public void setOpensubjectend(String opensubjectend) {
		this.opensubjectend = opensubjectend;
	}
	
	
	
	
	
	
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
//	public String getCoursename() {
//		return coursename;
//	}
//	public void setCoursename(String coursename) {
//		this.coursename = coursename;
//	}
	
	public String getOpenCourseBegin() {
		return openCourseBegin;
	}
	public void setOpenCourseBegin(String openCourseBegin) {
		this.openCourseBegin = openCourseBegin;
	}
	public String getOpencourseEnd() {
		return opencourseEnd;
	}
	public void setOpencourseEnd(String opencourseEnd) {
		this.opencourseEnd = opencourseEnd;
	}
	public String getLectureroomName() {
		return lectureroomName;
	}
	public void setLectureroomName(String lectureroomName) {
		this.lectureroomName = lectureroomName;
	}
	public String getLectureroomNumber() {
		return lectureroomNumber;
	}
	public void setLectureroomNumber(String lectureroomNumber) {
		this.lectureroomNumber = lectureroomNumber;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	
	

	
	
	
	
	
	
}
