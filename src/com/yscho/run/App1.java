package com.yscho.run;

import static com.yscho.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.yscho.model.dao.EmpDAO;
import com.yscho.model.dto.EmpDTO;

public class App1 {

	public static void main(String[] args) {

		Connection con = getConnection();
		
		EmpDAO empDAO = new EmpDAO();
		
		int lastNum = empDAO.lastNum(con);
		System.out.println("lastNum : " + lastNum);
		
		List<Map<Integer, String>> empNumCategory = empDAO.selectAllEmp(con);
		for(Map<Integer, String> empNumCate : empNumCategory) {
			System.out.println(empNumCate);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.print("사원번호 : ");
		String empNum = sc.nextLine();
		System.out.print("사원 이름 : ");
		String empName = sc.nextLine();
		System.out.print("주민번호 : ");
		String ssn = sc.nextLine();
		System.out.print("jobcode : ");
		String jobCode = sc.nextLine();
		System.out.print("sallevel : ");
		String salLevel = sc.nextLine();
		
		EmpDTO empdto = new EmpDTO(empNum, empName, ssn, jobCode, salLevel);
		
		int result = empDAO.insertMem(con, empdto);
		
		if(result > 0) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		
		
		
	}

}
