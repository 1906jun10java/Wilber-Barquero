package com.revature.daosimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.daos.ReimbursementDao;
import com.revature.util.ConnectionService;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static ConnectionService cs = ConnectionService.getInstance();
	private static Connection connection = cs.getConnection();
	
	 @Override
	 public Reimbursement createReimbursement() throws SQLException {
	  
	  String sql = "{CALL REQUEST_REIMBURSMENT(?,?,?,?)";
	  CallableStatement ps = connection.prepareCall(sql);
	  
	  Reimbursement createReim = new Reimbursement();
	  
	  ps.setInt(1, createReim.getEmpId());
	  ps.setDouble(2, createReim.getAmmount());
	  ps.setDate(3, (Date) createReim.getDate());
	  ps.setString(4, createReim.getType());
	  ps.executeUpdate();
	  
	  return createReim;
	 }
	 
	 
	 @Override
	 public List<Reimbursement> getPendingReimbursement(int id) throws SQLException {
	  
	  String sql = "SELECT * FROM REIMBURSEMENT WHERE REIM_STATUS = 'Pending' AND EMP_ID = ?";
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setInt(1, id);
	  ResultSet rs = stmt.executeQuery();
		if (rs.isBeforeFirst()) {
			return null;
		}
		
		List<Reimbursement> reimList = new ArrayList<>();
		while (rs.next()) {
			Reimbursement reimObj = new Reimbursement();
			reimObj.setReimId(rs.getInt("REIM_ID"));
			reimObj.setEmpId(rs.getInt("EMP_ID"));
			reimObj.setAmmount(rs.getDouble("REIM_AMMOUNT"));
			reimObj.setDate(rs.getDate("REIM_DATE"));
			reimObj.setType(rs.getString("REIM_TYPE"));
			reimObj.setStatus(rs.getString("REIM_STATUS"));
			reimList.add(reimObj);
		}
		return reimList;
	 }
	 
	 @Override
	 public List<Reimbursement> getApprovedReimbursetment(int approvedId) throws SQLException {
	  
	  String sql = "SELECT * FROM REIMBURSEMENT WHERE REIM_STATUS != 'Pending' AND EMP_APPROVEDBY =? ";
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setInt(1, approvedId);
	  ResultSet rs = stmt.executeQuery(sql);
	  if (rs.isBeforeFirst()) {
			return null;
		}
	  
	  List<Reimbursement> reimList = new ArrayList<>();
	  while (rs.next()) {
			Reimbursement reimObj = new Reimbursement();
			reimObj.setReimId(rs.getInt("REIM_ID"));
			reimObj.setEmpId(rs.getInt("EMP_ID"));
			reimObj.setAmmount(rs.getDouble("REIM_AMMOUNT"));
			reimObj.setDate(rs.getDate("REIM_DATE"));
			reimObj.setType(rs.getString("REIM_TYPE"));
			reimObj.setStatus(rs.getString("REIM_STATUS"));
			reimObj.setApprovedBy(rs.getString("REIM_APPROVEDBY"));
			reimList.add(reimObj);
		}
		return reimList;
	  
	 }
	 
	 @Override
	 public List<Reimbursement> getReimbursementById(int id) throws SQLException {
	  
	  String sql = "SELECT * FROM REIMBURSEMENT WHERE EMP_ID = ?";
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setInt(1, id);
	  ResultSet rs = stmt.executeQuery(sql);
	  if (rs.isBeforeFirst()) {
			return null;
		}
	  
	  List<Reimbursement> reimList = new ArrayList<>();
	  while (rs.next()) {
		  	Reimbursement reimObj = new Reimbursement();
			reimObj.setReimId(rs.getInt("REIM_ID"));
			reimObj.setEmpId(rs.getInt("EMP_ID"));
			reimObj.setAmmount(rs.getDouble("REIM_AMMOUNT"));
			reimObj.setDate(rs.getDate("REIM_DATE"));
			reimObj.setType(rs.getString("REIM_TYPE"));
			reimObj.setStatus(rs.getString("REIM_STATUS"));
			reimList.add(reimObj);
	  	}
	  	return reimList;
	 }
	 
	 
	 @Override
	 public List<Reimbursement> getReimbByDeaprtment(int m_id) throws SQLException {
	  
	  String sql = "SELECT * FROM REIMBURSEMENT WHERE REIM_STATUS = 'Pending' AND EMP_ID IN (SELECT EMP_ID FROM EMPLOYEE WHERE EMP_REPORTSTO = ?";
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setInt(1, m_id);
	  ResultSet rs = stmt.executeQuery();
	  
	  if (rs.isBeforeFirst()) {
			return null;
		}
	  
	  List<Reimbursement> reimList = new ArrayList<>();
	  while (rs.next()) {
		  Reimbursement reimObj = new Reimbursement();
			reimObj.setReimId(rs.getInt("REIM_ID"));
			reimObj.setEmpId(rs.getInt("EMP_ID"));
			reimObj.setAmmount(rs.getDouble("REIM_AMMOUNT"));
			reimObj.setDate(rs.getDate("REIM_DATE"));
			reimObj.setType(rs.getString("REIM_TYPE"));
			reimObj.setStatus(rs.getString("REIM_STATUS"));
			reimList.add(reimObj);
	  }
	  return reimList;
	  
	 }
	 @Override
	 public List<Reimbursement> getAllResolvedReimb() throws SQLException {
	  
	  String sql = "SELECT * FROM REIMBURSEMENT WHERE REIM_STATUS != 'Pending' ";
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  ResultSet rs = stmt.executeQuery(sql);
	  if (rs.isBeforeFirst()) {
			return null;
		}
	  
	  List<Reimbursement> reimList = new ArrayList<Reimbursement>();
	  while (rs.next()) {
		  Reimbursement reimObj = new Reimbursement();
			reimObj.setReimId(rs.getInt("REIM_ID"));
			reimObj.setEmpId(rs.getInt("EMP_ID"));
			reimObj.setAmmount(rs.getDouble("REIM_AMMOUNT"));
			reimObj.setDate(rs.getDate("REIM_DATE"));
			reimObj.setType(rs.getString("REIM_TYPE"));
			reimObj.setStatus(rs.getString("REIM_STATUS"));
			reimObj.setApprovedBy(rs.getString("REIM_APPROVEDBY"));
			reimList.add(reimObj);
	  }
	   return reimList;
	 }


}
