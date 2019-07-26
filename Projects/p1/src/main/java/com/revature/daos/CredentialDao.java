package com.revature.daos;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Credential;

public interface CredentialDao {
	Credential getPass(String email) throws SQLException;
	List <Credential> getAllUsers() throws SQLException;
}
