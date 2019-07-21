package com.revature.beans;

import java.util.Date;

public class Reimbursement {
	
	private int reimId;
	private int empId;
	private double ammount;
	private Date date;
	private String type;
	private String status;
	private String approvedBy;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimId, int empId, double ammount, Date date, String type, String status,
			String approvedBy) {
		super();
		this.reimId = reimId;
		this.empId = empId;
		this.ammount = ammount;
		this.date = date;
		this.type = type;
		this.status = status;
		this.approvedBy = approvedBy;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + empId;
		result = prime * result + reimId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (empId != other.empId)
			return false;
		if (reimId != other.reimId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", empId=" + empId + ", ammount=" + ammount + ", date=" + date
				+ ", type=" + type + ", status=" + status + ", approvedBy=" + approvedBy + "]";
	}
}
