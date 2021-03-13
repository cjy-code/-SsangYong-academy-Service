package com.test.admin;

/**
 * 교사 데이터 정보를 저장하기 위한 클래스이다.
 * 교사번호,교사이름,주민번호뒷자리,전화번호를 관리한다.
 * teacherSeq;	//교사번호
 * teacherName;	//교사이름
 * teacherTel;	//교사 전화번호
 * teacherSsn;	//교사 주민등록번호 뒷자리
 * @author 이은규
 */

public class TeacherDTO {

	private String teacherSeq;	// 교사 번호
	private String teacherName;	// 교사 이름
	private String teacherTel;	// 교사 전화번호
	private String teacherSsn;	// 교사 주민등록번호 뒷자리
	
	/**
	 * 교사 번호의 데이터를 내보내는 메소드이다.
	 * @return 교사 번호
	 */
	public String getTeacherSeq() {
		return teacherSeq;
	}
	
	/**
	 * 교사 번호의 데이터를 받는 메소드이다.
	 * @param teacherSeq 교사 번호
	 */
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	
	/**
	 * 이름에 대한 데이터를 내보내는 메소드이다.
	 * @return 이름
	 */
	public String getTeacherName() {
		return teacherName;
	}
	
	/**
	 * 이름에 대한 데이터를 받는 메소드이다.
	 * @param teacherName 이름
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	/**
	 * 주민등록번호(뒷자리)에 대한 데이터를 내보내는 메소드이다.
	 * @return 주민등록번호(뒷자리)
	 */
	public String getTeacherSsn() {
		return teacherSsn;
	}
	
	/**
	 * 주민등록번호(뒷자리)에 대한 데이터를 받는 메소드이다.
	 * @param teacherSsn 주민등록번호(뒷자리)
	 */
	public void setTeacherSsn(String teacherSsn) {
		this.teacherSsn = teacherSsn;
	}
	
	/**
	 * 전화번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 전화번호
	 */
	public String getTeacherTel() {
		return teacherTel;
	}
	
	/**
	 * 전화번호에 대한 데이터를 받는 메소드이다.
	 * @param teacherTel 전화번호
	 */
	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}

}
