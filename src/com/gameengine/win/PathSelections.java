package com.gameengine.win;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gameengine.dataaccesslayer.DataAccess;

import MiddleWare.DataPojo;

/**
 * Servlet implementation class PathSelections
 */
@WebServlet(name = "PathSelection", urlPatterns = { "/PathSelection" })
public class PathSelections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PathSelections() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		DataAccess dataAccessObj = new DataAccess();
		String type = (String)request.getSession().getAttribute("type");
		HashMap<String, DataPojo> data = (HashMap<String, DataPojo>) request.getSession().getAttribute("data");
		String trans = request.getParameter("Submit");
		String username = (String)request.getSession().getAttribute("username");
		System.out.println(type + username + trans);
		
		int coins = (int)request.getSession().getAttribute("coins");
		coins = coins - data.get(trans).getCost();
		//request.getSession().setAttribute("coins", coins);
		if(type.equalsIgnoreCase("challenged")){
			dataAccessObj.setPathTimeChallenged(data.get(trans).getTime(),username);
		}else{
			dataAccessObj.setPathTimeChallenger(data.get(trans).getTime(),username);
		}
		
		synchronized (request) {
			try {
				request.wait(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String winner=dataAccessObj.getWinner(username);
		String message = "";
		int walk = 0;
		
		/*response.getWriter().println("<HTML>");
		response.getWriter().println("<HEAD>");
		response.getWriter().println("</HEAD>");
		response.getWriter().println("<BODY>");*/
		if(winner.equals("tie"))
			message = "tie";//response.getWriter().println("There is a tie, no one won the game");
		else 
			message = winner;//response.getWriter().println("The winner is: "+winner);
		/*response.getWriter().println("<form action=\"logout\" method = \"post\">");
		response.getWriter().println("<input type=\"Submit\" value = \"logout\" />");*/
		int newCoins = 0;
		//response.getWriter().println("Username = "+username+"<br />");
		if(winner.equalsIgnoreCase(username)){
			newCoins = coins +  (data.get(trans).getCost()* 2);
			//System.out.println("coins =" +coins);
		}
		else if(winner.equalsIgnoreCase("tie"))
			newCoins = coins + data.get(trans).getCost();
		else
			newCoins = coins;
		request.getSession().setAttribute("coins", newCoins);
		
		if(trans.equalsIgnoreCase("walk")) {
			System.out.println("In walk");
			//response.getWriter().println("Thank you\n...You have chosen the greenest path...");
			//response.getWriter().println("We have a reward for you, " + data.get(trans).getCost() + 1 + " extra coin(s) from our side");
			newCoins += (data.get(trans).getCost() + 1);
			walk = data.get(trans).getCost() + 1;
			request.getSession().setAttribute("coins", newCoins);
		}
		/*response.getWriter().println("Now you have " + newCoins + " coins");
		request.getSession().setAttribute("coins", newCoins);
		response.getWriter().println("</form>");
		response.getWriter().println("</BODY>");
		response.getWriter().println("</HTML>");
		*/
		String url="Winner.jsp?message=" + message + "&walk=" + walk;
		System.out.println(url);
		//String encodedURL=response.encodeRedirectURL(url);
		//System.out.println(encodedURL);
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}