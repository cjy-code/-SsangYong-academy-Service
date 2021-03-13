package com.test.admin;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.main.Main;
import com.test.main.*;
/**
 * 관리자의 모든 기능을 컨트롤 할수 있는 클래스이다.
 * 기초정보관리,교사계정관리,개설과정관리,개설과목관리,교육생관리
 * 시험관리 및 성적 조회,출결 관리 및 출결 조회를 컨트롤할 수있다.
 *
 */

public class AdminController {
	
		
		private static Scanner scan;
		private static AdminView view;
		
		private static CourseDAO cdao;
		private static SubjectDAO sdao;
		private static BookDAO bdao;
		private static LectureRoomDAO lrdao;
		private static TeacherDAO tcdao;
		private static StudentDAO2 stdao;

		private static String opnum;
		
		static {
			opnum = "";
		}
		
		/**
		 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
		 */
		static {
			
			scan = new Scanner(System.in);
			view = new AdminView();
			
			cdao = new CourseDAO();
			sdao = new SubjectDAO();
			bdao = new BookDAO();
			lrdao = new LectureRoomDAO();
			tcdao = new TeacherDAO();
			stdao = new StudentDAO2();
			//ocdao = new OpenCourseDAO();
			
//			osdao = new OpenSubjectDAO();
//			edao = new ExamDAO();
//			adao = new AttendanceDAO();
//			tedao = new TeacherEvaluationDAO();
			
		}
		
		/**
		 * 관리자 메인 메뉴를 보여준다.
		 */
		public static void start() {
			//관리자 로그인
			boolean loop = true;
			while (loop) {
				
				view.mainMenu();
				Scanner sc = new Scanner(System.in);
				
				String sel = sc.nextLine();
				if (sel.equals("1")) { basicDataManagement(); } //기초 정보 관리 -
				else if (sel.equals("2")) { teacherAccountManagement(); } //교사 계정 관리 -
				else if (sel.equals("3")) { StudentMng.start(); } //교육생 계정 관리 
				else if (sel.equals("4")) { Admin_OpenCourseView.openCourseManagement(); } //개설 과정 관리 
				else if (sel.equals("5")) { Admin_OpenSubjectView.openSubjectManagement(); } //개설 과목 관리 
				else if (sel.equals("6")) { ExamRecord.start(); } //시험 및 성적 관리
				else if (sel.equals("7")) { Attendance.AttendanceMain(); } //출결 조회 및 근태 관리
				else if (sel.equals("0")) { Main.main(null);} //출결 조회 및 근태 관리
				
				else { loop = false; }
			}
			
			//관리자 로그아웃
		}
		

		
		



