package com.test.teacher;

/**
 * 교사가 담당한 과정의 과목의 배점을 가져오는 DTO입니다.
 * @author User
 *
 */
public class TeacherPointDTO {
	
	private String seq;
	private String courseSeq;
	private String courseName;
	private String subjectSeq;
	private String openSubjectSeq;
	private String subjectName;
	private String courseBegin;
	private String openSubjectBegin;
	private String teacherSeq;
	private String pointSeq;
	private String pointAttendance;
	private String pointHandWriting;
	private String pointPractical;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseBegin() {
		return courseBegin;
	}
	public void setCourseBegin(String courseBegin) {
		this.courseBegin = courseBegin;
	}
	public String getOpenSubjectBegin() {
		return openSubjectBegin;
	}
	public void setOpenSubjectBegin(String openSubjectBegin) {
		this.openSubjectBegin = openSubjectBegin;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getPointSeq() {
		return pointSeq;
	}
	public void setPointSeq(String pointSeq) {
		this.pointSeq = pointSeq;
	}
	public String getPointAttendance() {
		return pointAttendance;
	}
	public void setPointAttendance(String pointAttendance) {
		this.pointAttendance = pointAttendance;
	}
	public String getPointHandWriting() {
		return pointHandWriting;
	}
	public void setPointHandWriting(String pointHandWriting) {
		this.pointHandWriting = pointHandWriting;
	}
	public String getPointPractical() {
		return pointPractical;
	}
	public void setPointPractical(String pointPractical) {
		this.pointPractical = pointPractical;
	}

}
