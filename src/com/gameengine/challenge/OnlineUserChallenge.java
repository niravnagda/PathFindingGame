package com.gameengine.challenge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.gameengine.dataaccesslayer.DataAccess;

/**
 * Servlet implementation class OnlineUserChallenge
 */
@WebServlet("/OnlineUserChallenge")
public class OnlineUserChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OnlineUserChallenge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String username = (String)request.getSession().getAttribute("username");
		request.getSession().setAttribute("type", "challenger");
		PrintWriter out = response.getWriter();		
		String challengedUser = (String) request.getParameter("users");
		DataAccess data = new DataAccess();
		if(data.addChallenge(username, challengedUser)){
			synchronized (request) {
				try {
					request.wait(15000);	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(data.getChallengeStatus(username, challengedUser)){
				response.sendRedirect("Game.jsp");	
			}else{
				if(!data.removeChallenge(username, challengedUser)){
					System.out.println("Challenge not removed from db");
				}
				//response.sendRedirect("OnlineUsers.jsp?flag=true");
				response.sendRedirect("OnlineUsers.jsp?flag=true");		
			}
		}else{
			out.println("Challenge not sent");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}