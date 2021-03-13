package com.test.admin;

/**
 * 학생의 정보를 저장하는 DTO이다.
 * studentname //학생 이름
 * studentssn //주민번호 뒷자리
 * studenttel //전화번호
 * studentregister //등록일
 * completioncheck //수료 및 중도탈락 여부
 * @author 정경화
 *
 */
public class StudentDTO2 {

	private String studentname;
	private String studentssn;
	private String studenttel;
	private String studentregister;
	private String completioncheck;
	
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentssn() {
		return studentssn;
	}
	public void setStudentssn(String studentssn) {
		this.studentssn = studentssn;
	}
	public String getStudenttel() {
		return studenttel;
	}
	public void setStudenttel(String studenttel) {
		this.studenttel = studenttel;
	}
	public String getStudentregister() {
		return studentregister;
	}
	public void setStudentregister(String studentregister) {
		this.studentregister = studentregister;
	}
	public String getCompletioncheck() {
		return completioncheck;
	}
	public void setCompletioncheck(String completioncheck) {
		this.completioncheck = completioncheck;
	}
	

}
