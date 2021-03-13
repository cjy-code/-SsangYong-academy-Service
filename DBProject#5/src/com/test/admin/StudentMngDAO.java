package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentMngDAO {
	
	private  Connection conn;
	private  PreparedStatement pstat;
	private  CallableStatement cstat; 
	private  Statement stat;			
	private  ResultSet rs;	
	private  Scanner sc = new Scanner(System.in);

public StudentMngDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 모든 교육생 리스트를 불러오는 메서드
	 * @param word - 특정 조건절 키워드
	 * @return  list;
	 */
	ArrayList<StudentMngDTO> list(String word) {
		
		try {
			
			String where ="";
			 
			 if(word != null)	{
				 where = String.format("where name like '%%%s%%'", word);
			 }
			String sql = String.format("select * from vwStudent %s order by seq asc", where); 
			rs = stat.executeQuery(sql);
			
			
			ArrayList<StudentMngDTO> list = new ArrayList<StudentMngDTO>();
						
			
			
			while(rs.next()) {
				 StudentMngDTO dto = new StudentMngDTO();
				
				 dto.setSeq(rs.getString("seq"));
				 dto.setName(rs.getString("name"));
				 dto.setTel(rs.getString("tel"));
				 dto.setSsn(rs.getString("ssn"));
				 dto.setRegdate(rs.getString("regdate"));
				 
				 list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentMngDAO.list()");
			e.printStackTrace();
		}
		return null;
	}


	
	
	

	/**
	 * 교육생 정보 등록
	 * @param dto 
	 * @return  cstat.executeUpdate();
	 */
	public int add(StudentMngDTO dto) {
			
		try {
			
			StudentMng a = new StudentMng();// for pause 사용
			String sql = " call procAddStudent(?, ?, ?)";
			
			cstat = conn.prepareCall(sql);
					
		    cstat.setString(1, dto.getName());
			cstat.setString(2, dto.getTel());
			cstat.setString(3, dto.getSsn());
			
			System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[최종 등록 하시겠습니까?]");
			System.out.print("[Y/N]: ");
			String YN = sc.nextLine();
			System.out.println();
			
			if(YN.toUpperCase().equals("Y")) {
				System.out.println("등록 성공");
				return cstat.executeUpdate();
			}else {
				System.out.println("등록 취소");
				a.pause(0);
			}
			return 0;
			
		} catch (Exception e) {
			System.out.println("StudentMngDAO.add()");
			e.printStackTrace();
		}
		return 0;
		
	}//add
	
	
	
	
	/**
	 * 특정 교육생 정보를 불러오는 메서드
	 * @param seq - 교육생 번호
	 * @return - dto
	 */
	public StudentMngDTO get(String seq) {
		
		try {
			String sql ="select * from vwStudent where seq =?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				StudentMngDTO dto = new StudentMngDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setSsn(rs.getString("ssn"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
				
			}       
			
		} catch (Exception e) {
			System.out.println("StudentMngDAO.get()");
			e.printStackTrace();
		}
	
		
		return null;
	}//get()
	
	
	
	
	
	
	/**
	 * 교육생 정보 수정 메서드
	 * @param dto2
	 * @return
	 */
	public int edit(StudentMngDTO dto2)	{
		try {
			
			String sql = "update vwStudent set name=?, tel=?, ssn=? where seq =?";
			
			pstat = conn.prepareStatement(sql);
			conn.setAutoCommit(true);

		

			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getTel());
			pstat.setString(3, dto2.getSsn());
			pstat.setString(4, dto2.getSeq());
			
			
			return pstat.executeUpdate();
	    
		} catch (Exception e) {
			System.out.println("StudentMngDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * 교육생 정보 삭제 메서드
	 * @param dto2 -- 특정 교육생 번호
	 * @return cstat.executeUpdate();
	 */
	public int delete(String dto2)	{
		
		try {
			StudentMng a = new StudentMng();
			String sql = "call procDeleteStudent(?)";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto2);
			
			
			
			System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[최종 삭제 하시겠습니까?]");
			System.out.print("[Y/N]: ");
			String YN = sc.nextLine();
			System.out.println();
			
			if(YN.toUpperCase().equals("Y")) {
				System.out.println("삭제 성공");
				return cstat.executeUpdate();
			}else {
				System.out.println("삭제 취소");
				a.pause(0);
			}
			
		} catch (Exception e) {
			System.out.println("StudentMngDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}


	
	
	
	/**
	 * 특정 교육생 정보 조회
	 * @param seq -교육생 번호
	 * @return
	 */
	public StudentMngDTO getCertain(String seq) {
		try {
			
			String sql ="select * from vwCertainST where studentSeq =?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				StudentMngDTO dto = new StudentMngDTO();
				
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setStudentTel(rs.getString("studentTel"));
				dto.setStudentRegister(rs.getString("studentRegister"));
				dto.setCoursename(rs.getString("coursename"));
				dto.setCourseterm(rs.getString("courseterm"));
				dto.setLectureroomname(rs.getString("lectureroomname"));
				dto.setCompletioncheck(rs.getString("completioncheck"));
				dto.setCompletiondate(rs.getString("completiondate"));
				dto.setOpencoursebegin(rs.getString("opencoursebegin"));
				dto.setOpencourseend(rs.getString("opencourseend"));
				dto.setCoursestate(rs.getString("coursestate"));
				dto.setSsn(rs.getString("ssn"));
				return dto;
				
			}       
			
			
			
		} catch (Exception e) {
			System.out.println("StudentMngDAO.getCertain()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
