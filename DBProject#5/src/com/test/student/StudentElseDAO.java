package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.test.teacher.DBUtil;

import oracle.jdbc.OracleTypes;


public class StudentElseDAO {
	
	
	private static Connection conn;
	private static Statement stat;
	private static CallableStatement cstat;
	private static PreparedStatement pstat;
	private static ResultSet rs;

	
	/**
	 * 기본 생성자
	 */
	public StudentElseDAO() {
		
		try {
			
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		

		
	}

	/**
	 * 로그인에 성공했을 때 교육생의 개인정보와 수강정보를 출력하는 메서드
	 * @param i 학생번호(PK)
	 */
	public static void loginok(int i) {
		
		
		try {
			
			String sql = "{ call procStudentInfo(?,?,?,?,?,?,?,?,?) }";
			
			
			cstat = conn.prepareCall(sql);
			cstat.setInt(1, i);
			
			cstat.registerOutParameter(2, OracleTypes.VARCHAR);
			cstat.registerOutParameter(3, OracleTypes.VARCHAR);
			cstat.registerOutParameter(4, OracleTypes.DATE);
			cstat.registerOutParameter(5, OracleTypes.NUMBER);
			cstat.registerOutParameter(6, OracleTypes.VARCHAR);
			cstat.registerOutParameter(7, OracleTypes.DATE);
			cstat.registerOutParameter(8, OracleTypes.DATE);
			cstat.registerOutParameter(9, OracleTypes.NUMBER);
			
			//java.sql.SQLException: ORA-06550: line 1, column 7:
			//PLS-00306: wrong number or types of arguments in call to 'PROCSTUDENTINFO'
			cstat.executeQuery();
			
			
			String name = cstat.getString(2);
			String tel = cstat.getString(3);
			String rdate = (cstat.getString(4)).substring(0,10);
			int ssn = cstat.getInt(5);
			String courseName = cstat.getString(6);
			String sdate = (cstat.getString(7)).substring(0,10);
			String edate = (cstat.getString(8)).substring(0,10);
			int room = cstat.getInt(9);
			
			
			
			
			
				System.out.printf("이름: %s\n핸드폰번호: %s\n등록일: %s\n주민등록번호뒷자리: %d\n수강과정: %s\n과정시작일: %s\n과정종료일: %s\n강의실번호: %d\n", name,tel,rdate,ssn,courseName,sdate,edate,room);
				System.out.println("---------------------------------");
				System.out.println();
				
			
			
			
			
		} catch (SQLException e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 교육생의 성적을 조회하는 메서드
	 * @param i 학생번호(PK)
	 */
	public static void checkingGrade(int i) {
		
		try {
			
			String sql = String.format("select distinct  \"과목번호\", \"과목명\" from vwStudentGrade where \"학생번호\" = %d order by \"과목번호\"", i);
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				System.out.println(rs.getString("과목번호") +" : " + rs.getString("과목명"));
			}
			
			System.out.println("---------------------------------");
			System.out.println("성적을 조회할 과목의 번호를 입력해주세요");
			Scanner scan = new Scanner(System.in);
			int k = scan.nextInt();
			
			String sql2 = "{ call procStudentGrade(?,?,?) }";
			
			cstat = conn.prepareCall(sql2);
			cstat.setInt(1, i);
			cstat.setInt(2, k);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			
			rs = (ResultSet)cstat.getObject(3);
			
			if (rs.next()) {
			System.out.println("---------------------------------");
			System.out.println("(" + rs.getString("과목번호") + ")" + rs.getString("과목명"));
			System.out.println("수업시작일 : " + (rs.getString("과목시작일")).substring(0,10));
			System.out.println("수업종료일 : " + (rs.getString("과목종료일")).substring(0,10));
			System.out.println("주교재 : " + rs.getString("교재명"));
			System.out.println("담당교사 : " + rs.getString("교사명"));
			System.out.println("[성적] (출결 : 필기 : 실기 순)");
			System.out.println("[배점] " + rs.getString("출결배점") + " : " + rs.getString("필기배점") + " : " + rs.getString("실기배점") );
			System.out.println("[점수] " + rs.getString("출결점수") + " : " + rs.getString("필기점수") + " : " + rs.getString("실기점수") );
			System.out.println("시험날짜 : " + (rs.getString("시험날짜").substring(0,10)));
			System.out.println("시험문제 : " + rs.getString("시험문제"));
			System.out.println("다른 과목 조회는 1번, 교육생 메인으로 돌아가기는 2번");
			System.out.println("---------------------------------");
			
			int comeback = scan.nextInt();
			
			if (comeback == 1) {
				checkingGrade(i);
			} else if (comeback == 2) {
				
			}
			
			}
			
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
	}

	/**
	 * 교육생이 입실/퇴실을 기록하거나 교육생의 출결상황을 출력해주는 메서드 
	 * @param i 학생번호(PK)
	 */
	public static void attendance(int i) {
		
		System.out.println("---------------------------------");
		System.out.println("원하시는 메뉴의 숫자를 입력해주세요.");
		System.out.println("1.출결 기록하기");
		System.out.println("2.출결 조회하기");
		
		Scanner scan = new Scanner(System.in);
		int j = scan.nextInt();
		
		if (j == 1) {
			recording(i);
		} else if (j == 2) {
			System.out.println("---------------------------------");
			System.out.println("원하시는 옵션의 숫자를 입력해주세요");
			System.out.println("1. 전체 조회"); 
			System.out.println("2. 월별 조회");
			System.out.println("3. 일 조회");
			int k = scan.nextInt();
			if (k == 1) {
				wholeRecord(i);
			} else if (k == 2) {
				monthRecord(i);
			} else if (k == 3) {
				dateRecord(i);
			}
		}
		
	}

	/**
	 * 교육생의 전체 출결 상황을 출력해주는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void wholeRecord(int i) {
		
		System.out.println("---------------------------------");
		
		try {
			
			String sql = String.format("select vca.\"출결상태\" as \"출결상태\", count(*) as \"총\" from vwCheckAttendance vca where vca.\"학생번호\" = %d group by vca.\"출결상태\" order by vca.\"출결상태\"", i );
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				System.out.println(rs.getString("출결상태") + " : " + rs.getInt("총"));
				
			}
			
			System.out.println("---------------------------------");
			System.out.println("엔터를 누르면 출결 메뉴로 이동합니다");
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			attendance(i);
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
	}

	/**
	 * 교육생의 특정 월의 출결상황을 출력해주는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void monthRecord(int i) {
		
		System.out.println("조회를 원하는 달을 입력하세요(ex)05, 11");
		
			Scanner scan = new Scanner(System.in);
			String month = scan.nextLine();
			
		try {
			
			System.out.println("---------------------------------");
			String sql = String.format("select vca.\"출결상태\" as \"출결상태\", count(*)as \"총\" from vwCheckAttendance vca \r\n"
					+ "where vca.\"학생번호\" = %d and to_char(vca.\"입실시간\", 'mm') = '%s' group by vca.\"출결상태\" order by vca.\"출결상태\"",i, month);
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				System.out.println(rs.getString("출결상태") + " : " + rs.getInt("총"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 교육생의 특정 날짜의 출결상황을 출력해주는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void dateRecord(int i) {
		
		System.out.println("조회를 원하는 날짜를 입력하세요(ex)09-11");
		
		Scanner scan = new Scanner(System.in);
		String date = scan.nextLine();
		
	try {
		
		String sql = String.format("select vca.\"출결상태\" as \"출결상태\" from vwCheckAttendance vca\r\n"
				+ "where vca.\"학생번호\" = %d and to_char(vca.\"입실시간\", 'mm-dd') = '%s'",i, date);
		
		stat = conn.prepareStatement(sql);
		
		rs = stat.executeQuery(sql);
		
		while(rs.next()) {
			
			System.out.println(rs.getString("출결상태"));
		}
		
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 입실/퇴실을 기록해주는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void recording(int i) {
		
		System.out.println("---------------------------------");
		System.out.println("해당하는 숫자 입력");
		System.out.println("1. 입실");
		System.out.println("2. 퇴실");
		
		Scanner scan = new Scanner(System.in);
		
		int record = scan.nextInt();
		
		if (record == 1) {
			comein(i);
		} else if (record == 2) {
			exit(i);
			attendanceUpdate(i);
		}
		
		
		
	}

	/**
	 * 퇴실 기록이 찍히면 그날의 입실/퇴실 기록을 토대로 출결상황을 업데이트 해주는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void attendanceUpdate(int i) {
		
		try {
			
			String sql = String.format("select distinct\"수강번호\" from vwStudentGrade where \"학생번호\" = %d", i);
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			
			while(rs.next()) {
			
				int classNum = rs.getInt("수강번호");
					
				String sql2 = "{ call procAttendanceState(?) }";
				
				cstat = conn.prepareCall(sql2);
				
				cstat.setInt(1, classNum);
				
				int result = cstat.executeUpdate();
				
				if (result > 0) {
					System.out.println("---------------------------------");
					System.out.println("퇴실이 완료되었습니다.");
					System.out.println("메인으로 가시려면 1번을, 프로그램을 종료하시려면 2번을 입력해주세요.");
					
					Scanner scan = new Scanner(System.in);
					int exitOk = scan.nextInt();
					
					if (exitOk == 1) {
						loginok(i);
					} else if (exitOk == 2) {
						System.out.println("---------------------------------");
						System.out.println("프로그램을 이용해 주셔서 감사합니다. 안녕히가세요.");
						break;
					}
					
					
				}
			
	
			}
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * 퇴실을 기록하는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void exit(int i) {
		
		try {
			
			String sql = String.format("select distinct\"수강번호\" from vwStudentGrade where \"학생번호\" = %d", i);
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			
			while(rs.next()) {
			int classNum = rs.getInt("수강번호");
			
			
			String sql2 = "{ call procAddAttendanceExit(?) }";
			
			cstat = conn.prepareCall(sql2);
			
			cstat.setInt(1, classNum);
			
			cstat.executeUpdate();
			
			
	
			}
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 입실을 기록하는 메서드
	 * @param i 학생번호(PK)
	 */
	private static void comein(int i) {
		
		try {
			
			String sql = String.format("select distinct\"수강번호\" from vwStudentGrade where \"학생번호\" = %d", i);
			
			stat = conn.prepareStatement(sql);
			
			rs = stat.executeQuery(sql);
			
			
			while(rs.next()) {
			int classNum = rs.getInt("수강번호");
			
			
			String sql2 = "{ call procAddAttendanceEnter(?) }";
			
			cstat = conn.prepareCall(sql2);
			
			cstat.setInt(1, classNum);
			
			int result = cstat.executeUpdate();
			
			if(result > 0) {
				System.out.println("---------------------------------");
				System.out.println("정상적으로 입실완료 되었습니다.");
				System.out.println("엔터를 누르면 메인으로 돌아갑니다.");
				
				Scanner scan = new Scanner(System.in);
				
				scan.nextLine();
				scan.nextLine();
				loginok(i);
				
			
			}
			
	
			}
			
		} catch (Exception e) {
			System.out.println("StudentDAO");
			e.printStackTrace();
		}
		
	}

	/**
	 * 교육생의 로그인 첫 화면. 아이디와 비밀번호를 입력받아 교육생을 식별한다.
	 */
//	public void studentLogin() {
//		
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.println("아이디를 입력해주세요.");
//		String id = scan.nextLine();
//		
//		System.out.println("비밀번호를 입력해주세요.");
//		String password = scan.nextLine();
//		
//		try {
//			
//			String sql = String.format("select distinct studentname, studentssn, studentseq from vwstudentinfo where studentname = '%s' and studentssn = %s and rownum = 1", id, password);
//			
//			stat = conn.prepareStatement(sql);
//			
//			rs = stat.executeQuery(sql);
//			
//			if (rs.next()) {
//			
//				int i = Integer.parseInt(rs.getString("studentseq"));
//			
//				System.out.println("---------------------------------");
//				System.out.println("로그인 성공!");
//				
//				loginok(i);
//				
//				
//				//dao.loginok(i);	
//				//i는 학생pk(studentSeq)
//				
//				System.out.println("원하는 메뉴 선택");
//				System.out.println("1. 성적 조회");
//				System.out.println("2. 출결관리 및 출결조회");
//				System.out.println("3. 세부진도 조회");
//				System.out.println("4. 수업이해도 조회");
//				System.out.println("5. 1:1질의응답");
//				System.out.println("6. 프로젝트 관리");
//				
//				int j = scan.nextInt();
//				
//				if (j == 1) {
//					checkingGrade(i);
//				} else if (j == 2) {
//					attendance(i);
//				} else if (j == 3) {
//					String studentSeq = Integer.toString(i);
//					StudentWeeklyProgressMain.StudentWeeklyProgressMain(studentSeq);
//				} else if (j == 4) {
//					String seq = Integer.toString(i);
//					Understanding.start(seq);
//				} else if (j == 5) {
//					Student_QuestionView.openSubjectManagement();
//				} else if (j == 6) {
//					String seq = Integer.toString(i);
//					Project.start(seq);
//				}
//			
//			}
//			
//		} catch (Exception e) {
//			System.out.println("StudentDAO");
//			e.printStackTrace();
//		}
//		
//		
//		
//	}

	

}
