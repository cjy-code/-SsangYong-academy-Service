package com.test.admin;


/**
* 
* 강의실 정보를 저장하는 DTO 클래스이다.
* lectureRoomSeq = 강의실 번호
* lectureRoomName = 강의실 이름
* lectureRoomNumber = 강의실 수용인원수
* @author 이은규
*
*/

public class LectureRoomDTO {
	
	private String lectureRoomSeq;
	private String lectureRoomName;
	private String lectureRoomNumber;
	
	/**
	 * 강의실 번호를 내보내는 메소드이다.
	 * @return 강의실 번호
	 */
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	
	/**
	 * 강의실 번호를 받는 메소드이다.
	 * @param seq 강의실 번호
	 */
	public void setLectureRoomSeq(String lectureRoomSeq) {
		this.lectureRoomSeq = lectureRoomSeq;
	}
	
	/**
	 * 강의실명을 내보내는 메소드이다.
	 * @return 강의실명
	 */
	public String getLectureRoomName() {
		return lectureRoomName;
	}
	
	/**
	 * 강의실명을 받는 메소드이다.
	 * @param name 강의실명
	 */
	public void setLectureRoomName(String lectureRoomName) {
		this.lectureRoomName = lectureRoomName;
	}
	
	/**
	 * 강의실 수용인원에 대한 데이터를 내보내는 메소드이다.
	 * @return 강의실 수용인원
	 */
	public String getLectureRoomNumber() {
		return lectureRoomNumber;
	}
	
	/**
	 * 강의실 수용인원에 대한 데이터를 받는 메소드이다.
	 * @param acceptablePersonnel 강의실 수용인원
	 */
	public void setLectureRoomNumber(String lectureRoomNumber) {
		this.lectureRoomNumber = lectureRoomNumber;
	}
	

}
