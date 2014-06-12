package stockanalysis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetDBConnection {
	  static Connection con = null;
	public  static Connection getConnection(String schema){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema,"nani","nani");
			//System.out.println("got connection ");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return con;
		
		
	}
	

}
