package com.revature.beans;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimId;
	private double ammount;
	private Timestamp date;
	private String type;
	private String description;
	private int empId;
	
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(double ammount, Timestamp date, String type, String description) {
		super();
		this.ammount = ammount;
		this.date = date;
		this.type = type;
		this.description = description;
	}


	public Reimbursement(int reimId, double ammount, Timestamp date, String type, String description, int empId) {
		super();
		this.reimId = reimId;
		this.ammount = ammount;
		this.date = date;
		this.type = type;
		this.description = description;
		this.empId = empId;
	}


	public int getReimId() {
		return reimId;
	}
	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", ammount=" + ammount + ", date=" + date + ", type=" + type
				+ ", description=" + description + ", empId=" + empId + "]";
	}	
}
