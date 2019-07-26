package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/employeeHome")
public class EmployeeHome extends HttpServlet{
	
	private static final long serialVersionUID = 4046720853279424973L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String JSONraw = session.getAttribute("reportsTo").toString();
			Integer reportsTo = Integer.parseInt(JSONraw);
			if (reportsTo == 0 ) {
				response.setStatus(200);
				response.sendRedirect("managerHome");
			} else {
				response.setStatus(200);
				request.getRequestDispatcher("Employee.html").forward(request, response);
			}
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
