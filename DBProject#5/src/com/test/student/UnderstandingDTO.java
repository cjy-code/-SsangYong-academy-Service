package com.test.student;

/**
 * 교육생 수업 이해도 DTO
 * @author 최진영
 *
 */
public class UnderstandingDTO {

	
	//교육생에 해당하는 과목 리스트 DTO
	private String opensubjectseq;
	private String subjectname;
	
	
	//특정 과목 주간 진도표 리스트 DTO
	private String  weeklyprogressseq;
	private String  weeklyprogressdate;
	private String  lecturecontent;
	private String  subjectprogress;
	
	//이해도 수치 입력
	private String weeklyProgressSeq;
	private String classSeq;
	private String understandingNum;
	
	
	
	/**
	 * 주간 진도 번호
	 * @return weeklyprogressseq
	 */
	public String getWeeklyprogressseq() {
		return weeklyprogressseq;
	}
	
	/**
	 * 주간 진도 번호
	 * @param weeklyprogressseq
	 */
	public void setWeeklyprogressseq(String weeklyprogressseq) {
		this.weeklyprogressseq = weeklyprogressseq;
	}
	
	/**
	 * 주간 진도 날짜
	 * @return weeklyprogressdate
	 */
	public String getWeeklyprogressdate() {
		return weeklyprogressdate;
	}
	
	/**
	 * 주간 진도 날짜
	 * @param weeklyprogressdate
	 */
	public void setWeeklyprogressdate(String weeklyprogressdate) {
		this.weeklyprogressdate = weeklyprogressdate;
	}
	
	/**
	 * 수업 내용
	 * @return lecturecontent 
	 */
	public String getLecturecontent() {
		return lecturecontent;
	}
	
	/**
	 * 수업 내용
	 * @param lecturecontent
	 */
	public void setLecturecontent(String lecturecontent) {
		this.lecturecontent = lecturecontent;
	}
	
	/**
	 * 과목별 진행률 
	 * @return subjectprogress
	 */
	public String getSubjectprogress() {
		return subjectprogress;
	}
	
	/**
	 * 과목별 진행률
	 * @param subjectprogress
	 */
	public void setSubjectprogress(String subjectprogress) {
		this.subjectprogress = subjectprogress;
	}
	
	/**
	 * 과목 번호
	 * @returnopensubjectseq
	 */
	public String getOpensubjectseq() {
		return opensubjectseq;
	}
	
	/**
	 * 과목 번호
	 * @param opensubjectseq
	 */
	public void setOpensubjectseq(String opensubjectseq) {
		this.opensubjectseq = opensubjectseq;
	}
	
	/**
	 * 과목 이름
	 * @return subjectname
	 */
	public String getSubjectname() {
		return subjectname;
	}
	
	/**
	 * 과목 이름
	 * @param subjectname
	 */
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	
	
	
	
	
}
