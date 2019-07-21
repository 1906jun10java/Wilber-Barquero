package com.revature.servlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//resp.getWriter().write("FuuuuuuuuuuuuuuuuuuuuuuuCk");
		resp.sendRedirect("login");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// check whether a session already exists, and create one if not
		// overloaded version takes a boolean parameter, if false returns null when no session exists for the incoming request
		HttpSession session = req.getSession();
		// grab credentials from request
		Credentials creds = new Credentials(req.getParameter("username"), req.getParameter("password"));
		User u = authService.authenticateUser(creds);
		if (u != null) {
			// set user information as session attributes (not request attributes)
			session.setAttribute("userId", u.getId());
			session.setAttribute("username", u.getUsername());
			session.setAttribute("firstname", u.getFirstname());
			session.setAttribute("lastname", u.getLastname());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("problem", null);
			// resp.getWriter().write("welcome, "+u.getFirstname()+" "+u.getLastname());
			// redirect user to their profile page if authenticated
			resp.sendRedirect("profile");
		} else {
			
			// what if the creds are wrong?
			
			session.setAttribute("problem", "invalid credentials");
			
			// Option 1: print a sassy message (not super useful)
			// resp.getWriter().write("invalid credentials, nerd");
			
			// Option 2: redirect back to login
			resp.sendRedirect("login");
			
			// Option 3: send back a status code of 403 and a message
			// TO BE CONTINUED... WHAT IF THERE'S AN ERROR PAGE DEFINED?
			//resp.sendError(403, "invalid credentials");
		}
	}
}
