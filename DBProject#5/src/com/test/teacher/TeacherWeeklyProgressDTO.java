package com.test.teacher;

public class TeacherWeeklyProgressDTO {

	
	private String weeklyProgressSeq;
	private String openSubjectSeq;
	private String weeklyProgressDate;
	private String lectureContent;
	private String subjectProgress;
	
	private String teacherSeq;
//	private String wpdate;
	
	
	public String getWeeklyProgressSeq() {
		return weeklyProgressSeq;
	}
	public void setWeeklyProgressSeq(String weeklyProgressSeq) {
		this.weeklyProgressSeq = weeklyProgressSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getWeeklyProgressDate() {
		return weeklyProgressDate;
	}
	public void setWeeklyProgressDate(String weeklyProgressDate) {
		this.weeklyProgressDate = weeklyProgressDate;
	}
	public String getLectureContent() {
		return lectureContent;
	}
	public void setLectureContent(String lectureContent) {
		this.lectureContent = lectureContent;
	}
	public String getSubjectProgress() {
		return subjectProgress;
	}
	public void setSubjectProgress(String subjectProgress) {
		this.subjectProgress = subjectProgress;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
//	public String getWpdate() {
//		return wpdate;
//	}
//	public void setWpdate(String wpdate) {
//		this.wpdate = wpdate;
//	}
}
