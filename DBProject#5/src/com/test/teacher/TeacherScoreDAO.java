package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//import com.test.jdbc.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherScoreDAO {

	private Connection conn;
	private Statement stat; //매개변수x
	private PreparedStatement pstat; //매개변수o
	private CallableStatement cstat; //프로시저로 가져올 때
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public TeacherScoreDAO() {
		
	
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.TeacherScoreDAO()");
			e.printStackTrace();
		}
		
	}

	public int add(String openSubjectSeq, String classSeq, String scoreAttendance, String scoreHandWriting, String scorePractical) {
		
		return 0;
	}

	public int add(TeacherScoreDTO sdto) {
		
		try {
			
			String sql = "INSERT INTO tblScore (scoreSeq,openSubjectSeq,classSeq,scoreAttendance,scoreHandWriting,scorePractical) VALUES (scoreSeq.nextVal,?,?,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sdto.getOpenSubjectSeq());
			pstat.setString(2, sdto.getClassSeq());
			pstat.setString(3, sdto.getScoreAttendance());
			pstat.setString(4, sdto.getScoreHandWriting());
			pstat.setString(5, sdto.getScorePractical());
			
			return pstat.executeUpdate(); //성공하면 1반환
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}

	public int addAtdScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procAddScoreAtd(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScoreAttendance());		
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.addAtdScore()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int EditAtdScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procAddScoreAtd(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScoreAttendance());		
			cstat.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.EditAtdScore()");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	public int DeleteAtdScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procDeleteScoreAtd(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.executeQuery();
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.DeleteAtdScore()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int addHWScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procAddScoreHw(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScoreHandWriting());		
			cstat.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.addHWScore()");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	public int EditHWScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procAddScoreHw(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScoreHandWriting());		
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.EditHWScore()");
			e.printStackTrace();
		}
		return 0;
	}

	public int DeleteHWScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procDeleteScoreHw(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.DeleteHWScore()");
			e.printStackTrace();
		}
		return 0;
	}

	public int addPrScore(TeacherScoreDTO dto) {
		
		try {
			
			String sql = "{ call procAddScorePrt(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScorePractical());		
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.addPrScore()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int EditPrScore(TeacherScoreDTO dto) {
		
		try {
			String sql = "{ call procAddScorePrt(?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.setString(4, dto.getScorePractical());		
			cstat.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.EditPrScore()");
			e.printStackTrace();
		}
		return 0;
	}

	public int DeletePrScore(TeacherScoreDTO dto) {
		
		try {
			
			String sql = "{ call procDeleteScorePrt(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getTeacherSeq()); 
			cstat.setString(2, dto.getOpenSubjectSeq());
			cstat.setString(3, dto.getClassSeq());
			cstat.executeQuery();
			
		} catch (Exception e) {
			System.out.println("TeacherScoreDAO.DeletePrScore()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private static Scanner scan;
	
//	public String teacherCourseCheck(String teacherNum) {
//		
//		TeacherDTO dto = new TeacherDTO(teacherNum);
//		
//		Connection conn = null;
//		CallableStatement cstat = null;
		
//		try {
//			
//			conn = DBUtil.open();
//			
//			String sql = "{ call procCourseCheck(?, ?) }";
//			
//			cstat = conn.prepareCall(sql);
//				
//				System.out.println("교사번호출력테스트:" + teacherNum); //NULL값이 출력된다..
//				System.out.print("==============================================================================\n");
//				System.out.println("                          담당 개설 과정 목록 조회");
//				System.out.print("==============================================================================\n");
//				System.out.println("[개설과정번호]\t[과정명]\t[과정시작일시]\t[과정종료일시]\t[강의실]\t[학생수]\t[과정진행상태]");
//				
////				cstat.setString(1, dto.getTeacherSeq()); ///방법 찾기
//				cstat.setString(1, "1");
//				cstat.registerOutParameter(2, OracleTypes.CURSOR);
//				
//				cstat.executeQuery();
//				ResultSet rs = (ResultSet)cstat.getObject(2);
//
//				
//				while (rs.next()) {
//					System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n",rs.getString("개설과정번호"),rs.getString("과정명"),rs.getString("과정시작일시"),rs.getString("과정종료일시"),rs.getString("강의실"),rs.getString("학생수"),rs.getString("과정진행상태"));
//					
//				}
//				
//				boolean loop = true;
//				
//				while (loop) {
//					
//					System.out.print("==============================================================================\n");
//					System.out.print("1.담당 과정 과목 목록 조회\n");
//					System.out.print("0. 뒤로가기\n");
//					System.out.print("선택: ");
//					String num = scan.nextLine();
//					System.out.println();
//					
//					if (num.equals("1")) {
//						System.out.println();
//						System.out.print("개설 과정 번호: ");
//						String openCourseSeq = scan.nextLine();
//						teacherSubjectCheck(teacherNum, openCourseSeq);
//						loop = false;
//					} else {
//						TeacherMain.teacherMain(teacherNum);
//					}
//				}
//				
//				rs.close();
//				cstat.close();
//				conn.close();
//				
//			
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherScore.teacherScore()");
//			e.printStackTrace();
//		}
//		
//		return null;
//		
//	}
//	
//	
//	private String teacherSubjectCheck(String teacherNum, String openCourseSeq) {
//		
//		TeacherDTO dto = new TeacherDTO(teacherNum);
//		
//		Connection conn = null;
//		CallableStatement cstat = null;
//		
//		
//		try {
//			
//			conn = DBUtil.open();
//			
//			String sql = "{ call procSubjectCheck(?, ?, ?) }";
//			
//			cstat = conn.prepareCall(sql);
//			
//			System.out.println();
//			System.out.print("==============================================================================\n");
//			System.out.println("                       개설 과정 과목 목록 조회");
//			System.out.print("==============================================================================\n");
//			System.out.println("[개설과목번호]\t[과목명]\t[과목시작일시]\t[과목종료일시]\t[강의실]\t[교재명]\t[출결배점]\t[필기배점]\t[실기배점]\t[성적등록여부]\t\n");
//			
//			cstat.setString(1, teacherNum); ///방법 찾기
////			cstat.setString(1, "1");
//			cstat.setString(2, openCourseSeq);
//
//			cstat.registerOutParameter(3, OracleTypes.CURSOR);
//			
//			cstat.executeQuery();
//			ResultSet rs = (ResultSet)cstat.getObject(3);
//			
//			
//			while (rs.next()) {
//				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n",rs.getString("개설과목번호"),rs.getString("과목명"),rs.getString("과목시작일시"),rs.getString("과목종료일시"),rs.getString("강의실"),rs.getString("교재명"),rs.getString("출결배점"),rs.getString("필기배점"),rs.getString("실기배점"),rs.getString("성적등록여부"));
//				
//			}
//			
//			
//			boolean loop = true;
//			
//			while (loop) {
//				
//				System.out.print("==============================================================================\n");
//				System.out.print("1.특정 과목 전체 교육생 성적 조회\n");
//				System.out.print("0. 뒤로가기\n");
//				System.out.print("선택: ");
//				String num = scan.nextLine();
//				System.out.println();
//				
//				if (num.equals("1")) {
//					System.out.println();
//					System.out.print("개설 과목 번호: ");
//					String openSubjectNum = scan.nextLine();
//					teacherAllStdScoreCheck(teacherNum, openSubjectNum);
//					loop = false;
//				} else {
//					TeacherMain.teacherMain(teacherNum);
//				}
//			}
//			
//			rs.close();
//			cstat.close();
//			conn.close();
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherScore.teacherSubjectCheck()");
//			e.printStackTrace();
//		}
//		return null;
//		
//
//		
//	}
//	
//	
//	
//	
//	private String teacherAllStdScoreCheck(String teacherNum, String openSubjectNum) {
//		
//		System.out.println();
//		Connection conn = null;
//		CallableStatement cstat = null;
//		
//		try {
//			conn = DBUtil.open();
//			
//			String sql = "{ call procStudentCheck(?, ?, ?) }";
//			
//			cstat = conn.prepareCall(sql);
//			
//			System.out.println();
//			System.out.print("==============================================================================\n");
//			System.out.println(                openSubjectNum+"과목 전체 교육생 성적 조회");
//			System.out.print("==============================================================================\n");
//			System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[수료상태]\t[수료및중도탈락날짜]\t[출결성적]\t[필기성적]\t[실기성적]\t\n");
//			
//			cstat.setString(1, teacherNum);
//			cstat.setString(2, openSubjectNum);
//
//			cstat.registerOutParameter(3, OracleTypes.CURSOR);
//			
//			cstat.executeQuery();
//			ResultSet rs = (ResultSet)cstat.getObject(3);
//			
//			
//			while (rs.next()) {
//				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n",rs.getString("교육생번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료상태"),rs.getString("수료및중도탈락날짜"),rs.getString("출결성적"),rs.getString("필기성적"),rs.getString("실기성적"));
//				
//			}
//			
//			boolean loop = true;
//			
//			while (loop) {
//				
//				System.out.print("==============================================================================\n");
//				System.out.print("1.특정 교육생 성적 조회\n");
//				System.out.print("0. 뒤로가기\n");
//				System.out.print("선택: ");
//				String num = scan.nextLine();
//				System.out.println();
//				
//				if (num.equals("1")) {
//					System.out.println();
//					System.out.print("교육생 번호: ");
//					String studentNum = scan.nextLine();
//					teacherOneStdScoreCheck(teacherNum, openSubjectNum, studentNum);
//					loop = false;
//				} else {
//					TeacherMain.teacherMain(teacherNum);
//				}
//			}
//			
//			rs.close();
//			cstat.close();
//			conn.close();
//			
//			
//			
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherScore.teacherAllStdScoreCheck()");
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
//	
//	
//	
//	private String teacherOneStdScoreCheck(String teacherNum, String openSubjectNum, String studentNum) {
//		
//		System.out.println();
//		Connection conn = null;
//		CallableStatement cstat = null;
//		
//		try {
//			
//			conn = DBUtil.open();
//			
//			String sql = "{ call procOneStudentCheck(?, ?, ?, ?) }";
//			
//			cstat = conn.prepareCall(sql);
//			
//			System.out.println();
//			System.out.print("==============================================================================\n");
//			System.out.println(                			"교육생 성적 조회");
//			System.out.print("==============================================================================\n");
//			System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[수료상태]\t[수료및중도탈락날짜]\t[출결성적]\t[필기성적]\t[실기성적]\t\n");
//			
//			cstat.setString(1, teacherNum);
//			cstat.setString(2, openSubjectNum);
//			cstat.setString(3, studentNum);
//
//			cstat.registerOutParameter(4, OracleTypes.CURSOR);
//			
//			cstat.executeQuery();
//			ResultSet rs = (ResultSet)cstat.getObject(4);
//			
//			
//			while (rs.next()) {
//				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n",rs.getString("교육생번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료상태"),rs.getString("수료및중도탈락날짜"),rs.getString("출결성적"),rs.getString("필기성적"),rs.getString("실기성적"));
//				
//			}
//			
//			
//			boolean loop = true;
//			
//			while (loop) {
//				
//				System.out.print("==============================================================================\n");
//				System.out.print("1.출결 점수 관리\n");
//				System.out.print("2.필기 점수 관리\n");
//				System.out.print("3.실기 점수 관리\n");
//				System.out.print("0. 뒤로가기\n");
//				System.out.print("선택: ");
//				String num = scan.nextLine();
//				System.out.println();
//				
//				if (num.equals("1")) {
//					System.out.println();
//					System.out.println("[출결 점수 관리]");
//					System.out.println("1. 출결 점수 등록");
//					System.out.println("2. 출결 점수 수정");
//					System.out.println("3. 출결 점수 삭제");
//					System.out.println("0. 뒤로가기");
//					System.out.print("번호: ");
//					String atBbutton = scan.nextLine();
//						if (atBbutton.equals("1")) {
////							addAtdScore(teacherNum, openSubjectNum, studentNum);
////							addAtdScore();
//						} else if (atBbutton.equals("2")) {
////							editAtdScore(teacherNum, openSubjectNum, studentNum);
//						} else if (atBbutton.equals("3")) {
////							deleteAtdScore(teacherNum, openSubjectNum, studentNum);
//						} else {
//							TeacherMain.teacherMain(teacherNum); //수정해야함. 그냥 넣어논 것.
//						}
//					
//				} else if (num.equals("2")) {
//					System.out.println();
//					System.out.println("[필기 점수 관리]");
//					System.out.println("1. 필기 점수 등록");
//					System.out.println("2. 필기 점수 수정");
//					System.out.println("3. 필기 점수 삭제");
//					System.out.println("0. 뒤로가기");
//					System.out.print("번호: ");
//					String writtingButton = scan.nextLine();
//						if (writtingButton.equals("1")) {
//							
//						} else if (writtingButton.equals("2")) {
//							
//						} else if (writtingButton.equals("3")) {
//							
//						} else {
//							TeacherMain.teacherMain(teacherNum); //수정해야함. 그냥 넣어논 것.
//						}
//				} else if (num.equals("3")) {
//					System.out.println();
//					System.out.println("[실기 점수 관리]");
//					System.out.println("1. 실기 점수 등록");
//					System.out.println("2. 실기 점수 수정");
//					System.out.println("3. 실기 점수 삭제");
//					System.out.println("0. 뒤로가기");
//					System.out.print("번호: ");
//					String practicalButton = scan.nextLine();
//						if (practicalButton.equals("1")) {
//							
//						} else if (practicalButton.equals("2")) {
//							
//						} else if (practicalButton.equals("3")) {
//							
//						} else {
//							TeacherMain.teacherMain(teacherNum); //수정해야함. 그냥 넣어논 것.
//						}
//				} else {
//					TeacherMain.teacherMain(teacherNum);
//				}
//			}
//			
//			rs.close();
//			cstat.close();
//			conn.close();
//			
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherScore.teacherOneStdScoreCheck()");
//			e.printStackTrace();
//		}
//		return null;
//		
//	}




//	private void addAtdScore(String teacherNum, String openSubjectNum, String studentNum) {
//		
//		System.out.println();
//		Connection conn = null;
//		CallableStatement cstat = null;
//		
//		try {
//			
//			conn = DBUtil.open();
//			
//			System.out.println("[출결 점수 등록]");
//			System.out.print("출결 점수: ");
//			String atdScore = scan.nextLine();
//			
//			String sql = "{ call procAddScoreAtd(?, ?, ?, ?) }";
//			
//			cstat = conn.prepareCall(sql);
//			
//			System.out.println();
//			System.out.print("==============================================================================\n");
//			System.out.println(                			"출결 성적 등록");
//			System.out.print("==============================================================================\n");
////			System.out.println("[교육생번호]\t[이름]\t[전화번호]\t[수료상태]\t[수료및중도탈락날짜]\t[출결성적]\t[필기성적]\t[실기성적]\t\n");
//			
////			int atd = Integer.parseInt(atdScore);
//			
//			cstat.setString(1, teacherNum);
//			cstat.setString(2, openSubjectNum);
//			cstat.setString(3, studentNum);
////			cstat.setInt(4, atd);
//			cstat.setString(4, atdScore); //오류
//			
//			cstat.executeQuery();
//
//			
//			String osNum = cstat.getString(2);
//			String stdName = cstat.getString(3);
//			String score = cstat.getString(4);
//			System.out.println("출결 점수 등록이 완료되었습니다.");
//			System.out.print("개설과목번호: " + osNum + " 이름: " + stdName + " 출결점수: " + score );
//			
//			
//		} catch (Exception e) {
//			System.out.println("TeacherScoreDAO.addAtdScore()");
//			e.printStackTrace();
//		}
//		
//		
//	}


	
	
	
	
	
	
	
}//class
