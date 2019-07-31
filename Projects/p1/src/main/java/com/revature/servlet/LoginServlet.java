package com.revature.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.daosimpl.EmployeeDaoImpl;
import com.revature.services.LoginService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1998374341524544897L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
	} //redirect to employeeHome servlet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		LoginService login = new LoginService();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username"); //name on form
		String password = request.getParameter("password"); //name on form
		boolean test = login.loginTest(username,password );
		
		if(test == true) {
			session = createSession(username, session);
			response.setStatus(200);
			response.sendRedirect("employeeHome");
		}
		else {
			response.sendRedirect("login");
		}
		
		
	}
	public HttpSession createSession(String username,HttpSession session) {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee e = null;
		try {
			e = edi.getEmployeeByEmail(username);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		session.setAttribute("eId", e.getId());
		session.setAttribute("firstName", e.getFirstName());
		session.setAttribute("lastName", e.getLastName());
		session.setAttribute("email", e.getEmail());
		session.setAttribute("department", e.getDepartment());
		session.setAttribute("reportsTo", e.getReportsTo());
		
		return session;
	}
}
