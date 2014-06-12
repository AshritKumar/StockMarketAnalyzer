package stockanalysis;

import java.sql.*;

public class CompanyName {
	
	public String getCompanyName(String ticker){
		Connection con = null;
		String compName = null;
		try{
			con = GetDBConnection.getConnection("STOCKT_NSE");
			String query = "SELECT * FROM TNC WHERE TICKER=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,ticker.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				compName = rs.getString(2);
				//System.out.println(compName);
			} 
		con.close();
		}
		catch(SQLException e){
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	return compName;
	}
	
}
