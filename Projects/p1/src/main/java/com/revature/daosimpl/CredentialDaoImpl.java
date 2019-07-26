package com.revature.daosimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Credential;
import com.revature.beans.Employee;
import com.revature.daos.CredentialDao;
import com.revature.util.ConnectionService;

public class CredentialDaoImpl implements CredentialDao{
	private static ConnectionService cs = ConnectionService.getInstance();
	private static Connection connection = cs.getConnection();
	
	@Override
	public Credential getPass(String email) throws SQLException {
		
		return null;
	}

	@Override
		public List<Credential> getAllUsers() throws SQLException{
			String sql = "SELECT * FROM CREDENTIALS";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			List<Credential> cList = new ArrayList<>();
			while (rs.next()) {
				Credential credential = new Credential();
				credential.setEmail(rs.getString("EMP_EMAIL"));
				credential.setPass(rs.getString("EMP_PASS"));
				cList.add(credential);
			}
			return cList;
		}
}
