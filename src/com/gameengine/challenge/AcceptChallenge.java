package com.gameengine.challenge;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gameengine.dataaccesslayer.DataAccess;

/**
 * Servlet implementation class AcceptChallenge
 */
@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptChallenge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String challenged = (String)request.getSession(true).getAttribute("username");
		request.getSession().setAttribute("type", "challenged");
		String challenger = (String)request.getParameter("challenger");
		DataAccess data = new DataAccess();
		if(data.setChallengeStatus(challenger, challenged)){
			synchronized (request) {
				try {
					//System.out.println(data.getTimeStamp(challenger, challenged));
					request.wait(data.getTimeStamp(challenger, challenged));
					response.sendRedirect("Game.jsp");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			response.sendRedirect("OnlineUsers.jsp");
		}
		
	}

}
