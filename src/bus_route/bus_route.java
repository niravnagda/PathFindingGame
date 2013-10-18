package bus_route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class bus_route {

	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String ser = "jdbc:mysql://localhost:3306/bus_db";
	private String user = "root";
	private String pass = "root";
	
	public String get_route(String source, String destination) throws SQLException{
		return calculate_path(source, destination);
	}
	
	public String calculate_path(String source, String destination) throws SQLException{
		StringBuilder result = new StringBuilder();
		connectDb();
		st = conn.createStatement();
	    rs = st.executeQuery("select * from bus_route WHERE source='" + source + "' AND destination='" + destination + "'");	    
	    int columns = rs.getMetaData().getColumnCount();
	    
	    while (rs.next()) {
	        for (int i = 2; i <= columns; i++) {
	            result.append(rs.getString(i) + " ");
	        }
	        result.append("\n");
	    }
	    closeConnection();
		return result.toString();
	}
	private void connectDb(){
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(ser,user,pass);
		    } catch (SQLException ex) {
		    	// handle any errors
		    	Logger lgr = Logger.getLogger(bus_route.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		    	ex.printStackTrace();
		    } catch(Exception e){
		    	e.printStackTrace();
		    }
	}
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
	
	public static void main(String[] args) throws SQLException{
		System.out.println(new bus_route().get_route("mccallum","bush_turn_pike"));
	}
}