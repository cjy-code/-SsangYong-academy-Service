package com.test.student;

public class ProjectDTO {
	
	
	
	//교육생에 해당하는 과목 리스트 DTO
		private String opensubjectseq;
		private String subjectname;
		
	//특정 과목 프로젝트 리스트 DTO		
		
		private String projectListSeq;
		private String projectName;
		private String projectDeadLine;
		private String teamSeq;
		
	//프로젝트 피트백 DTO
		private String coursename;
		private String feedbackcontents;
		
		
		
		public String getCoursename() {
			return coursename;
		}
		public void setCoursename(String coursename) {
			this.coursename = coursename;
		}
		public String getFeedbackcontents() {
			return feedbackcontents;
		}
		public void setFeedbackcontents(String feedbackcontents) {
			this.feedbackcontents = feedbackcontents;
		}
		public String getTeamSeq() {
			return teamSeq;
		}
		public void setTeamSeq(String teamSeq) {
			this.teamSeq = teamSeq;
		}
		public String getProjectListSeq() {
			return projectListSeq;
		}
		public void setProjectListSeq(String projectListSeq) {
			this.projectListSeq = projectListSeq;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public String getProjectDeadLine() {
			return projectDeadLine;
		}
		public void setProjectDeadLine(String projectDeadLine) {
			this.projectDeadLine = projectDeadLine;
		}
		public String getOpensubjectseq() {
			return opensubjectseq;
		}
		public void setOpensubjectseq(String opensubjectseq) {
			this.opensubjectseq = opensubjectseq;
		}
		public String getSubjectname() {
			return subjectname;
		}
		public void setSubjectname(String subjectname) {
			this.subjectname = subjectname;
		}

		
		
}
