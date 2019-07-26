package com.revature.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.daosimpl.ReimbursementDaoImpl;
import com.revature.services.ReimbursementService;

@WebServlet("/submitReim")
public class SubmitReim extends HttpServlet {
	
	private static final long serialVersionUID = 8260704343848336633L;
	
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		
		if(session != null) {
			response.sendRedirect("employeeHome");
		}
		else {
			response.sendRedirect("login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IVE HIT THE SUBMITREQUESTSERVLET");
		session = request.getSession(false);
		ReimbursementDaoImpl rdi = new ReimbursementDaoImpl();
		
		if(session != null) {
			String EmployeeID = session.getAttribute("eId").toString();
			String reimAmount = request.getParameter("reimAmount");
			String reimType = request.getParameter("type");
			
			int employeeID = Integer.parseInt(EmployeeID);
			double amount = Double.parseDouble(reimAmount);
			

			boolean test = false;
			try {
				test = rdi.createReimbursement(employeeID, amount, reimType);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(test == true) {
				response.setStatus(200);
				response.sendRedirect("viewReim");
			}
			else {
				response.sendError(403);
				response.sendRedirect("employeeHome");
			}
		}
		else {
			response.sendRedirect("login");
		}
	}

}