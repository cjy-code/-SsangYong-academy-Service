package com.test.admin;



/**
 * 
 * 과목의 정보를 저장하는 DTO클래스이다.
 * subjectSeq; //과목 번호
 * bookSeq; //교재명
 * subjectName; //과목명
 * subjectTerm; //기간(일)
 * @author 이은규
 */
public class SubjectDTO {

	private String subjectSeq; //과목 번호
	private String bookSeq; //교재명
	private String subjectName; //과목명
	private String subjectTerm; //기간(일)

	
	/**
	 * 과목 번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목 번호
	 */
	
	public String getSubjectSeq() {
		return subjectSeq;
	}

	/**
	 * 과목 번호에 대한 데이터를 받는 메소드이다.
	 * @param subjectSeq 과목번호
	 */
	
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	
	/**
	 * 책번호에 대한 데이터를 내보내는 메소드이다.
	 * @param 책번호
	 */
	
	public String getBookSeq() {
		return bookSeq;
	}
	
	/**
	 * 책번호에 대한 데이터를 받는 메소드이다.
	 * @param bookSeq 책번호
	 */
	
	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}
	
	/**
	 * 과목명에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목명
	 */
	
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * 과목명에 대한 데이터를 받는 메소드이다.
	 * @param setSubjectName 과목명
	 */
	
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * 과목 기간(일)에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목 기간(일)
	 */
	
	public String getSubjectTerm() {
		return subjectTerm;
	}
	
	
	/**
	 * 과목 기간(일)에 대한 데이터를 받는 메소드이다.
	 * @param setSubjectTerm 과목 기간(일)
	 */
	
	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}
	
}
