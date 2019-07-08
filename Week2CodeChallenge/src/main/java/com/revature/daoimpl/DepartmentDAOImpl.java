package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Department;
import com.revature.util.ConnFactory;

public class DepartmentDAOImpl {
		public static ConnFactory cf = ConnFactory.getInstance();
		private static Connection conn = cf.getConnection();
		
		public List<Department> getDepartmentList() throws SQLException {
	        String sql = "SELECT * FROM DEPARTMENT";
	        PreparedStatement stmt = conn.prepareStatement(sql);

	        ResultSet rs = stmt.executeQuery();
	        List<Department> departments = new ArrayList<Department>();
	        if (!rs.isBeforeFirst()) {
	            return null;
	        }
	        while (rs.next()) {
	            Department dep = new Department();
	            dep.setDepID(rs.getInt("DEPARTMENT_ID"));
	            dep.setDepName(rs.getString("DEPARTMENT_NAME"));
	            departments.add(dep);
	        }

	        return departments;
	    }


		
		public void createDepartment(String depName) throws SQLException {
			String sql = "INSERT INTO DEPARTMENT VALUES(?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(2, depName);
			ps.executeUpdate();
			}
}
