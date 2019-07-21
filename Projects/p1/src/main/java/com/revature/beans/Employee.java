package com.revature.beans;

public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int reportsTo;
	
	public Employee() {
		super();
	}

	
	public Employee(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}


	public Employee(int id, String firstName, String lastName, String email, String password, int reportsTo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.reportsTo = reportsTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", reportsTo=" + reportsTo + "]";
	}
	
	
}
