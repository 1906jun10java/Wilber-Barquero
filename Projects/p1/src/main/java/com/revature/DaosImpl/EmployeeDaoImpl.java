package com.revature.DaosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.daos.EmployeeDao;
import com.revature.util.ConnectionService;

public class EmployeeDaoImpl implements EmployeeDao{
	private static ConnectionService cs = ConnectionService.getInstance();
	private static Connection connection = cs.getConnection();
	
	@Override
	public List<Employee> getAllEmployees() throws SQLException{
		String sql = "SELECT * FROM EMPLOYEE";
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		Employee empObj = new Employee();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		
		List<Employee> el = new ArrayList<>();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getInt("EMP_ID"));
			emp.setFirstName(rs.getString("EMP_FIRSTNAME"));
			emp.setLastName(rs.getString("EMP_LASTNAME"));
			emp.setEmail(rs.getString("EMP_EMAIL"));
			emp.setPassword(rs.getString("EMP_DEPARTMENT"));
			emp.setReportsTo(rs.getInt("EMP_REPORTSTO"));
			el.add(emp);
		}
		return el;
	}
	
	@Override
	public Employee getEmployeeById(int id) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		Employee empObj = new Employee();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			empObj.setId(rs.getInt("EMP_ID"));
			empObj.setFirstName(rs.getString("EMP_FIRSTNAME"));
			empObj.setLastName(rs.getString("EMP_LASTNAME"));
			empObj.setEmail(rs.getString("EMP_EMAIL"));
			empObj.setPassword(rs.getString("EMP_DEPARTMENT"));
			empObj.setReportsTo(rs.getInt("EMP_REPORTSTO"));
		}
		return empObj;
	}


	@Override
	public Employee getEmployeeByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		Employee empObj = new Employee();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			empObj.setId(rs.getInt("EMP_ID"));
			empObj.setFirstName(rs.getString("EMP_FIRSTNAME"));
			empObj.setLastName(rs.getString("EMP_LASTNAME"));
			empObj.setEmail(rs.getString("EMP_EMAIL"));
			empObj.setPassword(rs.getString("EMP_DEPARTMENT"));
			empObj.setReportsTo(rs.getInt("EMP_REPORTSTO"));
		}
		return empObj;
	}

/*	private Employee parseResultSet(ResultSet rs) throws SQLException {
		Employee empObj = new Employee();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			empObj.setId(rs.getInt("EMP_ID"));
			empObj.setFirstName(rs.getString("FIRSTNAME"));
			empObj.setLastName(rs.getString("LASTNAME"));
			empObj.setEmail(rs.getString("EMAIL"));
			empObj.setPassword(rs.getString("EMP_PASS"));
			empObj.setReportsTo(rs.getInt("MAN_ID"));
		}
		return empObj;
	}*/
}
