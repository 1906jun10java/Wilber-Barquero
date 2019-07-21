package com.revature.driver;

import java.sql.SQLException;
import java.util.List;

import com.revature.DaosImpl.EmployeeDaoImpl;
import com.revature.beans.Employee;

public class Driver {
	public static void main(String[] args) {
		
				EmployeeDaoImpl edi = new EmployeeDaoImpl();
				
				try {
					List<Employee> el = edi.getAllEmployees();
					for(int i=0;i<el.size();i++){
					    System.out.println(el.get(i));
					} 
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					System.out.println(edi.getEmployeeById(1));
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
}
