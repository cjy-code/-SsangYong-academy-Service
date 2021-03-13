package com.test.admin;

/**
 * 교재 정보를 저장하기 위한 클래스이다.
 * 교재번호, 교재명, 출판사를 관리한다.
 * bookSeq;	// 교재번호
 * bookTitle; // 교재명
 * bookPublisher; // 출판사
 * @author 이은규
 * 
 */

public class BookDTO {
	private String bookSeq;	// 교재번호
	private String bookTitle; // 교재명
	private String bookPublisher; // 출판사
	
	/**
	 * 교재번호를 내보내는 메소드이다.
	 * @return 교재번호
	 */
	public String getBookSeq() {
		return bookSeq;
	}
	
	/**
	 * 교재번호를 받는 메소드이다.
	 * @param seq 교재번호
	 */
	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}
	
	/**
	 * 교재명을 내보내는 메소드이다.
	 * @return 교재명
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	
	/**
	 * 교재명을 받는 메소드이다.
	 * @param title 교재명
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	/**
	 * 출판사를 내보내는 메소드이다.
	 * @return 출판사
	 */
	public String getBookPublisher() {
		return bookPublisher;
	}
	
	/**
	 * 출판사를 받는 메소드이다.
	 * 
	 * @param publisher 출판사
	 */
	public void setBookPublisher (String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	


}
