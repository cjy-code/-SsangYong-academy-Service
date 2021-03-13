package com.test.admin;

/**
 * 
 * 개설 과정 정보를 저장하는 DTO 클래스이다.
 * openCourseSeq 개설과정번호 
 * lectureRoomSeq 강의실번호
 * courseSeq 과정 번호
 * teacherSeq 교사 번호
 * openCourseBegin	 시작날짜
 * openCourseEnd	 종료날짜
 * @author 이은규
 */

public class OpenCourseDTO {

	private String openCourseSeq; //개설 과정 번호
	private String lectureRoomSeq; // 강의실 번호
	private String courseSeq; // 과정 번호
	private String teacherSeq; // 교사 번호
	private String openCourseBegin; // 시작 날짜
	private String openCourseEnd; // 종료 날짜
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 개설 과정 번호
	 */
	
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param openCourseSeq 개설 과정 번호
	 */
	
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
	}
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 강의실 번호
	 */
	
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param lectureRoomSeq 강의실 번호
	 */
	
	public void setLectureRoomSeq(String lectureRoomSeq) {
		this.lectureRoomSeq = lectureRoomSeq;
	}
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 과정 번호
	 */
	
	public String getCourseSeq() {
		return courseSeq;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param courseSeq 과정 번호
	 */
	
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 교사 번호
	 */
	
	public String getTeacherSeq() {
		return teacherSeq;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param teacherSeq 교사 번호
	 */
	
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 시작 날짜
	 */
	
	public String getOpenCourseBegin() {
		return openCourseBegin;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param openCourseBegin 시작 날짜
	 */
	
	public void setOpenCourseBegin(String openCourseBegin) {
		this.openCourseBegin = openCourseBegin;
	}
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 종료 날짜
	 */
	
	public String getOpenCourseEnd() {
		return openCourseEnd;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param openCourseEnd 종료 날짜
	 */
	
	public void setOpenCourseEnd(String openCourseEnd) {
		this.openCourseEnd = openCourseEnd;
	}

	
	
}
