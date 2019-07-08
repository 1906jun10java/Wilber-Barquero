package com.revature.beans;

public class Employee {
	private int empID;
	private String empFirstName;
	private String empLastName;
	private int empDepartmentID;
	private double empSalary;
	private String empEmail;
	
	public Employee() {
		super();
	}

	public Employee(int empID, String empFirstName, String empLastName, int empDepartmentID, double empSalary,
			String empEmail) {
		super();
		this.empID = empID;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empDepartmentID = empDepartmentID;
		this.empSalary = empSalary;
		this.empEmail = empEmail;
	}

	
	
	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public int getEmpDepartmentID() {
		return empDepartmentID;
	}

	public void setEmpDepartmentID(int empDepartmentID) {
		this.empDepartmentID = empDepartmentID;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empDepartmentID=" + empDepartmentID + ", empSalary=" + empSalary + ", empEmail=" + empEmail + "]";
	}
}


