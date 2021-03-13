package com.test.teacher;


/**
 * 
 * 과목의 정보를 저장하는 DTO클래스이다.
 * understandingCheckSeq; //이해도조회번호
 * weeklyProgressSeq; //주간진도표번호
 * classSeq//수강신청번호
 * understandingNum// 이해도 수치
 * @author 이은규
 */
public class UnderstandingDTO {
	
	private String understandingCheckSeq;  //이해도조회번호
	private String weeklyProgressSeq;	   //주간진도표번호
	private String classSeq;			   //수강신청번호
	private String understandingNum;	   //이해도수치


	/**
	 * 수강신청번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 수강신청번호
	 */
	
	public String getClassSeq() {
		return classSeq;
	}

	/**
	 *수강신청번호에 대한 데이터를 받는 메소드이다.
	 * @param classSeq 수강신청번호
	 */
	
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
	}

	/**
	 * 이해도 조회 번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 이해도 조회 번호
	 */
	
	public String getUnderstandingCheckSeq() {
		return understandingCheckSeq;
	}
	
	/**
	 * 이해도 조회 번호에 대한 데이터를 받는 메소드이다.
	 * @param understandingCheckSeq 이해도 조회 번호
	 */
	
	public void setUnderstandingCheckSeq(String understandingCheckSeq) {
		this.understandingCheckSeq = understandingCheckSeq;
	}
	
	/**
	 * 주간 진도표 번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 주간 진도표 번호
	 */
	
	public String getWeeklyProgressSeq() {
		return weeklyProgressSeq;
	}

	/**
	 * 주간 진도표 번호에 대한 데이터를 받는 메소드이다.
	 * @param weeklyProgressSeq 주간 진도표 번호
	 */
	
	public void setWeeklyProgressSeq(String weeklyProgressSeq) {
		this.weeklyProgressSeq = weeklyProgressSeq;
	}
	
	/**
	 * 이해도 수치에 대한 데이터를 내보내는 메소드이다.
	 * @return 이해도 수치
	 */
	
	public String getUnderstandingNum() {
		return understandingNum;
	}

	/**
	 * 이해도 수치에 대한 데이터를 받는 메소드이다.
	 * @param understandingNum 이해도 수치
	 */
	
	public void setUnderstandingNum(String understandingNum) {
		this.understandingNum = understandingNum;
	}
	

	

	
	
	
	
	
}
