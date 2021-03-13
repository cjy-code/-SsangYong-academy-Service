package com.test.teacher;

import java.util.ArrayList;




//import dto.UnderstandingDTO;



public class TeacherUnderstanding {
	
		//전체 이해도조회 출력


				
		public static void teacherList(ArrayList<UnderstandingDTO> result) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("이해도조회번호\t 수강번호\t\t주간진도표번호\t\t\t   이해도수치");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			for (UnderstandingDTO udto : result) {
				System.out.printf("%s\t%s\t\t    %s\t\t\t%s\n"
						, udto.getUnderstandingCheckSeq()
						, udto.getClassSeq()
						, udto.getWeeklyProgressSeq()
						, udto.getUnderstandingNum()
				);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}




}
