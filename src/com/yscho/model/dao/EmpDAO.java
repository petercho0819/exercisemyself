package com.yscho.model.dao;

import static com.yscho.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.yscho.model.dto.EmpDTO;

public class EmpDAO {
	
	
	private Properties prop = new Properties();
	public EmpDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/emp-query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int lastNum(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		int lastNum = 0;
		
		String query = prop.getProperty("lastNum");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				lastNum = rset.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return lastNum;
	}

	public List<Map<Integer, String>> selectAllEmp(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		List<Map<Integer, String>> empNum = null;
		
		String query = prop.getProperty("empNum");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			empNum = new ArrayList<>();
			
			while(rset.next()) {
				Map<Integer, String> empNumCate = new HashMap();
				empNumCate.put(rset.getInt("EMP_ID"), rset.getString("EMP_NAME"));
				empNum.add(empNumCate);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return empNum;
	}

	public int insertMem(Connection con, EmpDTO empdto) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertMem");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empdto.getEmpId());
			pstmt.setString(2, empdto.getEmpName());
			pstmt.setString(3, empdto.getEmpNo());
			pstmt.setString(4, empdto.getJobCode());
			pstmt.setString(5, empdto.getSalLevel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


}