		//기초 정보 관리
		private static void basicDataManagement() {
			
			boolean loop = true;
			
			while (loop) {
				
				view.chooseBasicDataManagement();
				Scanner sc = new Scanner(System.in);
				
				String sel = sc.nextLine();
				if (sel.equals("1")) { courseManagement(); } //과정 관리
				else if (sel.equals("2")) { subjectManagement(); } //과목 관리
				else if (sel.equals("3")) { bookManagement(); } // 교재 관리
				else if (sel.equals("4")) { lectureRoomManagement(); } //강의실 관리
				else { loop = false; }
			}
		}

		
		
		
		//과정 관리
		private static void courseManagement() {
			
			//전체 과정
			ArrayList<CourseDTO> result = cdao.courseList();
			
			boolean loop = true;
			while (loop) {
				
				view.chooseCourseMenu(result);
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addCourseMenu(); } //과정 등록
				else if (sel.equals("2")) { updateCourseMenu(); } //과정 수정
				else if (sel.equals("3")) { deleteCourseMenu(); } //과정 삭제
				else { loop = false; }
			}
		}

		//과정 등록 메뉴
		private static void addCourseMenu() {
			
			//과정 등록 메뉴 헤더
			view.addCourseMenuHeader();
			
			System.out.print("과정명 : ");
			String courseName = scan.nextLine();
			
			System.out.print("기간 : ");
			String courseTerm = scan.nextLine();
			
			CourseDTO cdto = new CourseDTO(); //상자
			cdto.setCourseName(courseName);
			cdto.setCourseTerm(courseTerm);
			
			boolean loop = true;
			while (loop) {
				
				view.addCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addCourse(cdto); return; } //과정 등록
				else { loop = false; }
			}
			
		}

		//과정 등록
		private static void addCourse(CourseDTO cdto) {
			
			//쿼리
			int result = cdao.addCourse(cdto);
			view.addResult(result);
			
		}

		//과정 수정
		private static void updateCourseMenu() {
			
			//과정 수정 헤더
			view.updateCourseMenuHeader();
			
			//전체 과정
			ArrayList<CourseDTO> result = cdao.courseList();
			view.courseList(result);
			
			System.out.print("과정 번호 : ");
			String courseSeq = scan.nextLine(); //과정 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.chooseUpdateCourse();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { updateCourseName(courseSeq); return; } //과정명 수정
				if (sel.equals("2")) { updateCourseTerm(courseSeq); return; } //기간 수정
				else { loop = false; }
			}
			
		}

		//과정명 수정
		private static void updateCourseName(String courseSeq) {
			
			//과정명 수정 헤더
			view.updateCourseNameHeader();
			
			System.out.print("과정명 : ");
			String courseName = scan.nextLine(); //과정명 입력
			
			//쿼리
			int result = cdao.updateCourse(courseSeq, courseName, "");
			view.updateResult(result);

		}

		//기간 수정
		private static void updateCourseTerm(String processSeq) {
			
			//기간 수정 헤더
			view.updateCourseTermHeader();
			
			System.out.print("기간(일) : ");
			String courseTerm = scan.nextLine(); //기간(일) 입력
			
			//쿼리
			int result = cdao.updateCourse(processSeq, "", courseTerm);
			view.updateResult(result);

			
		}

		//과정 삭제 메뉴
		private static void deleteCourseMenu() {
			
			//과정 삭제 헤더
			view.deleteCourseMenuHeader();
			
			//전체 과정
			ArrayList<CourseDTO> result = cdao.courseList();
			view.courseList(result);
			
			System.out.print("과정 번호 : ");
			String courseSeq = scan.nextLine(); //과정 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.deleteCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { deleteCourse(courseSeq); return; } //과정 삭제
				else { loop = false; }
			}
			
		}
		
		//과정 삭제
		private static void deleteCourse(String courseSeq) {
			
			//쿼리
			int result = cdao.deleteCourse(courseSeq);
			view.deleteResult(result);
			
		}
		
		
		
		
		
		

		
		
		//과목 관리
		private static void subjectManagement() {
			
			//전체 과목
			ArrayList<SubjectDTO> result = sdao.subjectList();
			
			boolean loop = true;
			while (loop) {
				
				view.chooseSubjectMenu(result);
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addSubjectMenu(); } //과목 등록
				else if (sel.equals("2")) { updateSubjectMenu(); } //과목 수정
				else if (sel.equals("3")) { deleteSubjectMenu(); } //과목 삭제
				else { loop = false; }
			}
		}

		//과목 등록 메뉴
		private static void addSubjectMenu() {
			
			view.addSubjectMenuHeader();
			
			System.out.print("과목명 : ");
			String subjectName = scan.nextLine();
			
			System.out.print("기간 : ");
			String subjectTerm = scan.nextLine();
			
			System.out.print("책번호 : ");
			String bookSeq = scan.nextLine();
			
			SubjectDTO sdto = new SubjectDTO(); //상자
			sdto.setBookSeq(bookSeq);
			sdto.setSubjectName(subjectName);
			sdto.setSubjectTerm(subjectTerm);
			
			boolean loop = true;
			while (loop) {
				
				view.addCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addSubject(sdto); return; } //과목 등록
				else { loop = false; }
			}
			
		}

		//과목 등록
		private static void addSubject(SubjectDTO sdto) {
			
			//쿼리
			int result = sdao.addSubject(sdto);
			view.addResult(result);
			
		}
		
		//과목 수정 메뉴
		private static void updateSubjectMenu() {
			
			//과목 수정 메뉴 헤더
			view.updateSubjectMenuHeader();
			
			//전체 과목 출력
			ArrayList<SubjectDTO> result = sdao.subjectList();
			view.subjectList(result);
			
			System.out.print("과목 번호 : ");
			String subjectSeq = scan.nextLine(); //과목 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.chooseUpdateSubject();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { updateSubjectName(subjectSeq); return; } //과목명 수정
				if (sel.equals("2")) { updateSubjectTerm(subjectSeq); return; } //기간 수정
				else { loop = false; }
			}
			
		}

		//과목명 수정
		private static void updateSubjectName(String subjectSeq) {
			
			//과목명 수정 헤더
			view.updateSubjectTitleHeader();
			
			System.out.print("과목명 : ");
			String subjectName = scan.nextLine(); //과목명 입력
			
			//쿼리
			int result = sdao.updateSubject(subjectSeq,"", subjectName, "");
			view.updateResult(result);
			
		}

		//기간 수정
		private static void updateSubjectTerm(String subjectSeq) {
			
			//기간 수정 헤더
			view.updateSubjectTermHeader();
			
			System.out.print("기간(일) : ");
			String subjectTerm = scan.nextLine(); //기간(일) 입력
			
			//쿼리
			int result = sdao.updateSubject(subjectSeq, "",  "", subjectTerm);
			view.updateResult(result);
			
		}

		
		//과목 삭제 메뉴
		private static void deleteSubjectMenu() {
			
			//과목 삭제 헤더
			view.deleteSubjectMenuHeader();
			
			//전체 과목
			ArrayList<SubjectDTO> result = sdao.subjectList();
			view.subjectList(result);
			
			System.out.print("과목 번호 : ");
			String subjectSeq = scan.nextLine(); //과목 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.deleteCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { deleteSubject(subjectSeq); return; } //과목 삭제
				else { loop = false; }
			}
			
		}
		
		//과목 삭제
		private static void deleteSubject(String subjectSeq) {
			
			//쿼리
			int result = sdao.deleteSubject(subjectSeq);
			view.deleteResult(result);
				
		}
		
		
		
		
		
		
		//교재 관리
		private static void bookManagement() {
			
			//전체 교재
			ArrayList<BookDTO> result =  bdao.bookList();
			
			boolean loop = true;
			while (loop) {
				
				view.chooseBookMenu(result);
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addBookMenu(); } //교재 등록
				else if (sel.equals("2")) { updateBookMenu(); } //교재 수정
				else if (sel.equals("3")) { deleteBookMenu(); } //교재 삭제
				else { loop = false; }
			}
		}

		//교재 등록 메뉴
		private static void addBookMenu() {
			
			view.addBookMenuHeader();
			
			System.out.print("교재명 : ");
			String bookTitle = scan.nextLine();
			
			System.out.print("출판사 : ");
			String bookPublisher = scan.nextLine();
			
			BookDTO bdto = new BookDTO(); //상자
			bdto.setBookTitle(bookTitle);
			bdto.setBookPublisher(bookPublisher);
			
			boolean loop = true;
			while (loop) {
				
				view.addCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addBook(bdto); return; } //교재 등록
				else { loop = false; }
			}
			
		}

		//교재 등록
		private static void addBook(BookDTO bdto) {
			
			//쿼리
			int result = bdao.addBook(bdto);
			view.addResult(result);
			
		}
		
		//교재 수정 메뉴
		private static void updateBookMenu() {
			
			//교재 수정 헤더
			view.updateBookMenuHeader();
			
			//전체 교재 출력
			ArrayList<BookDTO> result = bdao.bookList();
			view.allBookList(result);
			
			System.out.print("교재 번호 : ");
			String bookSeq = scan.nextLine(); //교재 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.chooseUpdateBook();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { updateBookTitle(bookSeq); return;} //교재명 수정
				if (sel.equals("2")) { updateBookPublisher(bookSeq); return;} //출판사 수정
				else { loop = false; }
			}
			
		}

		//교재명 수정
		private static void updateBookTitle(String bookSeq) {
			
			//교재명 수정 헤더
			view.updateBookTitleHeader();
			
			System.out.print("교재명 : ");
			String bookTitle = scan.nextLine(); //교재명 입력
			
			//쿼리
			int result = bdao.updateBook(bookSeq, bookTitle, "");
			view.updateResult(result);

		}

		//출판사 수정
		private static void updateBookPublisher(String bookSeq) {
			
			//출판사 수정 헤더
			view.updateBookPublisherHeader();
			
			System.out.print("출판사명 : ");
			String bookPublisher = scan.nextLine(); //기간(일) 입력
			
			//쿼리
			int result = bdao.updateBook(bookSeq, "", bookPublisher);
			view.updateResult(result);

		}

		
		//교재 삭제 메뉴
		private static void deleteBookMenu() {
			
			//교재 삭제 헤더
			view.deleteBookMenuHeader();
			
			//전체 교재
			ArrayList<BookDTO> result = bdao.bookList();
			view.allBookList(result);
			
			System.out.print("교재 번호 : ");
			String bookSeq = scan.nextLine(); //교재 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.deleteCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { deleteBook(bookSeq); return; } //교재 삭제
				else { loop = false; }
			}
			
		}
		
		//교재 삭제
		private static void deleteBook(String bookSeq) {
			
			//쿼리
			
			int result = bdao.deleteBook(bookSeq);
			view.deleteResult(result);
				
		}
		
		
		
		
		
		
		
		
		
		
		
		

		//강의실 관리
		private static void lectureRoomManagement() {
			
			//전체 강의실
			ArrayList<LectureRoomDTO> result =  lrdao.allLectureRoomList();
			
			boolean loop = true;
			while (loop) {
				
				view.chooseLectureRoomMenu(result);
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addLectureRoomMenu(); } //강의실 등록
				else if (sel.equals("2")) { updateLectureRoomMenu(); } //강의실 수정
				else if (sel.equals("3")) { deleteLectureRoomMenu(); } //강의실 삭제
				else { loop = false; }
			}
		}

		//강의실 등록 메뉴
		private static void addLectureRoomMenu() {
			
			//강의실 등록 메뉴 헤더
			view.addLectureRoomMenuHeader();
			
			System.out.print("강의실명 : ");
			String lectureRoomName = scan.nextLine();
			
			System.out.print("수용인원 : ");
			String lectureRoomNumber = scan.nextLine();
			
			LectureRoomDTO lrdto = new LectureRoomDTO(); //상자
			lrdto.setLectureRoomName(lectureRoomName);
			lrdto.setLectureRoomNumber(lectureRoomNumber);
			
			boolean loop = true;
			while (loop) {
				
				view.addCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addLectureRoom(lrdto); return; } //강의실 등록
				else { loop = false; }
			}
			
		}

		//강의실 등록
		private static void addLectureRoom(LectureRoomDTO lrdto) {
			
			//쿼리
			int result = lrdao.addLecture(lrdto);
			view.addResult(result);
			
		}
		
		//강의실 수정 메뉴
		private static void updateLectureRoomMenu() {
			
			//강의실 수정 헤더
			view.updateLectureRoomMenuHeader();
			
			//전체 강의실 출력
			ArrayList<LectureRoomDTO> result = lrdao.allLectureRoomList();
			view.lectureRoomList(result);
			
			System.out.print("강의실 번호 : ");
			String lectureRoomSeq = scan.nextLine(); //강의실 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.chooseUpdateLectureRoom();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { updateLectureRoomName(lectureRoomSeq); return; } //강의실명 수정
				if (sel.equals("2")) { updateLectureRoomNumber(lectureRoomSeq); return; } //수용인원수 수정
				else { loop = false; }
			}
			
		}

		//강의실명 수정
		private static void updateLectureRoomName(String lectureRoomSeq) {		
			
			//강의실명 수정 헤더
			view.updateLectureRoomNameHeader();
			
			System.out.print("강의실명 : ");
			String lectureRoomName = scan.nextLine(); //강의실명 입력
			
			//쿼리
			int result = lrdao.updateLectureRoom(lectureRoomSeq, lectureRoomName, "");
			view.updateResult(result);

		}

		//수용인원수 수정
		private static void updateLectureRoomNumber(String lectureRoomSeq) {
			
			//수용인원수 수정 헤더
			view.updateLectureRoomNumberHeader();
			
			System.out.print("수용인원 : ");
			String lectureRoomNumber = scan.nextLine(); //수용인원수 입력
			
			//쿼리
			int result = lrdao.updateLectureRoom(lectureRoomSeq, "", lectureRoomNumber);
			view.updateResult(result);

		}

		
		//강의실 삭제 메뉴
		private static void deleteLectureRoomMenu() {
			
			//강의실 삭제 헤더
			view.deleteLectureRoomMenuHeader();
			
			//전체 강의실
			ArrayList<LectureRoomDTO> result = lrdao.allLectureRoomList();
			view.lectureRoomList(result);
			
			System.out.print("강의실 번호 : ");
			String lectureRoomSeq = scan.nextLine(); //강의실 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.deleteCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { deleteLectureRoom(lectureRoomSeq); return; } //강의실 삭제
				else { loop = false; }
			}
			
		}
		
		//강의실 삭제
		private static void deleteLectureRoom(String lectureRoomSeq) {
			
			//쿼리
			int result = lrdao.deleteLectureRoom(lectureRoomSeq);
			view.deleteResult(result);
				
		}

		
		
		
		
		
		


		//교사 계정 관리
		private static void teacherAccountManagement() {
			
			//전체 교사
			ArrayList<TeacherDTO> result =  tcdao.allTeacherList();
			
			boolean loop = true;
			while (loop) {
				
				view.chooseTeacherMenu(result);
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addTeacherMenu(); } //교사 등록
				else if (sel.equals("2")) { updateTeacherMenu(); } //교사 수정
				else if (sel.equals("3")) { deleteTeacherMenu(); } //교사 삭제
				else if (sel.equals("4")) { selectSpecificTeacherMenu(); } //특정 교사 조회
				else { loop = false; }
			}
		}

		//교사 등록 메뉴
		private static void addTeacherMenu() {
			
			view.addTeacherMenuHeader();
			
			System.out.print("교사명 : ");
			String teacherName = scan.nextLine();
			
			System.out.print("전화번호 : ");
			String teacherTel = scan.nextLine();
			
			System.out.print("주민번호 뒷자리 : ");
			String teacherSsn = scan.nextLine();

			
			TeacherDTO tdto = new TeacherDTO(); //상자
			tdto.setTeacherName(teacherName);
			tdto.setTeacherTel(teacherTel);
			tdto.setTeacherSsn(teacherSsn);
			

			//전체 과목 출력
			ArrayList<SubjectDTO> subjects = sdao.subjectList();
			view.subjectList(subjects);
			
			System.out.println("<<과목리스트 중 해당 교사가 강의 가능한 과목을 입력하세요.");
			System.out.print("강의 가능 과목(,로 다중 선택) : ");
			String[] availableSubject = scan.nextLine().split(",");
			
			
			boolean loop = true;
			while (loop) {
				
				view.addCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { addTeacher(tdto, availableSubject); return; } //교사 등록
				else { loop = false; }
			}
			
		}

		//교사 등록
		private static void addTeacher(TeacherDTO tadto, String[] availableSubject) {
			
			//쿼리
			int result = tcdao.addTeacher(tadto, availableSubject);
			view.addResult(result);
		}
		
		//교사 정보 수정 메뉴
		private static void updateTeacherMenu() {
			
			//교사 수정 헤더
			view.updateTeacherMenuHeader();
			
			//전체 교사 출력
			ArrayList<TeacherDTO> result = tcdao.allTeacherList();
			view.teacherList(result);
			
			System.out.print("교사 번호 : ");
			String teacherSeq = scan.nextLine(); //교사 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.chooseUpdateTeacher();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { updateTeacherName(teacherSeq); return; } //교사명 수정
				if (sel.equals("2")) { updateTeacherTel(teacherSeq); return; } //전화번호 수정
				if (sel.equals("3")) { updateTeacherSsn(teacherSeq); return; } //주민번호 뒷자리 수정

				else { loop = false; }
			}
			
		}

		//교사명 수정
		private static void updateTeacherName(String teacherSeq) {
			
			//교사명 수정 헤더
			view.updateTeacherNameHeader();
			
			System.out.print("교사명 : ");
			String teacherName = scan.nextLine(); //교사명 입력
			
			//쿼리	
			int result = tcdao.updateTeacher(teacherSeq, teacherName, "", "");
			view.updateResult(result);

		}

		//교사 - 전화번호 수정
		private static void updateTeacherTel(String teacherSeq) {
			
			//전화번호 수정 헤더
			view.updateTeacherTelHeader();
			
			System.out.print("전화번호 : ");
			String teacherTel = scan.nextLine(); //전화번호 입력
			
			//쿼리		
			int result = tcdao.updateTeacher(teacherSeq, "", teacherTel, "");
			view.updateResult(result);
		}
		
		//교사 주민번호 뒷자리 수정
		private static void updateTeacherSsn(String teacherSeq) {
			
			//교사 주민번호 뒷자리 수정 헤더
			view.updateTeacherSsnHeader();
			
			System.out.print("주민번호 뒷자리 : ");
			String teacherSsn = scan.nextLine(); //주민번호 뒷자리 입력
			
			//쿼리
			int result = tcdao.updateTeacher(teacherSeq, "", "",  teacherSsn);
			view.updateResult(result);
		}


		
		//교사 삭제 메뉴
		private static void deleteTeacherMenu() {
			
			//교재 삭제 헤더
			view.deleteTeacherMenuHeader();
			
			//전체 교사
			ArrayList<TeacherDTO> result = tcdao.allTeacherList();
			view.teacherList(result);
			
			System.out.print("교사 번호 : ");
			String teacherSeq = scan.nextLine(); //교사 번호 선택
			
			boolean loop = true;
			while (loop) {
				
				view.deleteCheckMenu();
				
				String sel = scan.nextLine();
				if (sel.equals("1")) { deleteTeacher(teacherSeq); return; } //교사 삭제
				else { loop = false; }
			}
			
		}
		
		//교사 삭제
		private static void deleteTeacher(String teacherSeq) {
			
			//쿼리
			int result = tcdao.deleteTeacher(teacherSeq);
			view.deleteResult(result);
				
		}

		//특정 교사 조회 메뉴
		private static void selectSpecificTeacherMenu() {
			
			//특정 교사 조회 메뉴 헤더
			view.selectSpecificTeacherMenuHeader();
			
			System.out.print("교사번호(숫자) : ");
			String teacherSeq = scan.nextLine();
			
			ArrayList<OpenCourseDTO> teacherOpenCourse = tcdao.openCourseList(teacherSeq);
			view.openCourseList(teacherOpenCourse);
			
		}
		
		
		
		public static int checkTitle(String str, int length) {
			
			int result = length;
			
			for (int i = 0; i < str.length(); i++) {
				
				if (str.charAt(i) >= '가' && str.charAt(i) <= '힣') {
					
					result--;
					
				}
			}
			
			return result;
		
		}
		
		
		
		
		
		
		
	
}

