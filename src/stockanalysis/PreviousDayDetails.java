package stockanalysis;

import java.sql.*;

public class PreviousDayDetails {

	
	public Date date;
	
	public double getPreMaxValue(String ticker) throws SQLException { 
		Connection con = GetDBConnection.getConnection("STOCKT_NSE");
		String query = "SELECT HIGH FROM "+ticker.replace('.','_').toUpperCase()+" ORDER BY DATE DESC LIMIT 1";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				
			return rs.getDouble(1);
			
			 }
			
			else{
			return -1;
			}
			} catch (SQLException e) {
		 e.printStackTrace();
		return -1;
		}
		finally{
			con.close();
		}
}
	public double getPreMinValue(String ticker) throws SQLException{
		Connection con = GetDBConnection.getConnection("STOCKT_NSE");
		String query = "SELECT LOW FROM "+ticker.replace('.','_').toUpperCase()+" ORDER BY DATE DESC LIMIT 1";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
			return rs.getDouble(1);
			 }
			else{
			return -1;
			}
		} catch (SQLException e) {
		 e.printStackTrace();
		return -1;
		}
		finally{
			con.close();
		}
}
	public double getVolume(String ticker) throws SQLException{
		Connection con = GetDBConnection.getConnection("STOCKT_NSE");
		String query = "SELECT VOLUME,DATE FROM "+ticker.replace('.','_').toUpperCase()+" ORDER BY DATE DESC LIMIT 1";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){ 
			date=rs.getDate("DATE");
			return rs.getDouble("VOLUME");
			 }
			else{
			return -1;
			}
		} catch (SQLException e) {
		 e.printStackTrace();
		return -1;
		}finally{
			con.close();
		}
}

	public double getPreDayClose(String ticker) throws SQLException{
		Connection con = GetDBConnection.getConnection("STOCKT_NSE");
		String query = "SELECT CLOSE FROM "+ticker.replace('.','_').toUpperCase()+" ORDER BY DATE DESC LIMIT 1";
		try {
			//PreparedStatement pstmt = con.prepareStatement(query);
			//pstmt.setString(1,ticker.replace('.','_'));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
			return rs.getDouble(1);
			 }
			else{
			return -1;
			}
		} catch (SQLException e) {
		 e.printStackTrace();
		return -1;
		
		}finally{
			con.close();
		}
		}
		public double getPreDayOpen(String ticker) throws SQLException{
			Connection con = GetDBConnection.getConnection("STOCKT_NSE");
			String query = "SELECT OPEN FROM "+ticker.replace('.','_').toUpperCase()+" ORDER BY DATE DESC LIMIT 1";
			try {
				//PreparedStatement pstmt = con.prepareStatement(query);
				//pstmt.setString(1,ticker.replace('.','_'));
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
				return rs.getDouble(1);
				 }
				else{
				return -1;
				}
			} catch (SQLException e) {
			 e.printStackTrace();
			return -1;
			
			}finally{
				con.close();
			}
}
	
}
