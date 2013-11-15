package BUS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.ORB;
import Comm.*;

public class bus_route extends CommPackagePOA{

	private Connection conn = null; //connection string
	private Statement st = null; //Query statement 
	private ResultSet rs = null; //Query result set
	private String ser = "jdbc:mysql://localhost:3306/bus_db"; //database location
	private String user = "root"; //username for database
	private String pass = "root"; // password for database
	private ORB orb;
	
	public void setORB(ORB orb_val){
		orb = orb_val;
	}
	
	@Override
	public String get_route(String source, String destination){
		try {
			return calculate_path(source, destination);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "No_path";
	}
	
	public void shutdown() {
		orb.shutdown(false);
	}
	public String calculate_path(String source, String destination) throws SQLException{
		StringBuilder result = new StringBuilder();
		connectDb(); // Connect to the database
		st = conn.createStatement();
	    rs = st.executeQuery("select source, destination, time, cost from bus_route WHERE source='" + source + "' AND destination='" + destination + "'");	    
	    int columns = rs.getMetaData().getColumnCount();
	    
	    while (rs.next()) {
	        for (int i = 1; i <= columns; i++) {
	            result.append(rs.getString(i) + " ");
	        }
	        result.append("\n");
	    }
	    System.out.println(result.toString());
	    closeConnection();
		return result.toString();
	}
	private void connectDb(){
		try {
			  Class.forName("com.mysql.jdbc.Driver"); //connect using jdbc
		      conn = DriverManager.getConnection(ser,user,pass); //connect to database
		    } catch (SQLException ex) {
		    	// handle any errors
		    	Logger lgr = Logger.getLogger(bus_route.class.getName()); //logger to log messages
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		    	ex.printStackTrace();
		    } catch(Exception e){
		    	e.printStackTrace();
		    }
	}
	//terminate connection after database has been used
	private void closeConnection() throws SQLException
	{
		try{
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
