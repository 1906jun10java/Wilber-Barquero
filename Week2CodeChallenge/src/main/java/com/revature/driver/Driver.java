package com.revature.driver;

import java.sql.SQLException;

import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.DepartmentDAOImpl;

public class Driver {
	public static void main(String[] args) {
		DepartmentDAOImpl ddi = new DepartmentDAOImpl();		
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		
		try {
			System.out.println(ddi.getDepartmentList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(edi.getEmployeeList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			edi.createEmployee("Alien","Ware",3,60000.00,"aw@gmail.com");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
