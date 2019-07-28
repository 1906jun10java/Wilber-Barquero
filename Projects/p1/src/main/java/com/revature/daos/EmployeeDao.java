package com.revature.daos;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {
	List<Employee> getAllEmployees() throws SQLException;
	Employee getEmployeeById(int id) throws SQLException;
	Employee getEmployeeByEmail(String email) throws SQLException;	
	void saveEmployee(Employee e) throws SQLException;
	void removeEmployee(String email) throws SQLException;
}
