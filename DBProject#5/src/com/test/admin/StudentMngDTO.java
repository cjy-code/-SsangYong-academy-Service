package com.test.admin;


/**
 * 학생 관리 DTO
 * @author 최진영
 *
 */
public class StudentMngDTO {
	//모든 학생 LIST
	private String seq;      // 교육생 번호
	private String name;     // 교육생 이름
	private String tel;      // 교육생 전화번호
	private String regdate;  // 교육생 등록일
	private String ssn;      // 교육생 주민번호
	
	
	
	
	//특정 교육생 조회
	
	private String studentSeq;
	private String studentName;
	private String studentTel;
	private String studentRegister;
	private String coursename;
	private String courseterm;
	private String lectureroomname;
	private String completioncheck;
	private String completiondate;
	private String opencoursebegin;
	private String opencourseend;
	private String coursestate;
	
	
	// 
	
	
	/**
	 * 학생 번호 GET
	 * @return studentSeq
	 */
	public String getStudentSeq() {
		return studentSeq;
	}
	
	/**
	 * 학생 번호 SET
	 * @param 학생 번호
	 */
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	
	/**
	 * 학생 이름 GET
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * 학생 이름 SET
	 * @param 학생 이름
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 학생 전화번호 GET
	 * @return  studentTel
	 */
	public String getStudentTel() {
		return studentTel;
	}
	
	/**
	 * 학생 전화번호 SET
	 * @param 학생 전화번호
	 */
	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}
	
	/**
	 * 학생 가입일 GET
	 * @return studentRegister
	 */
	public String getStudentRegister() {
		return studentRegister;
	}
	
	/**
	 * 학생 가입일 SET
	 * @param 학생 주민번호
	 */
	public void setStudentRegister(String studentRegister) {
		this.studentRegister = studentRegister;
	}
	
	/**
	 * 과정 진행상태 GET
	 * @return coursestate
	 */
	public String getCoursestate() {
		return coursestate;
	}
	
	/**
	 * 과정 진행상태 SET
	 * @param coursestate 과정 진행 상태
	 */
	public void setCoursestate(String coursestate) {
		this.coursestate = coursestate;
	}
	
	/**
	 * 과정 이름 GET
	 * @return coursename
	 */
	public String getCoursename() {
		return coursename;
	}
	
	/**
	 * 과정 이름 SET
	 * @param coursename 과정이름
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	/**
	 * 과정 기간 GET
	 * @return courseterm
	 */
	public String getCourseterm() {
		return courseterm;
	}
	
	/**
	 * 과정 기간 SET
	 * @param courseterm 과정 기간
	 */
	public void setCourseterm(String courseterm) {
		this.courseterm = courseterm;
	}
	
	/**
	 * 강의실 명 GET
	 * @return 강의실 명
	 */
	public String getLectureroomname() {
		return lectureroomname;
	}
	
	/**
	 * 강의실명 SET
	 * @param lectureroomname 강의실 명
	 */
	public void setLectureroomname(String lectureroomname) {
		this.lectureroomname = lectureroomname;
	}
	
	/** 
	 * 수강 여부 GET
	 * @return completioncheck
	 */
	public String getCompletioncheck() {
		return completioncheck;
	}
	
	/**
	 * 수료 여부 SET
	 * @param completioncheck 수강여부
	 */
	public void setCompletioncheck(String completioncheck) {
		this.completioncheck = completioncheck;
	}
	
	/**
	 * 수료 날짜 GET
	 * @return completiondate
	 */
	public String getCompletiondate() {
		return completiondate;
	}
	
	/**
	 *  수료 날짜 SET
	 * @param completiondate
	 */
	public void setCompletiondate(String completiondate) {
		this.completiondate = completiondate;
	}
	
	/**
	 * 개설 과정 시작일 GET
	 * @return getOpencoursebegin
	 */
	public String getOpencoursebegin() {
		return opencoursebegin;
	}
	
	/**
	 * 개설 과정 시작일 SET
	 * @param opencoursebegin
	 */
	public void setOpencoursebegin(String opencoursebegin) {
		this.opencoursebegin = opencoursebegin;
	}
	
	/**
	 * 개설 과정 종료일 GET
	 * @return opencourseend
	 */
	public String getOpencourseend() {
		return opencourseend;
	}
	
	/**
	 * 개설 과정 종료일 SET
	 * @param opencourseend 
	 */
	public void setOpencourseend(String opencourseend) {
		this.opencourseend = opencourseend;
	}
	
	/**
	 * 주민번호 GET
	 * @return getSsn
	 */
	public String getSsn() {
		return ssn;
	}
	
	/**
	 * 주민 번호 SET
	 * @param ssn
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSeq() {
		return seq;
	}
	
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
