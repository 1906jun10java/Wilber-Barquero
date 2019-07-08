package com.revature.beans;

public class Department {
	private int depID;
	private String depName;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int depID, String depName) {
		super();
		this.depID = depID;
		this.depName = depName;
	}

	public int getDepID() {
		return depID;
	}

	public void setDepID(int depID) {
		this.depID = depID;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Override
	public String toString() {
		return "Department [depID=" + depID + ", depName=" + depName + "]";
	}
	
}
