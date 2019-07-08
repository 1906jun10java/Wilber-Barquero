package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.ConnFactory;

public class EmployeeDAOImpl {
	public static ConnFactory cf = ConnFactory.getInstance();
	private static Connection conn = cf.getConnection();
	
	public List<Employee> getEmployeeList() throws SQLException {
		
        String sql = "SELECT * FROM EMPLOYEE";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<Employee> employee = new ArrayList<Employee>();
        if (!rs.isBeforeFirst()) {
            return null;
        }
        while (rs.next()) {
            Employee emp = new Employee();
            emp.setEmpID(rs.getInt("EMPLOYEE_ID"));
            emp.setEmpFirstName(rs.getString("EMP_FIRSTNAME"));
            emp.setEmpFirstName(rs.getString("EMP_LASTNAME"));
            emp.setEmpDepartmentID(rs.getInt("DEPARTMENT_ID"));
            emp.setEmpSalary(rs.getDouble("SALARY"));
            emp.setEmpEmail(rs.getString("EMP_EMAIL"));
            employee.add(emp);
            System.out.println("");
        }

        return employee;
    }


	
	public void createEmployee(String empFirstName, String empLastName, int empDepartmentID, double empSalary,
			String empEmail) throws SQLException {
	
		String sql = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, "");
		ps.setString(2, empFirstName);
		ps.setString(3, empLastName);
		ps.setInt(4, empDepartmentID);
		ps.setDouble(5, empSalary);
		ps.setString(6, empEmail);
		ps.executeUpdate();
		}
}
