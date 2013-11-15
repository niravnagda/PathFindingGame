/*
 * @(#)RegistrationServlet.java   13/10/20
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package com.gameengine.registration;

//~--- non-JDK imports --------------------------------------------------------

import com.gameengine.dataaccesslayer.DataAccess;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet.
 *
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
        response.setContentType("text/html");    // Setting the response type.

        PrintWriter out      = response.getWriter();    // Getting writer object.
        UserData    userData = new UserData();          // Data object for user data

        // Retrieving the user data from registration form
        userData.setFirstName(request.getParameter("firstname").toString());
        userData.setLastName(request.getParameter("lastname").toString());
        userData.setEmailId(request.getParameter("email").toString());
        userData.setPassword(request.getParameter("password").toString());
        userData.setUserName(request.getParameter("username").toString());

        DataAccess dataAccess = new DataAccess();

        // Sending the user data to data access layer for registration.
        if (dataAccess.register(userData)) {

            // Redirects to Login page is registration successful
            response.sendRedirect("Login.html");

            // out.println("Registration Successful!!!");
        } else {
            out.println("Registration Failed!!!");
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
