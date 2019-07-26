package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Credential;
import com.revature.daosimpl.CredentialDaoImpl;

public class LoginService {
CredentialDaoImpl cdi = new CredentialDaoImpl();
	
	
	public boolean loginTest(String username, String password) {
		List<Credential> loginVerify = new ArrayList<>();

		try {
			loginVerify.addAll(cdi.getAllUsers());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	
	
		for(Credential c :loginVerify) {
			if(c.getEmail() == null) {
				return false;
			}
			if(c.getEmail().equals(username)){ 
				System.out.println(username);
				if(c.getPass().equals(password)) {
					System.out.println(password);
					return true;
				}
			}
		}
		return false;
	}
}
