/*
 * @(#)DataAccess.java   13/10/11
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package com.gameengine.dataaccesslayer;

//~--- non-JDK imports --------------------------------------------------------

import com.gameengine.login.LoginPojo;
import com.gameengine.registration.UserData;

//~--- JDK imports ------------------------------------------------------------



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Description: Data Access Layer to access the Database through JDBC connection with MySQL.
 * Component: Model
 * @author JaSon
 */
public class DataAccess {
	Connection con;    // Connection Object for Establishing Connection to Database.

	public DataAccess() {

		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");    // Driver Path and Loading of Driver.

			// Establishing the Connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamedb", "root", "root");
		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Description: Registering the user in the database.
	 * @param userData : POJO data received from user request.
	 * @return true if successfully registered in the Database else false.
	 */
	public boolean register(UserData userData) {
		PreparedStatement pStat1 = null;
		PreparedStatement pStat2 = null;
		PreparedStatement pStat3 = null;
		Statement         stat   = null;

		try {

			// inserting data(FirstName, LastName, email) in user table.
			pStat1 = con.prepareStatement("insert into user(firstname,lastname,email) values(?, ?, ?);");
			pStat1.setString(1, userData.getFirstName());
			pStat1.setString(2, userData.getLastName());
			pStat1.setString(3, userData.getEmailId());

			int val = pStat1.executeUpdate();

			if (val > 0) {
				stat = con.createStatement();

				// Retrieving the auto-generated id by the RDBMS
				ResultSet rs = stat.executeQuery("select id from user where email = '" + userData.getEmailId() + "';");

				if (rs.next()) {
					userData.setId(rs.getInt(1));
				}

				// Setting the password & username in user_credentials tables based on the corresponding id generated
				pStat2 = con.prepareStatement(
						"insert into user_credentials(id,username,password) values (?,?, PASSWORD(?));");
				pStat2.setInt(1, userData.getId());
				pStat2.setString(2, userData.getUserName());
				pStat2.setString(3, userData.getPassword());

				int val1 = pStat2.executeUpdate();

				// setting the default value for number of coins and level for corresponding id
				if (val1 > 0) {
					pStat3 = con.prepareStatement("insert into user_gameaccount(id,coins,level) values(?,10,1)");
					pStat3.setInt(1, userData.getId());

					int val2 = pStat3.executeUpdate();

					if (val2 > 0) {
						return true;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return false;
	}

	/**
	 * Description: Used for validating the user credentials.
	 * @param loginPojo
	 * @return true if user is successfully validated else false
	 */
	public boolean loginVerification(LoginPojo loginPojo) {
		//Statement stat = null;
		PreparedStatement stat = null;

		try {
			/*stat = con.createStatement();

            // Quering the database for validating the password and username.
            ResultSet rs = stat.executeQuery("select id from user_credentials where username='"
                                             + loginPojo.getUsername() + "' AND password=PASSWORD('"
                                             + loginPojo.getPassword() + "');");*/

			stat = con.prepareStatement("insert into online_users (id) select id from user_credentials where username='"
					+ loginPojo.getUsername() + "' AND password=PASSWORD('"
					+ loginPojo.getPassword() + "');");
			int result = stat.executeUpdate();
			System.out.println(result);
			if (result == 1) {
				//loginPojo.setId(rs.getInt(1));
				return true;
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//closeConnection();
		}

		return false;
	}

	/**
	 * Description: Closing the database connection.
	 */
	public void closeConnection() {
		try {
			con.close();    // closing the connection.
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getOnlineUsers(String username){
		ArrayList<String> onlineUsers = new ArrayList<>();
		Statement stat;

		String sql = "select username from user_credentials where id IN (select id from online_users) and username <> '"+username+"';";

		try {
			stat= con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				onlineUsers.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return onlineUsers;
	}

	public boolean addChallenge(String challenger, String challenged){
		PreparedStatement pStat = null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("insert into gamePlay(challenger, challenged, challengeTime) values(?,?,?);");
			pStat.setString(1, challenger);
			pStat.setString(2, challenged);
			pStat.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			result = (pStat.executeUpdate()==1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getChallengers(String username){
		ArrayList<String> challengers = new ArrayList<>();
		Statement stat;

		String sql = "select challenger from gamePlay where challenged = '"+username+"';";

		try {
			stat= con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				challengers.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challengers;
	}

	public boolean getChallengeStatus(String challenger,String challenged){
		boolean result=false;
		Statement stat;

		String sql = "select accepted from gamePlay where challenger = '"+challenger+"' and challenged='"+challenged+"';";

		try {
			stat= con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				if(rs.getBoolean(1))
					result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean setChallengeStatus(String challenger, String challenged){
		PreparedStatement pStat =null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("update gamePlay set accepted = true where challenger='"+challenger+"' and challenged='"+challenged+"';");
			result = (pStat.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public long getTimeStamp(String challenger,String challenged){
		long result=0;
		Statement stat;

		String sql = "select challengeTime from gamePlay where challenger = '"+challenger+"' and challenged='"+challenged+"';";

		try {
			stat= con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				Timestamp time=rs.getTimestamp(1);
				result=20000-(System.currentTimeMillis()-time.getTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public boolean setPathTimeChallenged(int time, String challenged){
		PreparedStatement pStat =null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("update gamePlay set PathTimeChallenged = "+time+" where accepted = true AND challenged='"+challenged+"';");
			result = (pStat.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean setPathTimeChallenger(int time, String challenger){
		PreparedStatement pStat =null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("update gamePlay set PathTimeChallenger = "+time+" where accepted = true AND challenger='"+challenger+"';");
			result = (pStat.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public boolean logout(String username){

		PreparedStatement pStat1 = null;
		PreparedStatement pStat2 = null;

		boolean result1=false;
		boolean result2=false;

		try {
			pStat1 = con.prepareStatement("Delete from gamePlay where challenger = '"+username+"' or challenged = '"+username+"';");
			result1 = (pStat1.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pStat2 = con.prepareStatement("Delete from online_users where id IN (select id from user_credentials where username = '"+username+"');");
			result2 = (pStat2.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result1&&result2;
	}


	public String getWinner(String player){
		int pathTimeChallenged=9999,pathTimeChallenger=9999;
		Statement stat = null;
		ResultSet rs1=null;
		String getPathTimes = "select PathTimeChallenger, PathTimeChallenged, challenger, challenged from gameplay where challenger='"+player+"' OR challenged='"+player+"' AND accepted=true;";
		String winner = null ;
		try {
			stat = con.createStatement();
			rs1 = stat.executeQuery(getPathTimes);
			if(rs1.next()){
				pathTimeChallenger = rs1.getInt(1);
				pathTimeChallenged = rs1.getInt(2);
			}
			if(pathTimeChallenged > pathTimeChallenger){
				winner = rs1.getString(3);
			}else if(pathTimeChallenged < pathTimeChallenger){
				winner = rs1.getString(4);
			}else {
				winner = "tie";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				removeChallenge(rs1.getString(3), rs1.getString(4));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return winner;
	}

	public int getCoins(String username){

		int coins = 0;

		Statement stat = null;
		String getCoins = "select coins from user_gameaccount where id IN (select id from user_credentials where username = '"+username+"');";

		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(getCoins);
			if(rs.next()){
				coins = rs.getInt(1);
				System.out.println("Coins in getCoins: "+coins);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coins;
	}

	public boolean setCoins(String username,int coins){


		PreparedStatement pStat =null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("update user_gameaccount set coins = "+coins+" where id IN (select id from user_credentials where username = '"+username+"');");
			result = (pStat.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean removeChallenge(String challenger, String challenged)
	{
		PreparedStatement pStat = null;
		boolean result=false;
		try {
			pStat = con.prepareStatement("delete from gamePlay where challenger =? AND challenged=?;");
			pStat.setString(1, challenger);
			pStat.setString(2, challenged);
			result = (pStat.executeUpdate()==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}


//~ Formatted by Jindent --- http://www.jindent.com
