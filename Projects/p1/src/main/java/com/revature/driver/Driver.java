package com.revature.driver;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Credential;
import com.revature.beans.Employee;
import com.revature.daosimpl.CredentialDaoImpl;
import com.revature.daosimpl.EmployeeDaoImpl;

public class Driver {
	public static void main(String[] args) {
		
				EmployeeDaoImpl edi = new EmployeeDaoImpl();
				CredentialDaoImpl cdi = new CredentialDaoImpl();
				
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
				
				
				try {
					List<Credential> cList = cdi.getAllUsers();
					for(int i=0;i<cList.size();i++){
					    System.out.println(cList.get(i));
					} 
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
}
