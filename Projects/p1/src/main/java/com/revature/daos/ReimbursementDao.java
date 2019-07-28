package com.revature.daos;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;

public interface ReimbursementDao {

	Reimbursement createReimbursement(int id, double amount, String type) throws SQLException;
	List<Reimbursement> getPendingReimbursement(int id) throws SQLException;
	List<Reimbursement> getApprovedReimbursetment(int approvedId) throws SQLException;
	List<Reimbursement> getReimbursementById(int id) throws SQLException;
	List<Reimbursement> getReimbByDeaprtment(int id) throws SQLException;
	List<Reimbursement> getAllResolvedReimb() throws SQLException;
}
 