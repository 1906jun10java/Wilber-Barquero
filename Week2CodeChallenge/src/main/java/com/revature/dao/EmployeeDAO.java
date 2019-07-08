package com.revature.dao;

import com.revature.beans.Employee;
import java.sql.SQLException;
import java.util.List;



public interface EmployeeDAO {
	
	public abstract void createEmployee(String empFirstName, String empLastName, double empSalary,
			String empEmail) throws SQLException;
	public abstract List<Employee> getEmployeeList() throws SQLException;
}
