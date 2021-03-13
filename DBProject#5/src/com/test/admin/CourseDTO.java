package com.test.admin;

/**
 * 
 * 과정의 정보를 저장하는 DTO이다. 
 * courseSeq; //번호 
 * courseName; //과정명 
 * courseTerm; //기간
 * @author 이은규
 */

public class CourseDTO {
	
	private String courseSeq; //번호
	private String courseName; //과정명
	private String courseTerm; //기간
	
	/**
	 * 과정번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 과정번호
	 */
	public String getCourseSeq() {
		return courseSeq;
	}
	
	/**
	 * 과정번호에 대한 데이터를 받는 메소드이다.
	 * @param courseSeq 과정번호
	 */
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	
	/**
	 * 과정명에 대한 데이터를 내보내는 메소드이다.
	 * @return 과정명
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * 과정명에 대한 데이터를 받는 메소드이다.
	 * @param courseName 과정명
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * 기간(일)에 대한 데이터를 내보내는 메소드이다.
	 * @return 기간(일)
	 */
	public String getCourseTerm() {
		return courseTerm;
	}
	
	/**
	 * 기간(일)에 대한 데이터를 받는 메소드이다.
	 * @param courseTerm 기간(일)
	 */
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}


}
