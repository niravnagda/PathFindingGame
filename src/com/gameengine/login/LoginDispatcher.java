/*
 * @(#)LoginDispatcher.java   13/10/20
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package com.gameengine.login;

//~--- non-JDK imports --------------------------------------------------------

import com.gameengine.dataaccesslayer.DataAccess;

//~--- JDK imports ------------------------------------------------------------


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginDispatcher() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.setContentType("text/html");    // Setting the Response Type.

		PrintWriter out = response.getWriter();    // Getting the response Writer.

		// Create session object
		// For session management.
		HttpSession session = request.getSession(true);

		// Storing user Credentials for validation.
		LoginPojo loginPojo = new LoginPojo();

		loginPojo.setUsername(request.getParameter("username").toString());
		loginPojo.setPassword(request.getParameter("password").toString());

		DataAccess dataAccess = new DataAccess();

		// Pass data to the data access layer and validate the user credentials.
		if (dataAccess.loginVerification(loginPojo)) {

			// Successful Validation, then redirect him to the game.
			out.println("Login Successful!!");

			// create cookie for session management
			Cookie cookie = new Cookie("username", loginPojo.getUsername());

			cookie.setMaxAge(300);
			response.addCookie(cookie);

			// set session attributes
			// Setting username for session.
			session.setAttribute("username", loginPojo.getUsername());


			// Setting id for session.
			// session.setAttribute("id", loginPojo.getId());

			// session.setAttribute("password", loginPojo.password);
			session.setAttribute("onlineFlag", true);

			// set max inactive period to 300 seconds.
			session.setMaxInactiveInterval(300);
			
			int coins = dataAccess.getCoins(loginPojo.getUsername());
			session.setAttribute("coins", coins);
			response.sendRedirect("OnlineUsers.jsp?flag=false");
		} else {
			if(request.getSession().isNew())
				out.println("Login Failed!!");
			else
				response.sendRedirect("OnlineUsers.jsp?flag=false");		}
	}
}


//~ Formatted by Jindent --- http://www.jindent.com
