package stockanalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import stockanalysis.GetDBConnection;;


public class FileToDB {
	 
	 
	
	  
	

	public  int  exportToDB(String sdate, double open, double high, double low,
			double close, int volume, String ticker) {
		//java.sql.Date date = new java.sql.Date(sdate.getTime());
		double amtChng = close - open;
		double percentChng =  (amtChng/open)*100;
		String schema  = ticker.substring(0, 10);
		System.out.println(schema);
		Connection con = null;
		try {
			con = GetDBConnection.getConnection(schema);
			//System.out.println(con);
			ResultSet rs = con.getMetaData().getTables(null, null , ticker , null);
			Statement stmt;
			
			String query;
			if(!rs.next()){
				//System.out.println("creating table"+ticker);
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				//System.out.println(rs.getString(3));
				//System.out.println(rs.getString(4));
				
				 query = "CREATE TABLE  "+ticker+"(date DATE PRIMARY KEY, open DOUBLE , high DOUBLE ,low DOUBLE, close DOUBLE, volume int(10), amtChng DOUBLE , percentChng DOUBLE )";
				 System.out.println("creating table "+ticker);
				 stmt = con.createStatement();
				 stmt.executeUpdate(query);
				 
				 
				 
				 query = "INSERT INTO "+ticker+" values(?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, sdate);
					pstmt.setDouble(2, open);
					pstmt.setDouble(3, high);
					pstmt.setDouble(4, low);
					pstmt.setDouble(5, close);
					pstmt.setInt(6, volume);
					pstmt.setDouble(7, amtChng);
					pstmt.setDouble(8, percentChng);
					pstmt.executeUpdate();
				 
				 
				 }
			
				
			else{
				System.out.println("insearting into table "+ticker);
				
				query = "INSERT INTO "+ticker+" values(?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, sdate);
				pstmt.setDouble(2, open);
				pstmt.setDouble(3, high);
				pstmt.setDouble(4, low);
				pstmt.setDouble(5, close);
				pstmt.setInt(6, volume);
				pstmt.setDouble(7, amtChng);
				pstmt.setDouble(8, percentChng);
				pstmt.executeUpdate();
				
				}
			
				con.close();
				
			
			
			
			
			
			
		}
   catch(SQLIntegrityConstraintViolationException ee){
	   try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return 0;
	   //ee.printStackTrace();
	   
			
		}
		
		catch (SQLException e) {
		
			
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return 0;
		}
		return 1;
		
		
		/*finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}*/
		
		
		
	}

}

