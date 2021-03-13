package com.test.student;

/**
 * 학생의 세부 진도표 데이터를 담당하는 DTO입니다.
 * @author User
 *
 */
public class StudentWeeklyProgressDTO {
	
	private String seq;
	private String weeklyProgressSeq;
	private String openCourseSeq;
	private String openSubjectSeq;
	private String weeklyProgressDate;
	private String lectureContent;
	private String subjectProgress;
	private String studentSeq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getWeeklyProgressSeq() {
		return weeklyProgressSeq;
	}
	public void setWeeklyProgressSeq(String weeklyProgressSeq) {
		this.weeklyProgressSeq = weeklyProgressSeq;
	}
	public String getOpenCourseSeq() {
		return openCourseSeq;
	}
	public void setOpenCourseSeq(String openCourseSeq) {
		this.openCourseSeq = openCourseSeq;
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
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	
}
