package com.test.admin;


/**
 * 
 * 조번호의 정보를 저장하는 DTO클래스이다.
 *	 teamSeq; 	// 조번호
 * @author 이은규
 */

public class TeamDTO {
	
	private String teamSeq; //조번호

	/**
	 * 조번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 조번호
	 */
	
	public String getTeamSeq() {
		return teamSeq;
	}

	/**
	 * 교육생 번호에 대한 데이터를 받는 메소드이다.
	 * @param teamSeq 조번호
	 */
	
	public void setTeamSeq(String teamSeq) {
		this.teamSeq = teamSeq;
	}
	

}
