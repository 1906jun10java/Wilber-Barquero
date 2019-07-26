package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;

@WebServlet("/session")
public class Session extends HttpServlet{

	private static final long serialVersionUID = -4209722164886337248L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			Integer employeeID = (Integer) session.getAttribute("eId");
			String firstName = session.getAttribute("firstName").toString();
			String lastName = session.getAttribute("lastName").toString();
			String email = session.getAttribute("email").toString();
			String department = session.getAttribute("department").toString();
			Integer reportsTo = (Integer) session.getAttribute("reportsTo");
			Employee currentSession = new Employee(employeeID, firstName, lastName, email, department, reportsTo);
			response.getWriter().write((new ObjectMapper()).writeValueAsString(currentSession));
		} 
		else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
