package stockanalysis;
import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;


public class CurrentDayGainerLooser {

	
		
		public static void insertIntoGL() throws IOException{
		DecimalFormat df = new DecimalFormat("#.###");	
		PercentChange pchng = new PercentChange();
		String ticker;
		FileReader fr = new FileReader("/home/nani/work/nsetest.txt");
		BufferedReader br = new BufferedReader(fr);
		Connection conn = GetDBConnection.getConnection("STOCKT_NSE");
		Statement stmt=null;
		PreparedStatement pst = null;
		try {
			stmt = conn.createStatement();
			 
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		while((ticker = br.readLine())!=null){
			String tick = ticker.concat(".NS");
		try {
			double chng[]  =  pchng.getCurrentDayChng(tick);
			//System.out.println("companies increased");
			if(chng[0]>0){
				System.out.println("companies increased");
				ResultSet rs = conn.getMetaData().getTables(null, null , "gainers" , null);
				if(stmt!=null){
				if(!rs.next()){
					//if table dosent exist create it
					System.out.println("creating table gainers..");
					String ctquery = "create table gainers(ticker varchar(10), increase double, percent_inc double)";
					stmt.executeUpdate(ctquery);
					}
				System.out.println("checking whether ticker values exists or not "+tick);
			    String check = "select * from gainers where ticker='"+tick+"'";
			    System.out.println("*************************");
			   ResultSet rs1 =  stmt.executeQuery(check);
			    if(rs1.next()){
			   System.out.println("Updating the query");
			   System.out.println("Change Inc:"+Double.valueOf(df.format((chng[0]))));
			   System.out.println("Company :"+tick);
			   ;
               /*pst = conn.prepareStatement(uquery);  
               pst.setDouble(1, Double.valueOf(df.format((chng[0]))));
               pst.setDouble(2, Double.valueOf(df.format((chng[1]))));
               pst.setString(3, tick);*/
               stmt.executeUpdate(uquery);
			    }
			    else{ 
			    	System.out.println("Inserting values"); 
				String inquery = "INSERT INTO gainers values(?,?,?)";
				System.out.println(Double.valueOf(df.format((chng[0]))));
				PreparedStatement pstmt = conn.prepareStatement(inquery);
				pstmt.setString(1, tick);
				pstmt.setDouble(2, Double.valueOf(df.format((chng[0]))));
				pstmt.setDouble(3, Double.valueOf(df.format((chng[1]))));
				System.out.println(Double.valueOf(df.format((chng[1]))));
				pstmt.executeUpdate(); 
				}
			 }
			}
			else{
				System.out.println("companies decreased");
				ResultSet rs = conn.getMetaData().getTables(null, null , "losers" , null);
				if(stmt!=null){
					if(!rs.next()){
						//if table dosent exists create it 
						String ctquery = "create table losers(ticker varchar(10), decrease double, percent_dec double)";
						stmt.executeUpdate(ctquery);
						} 
					
					String check = "select * from losers where ticker='"+tick+"'";
					System.out.println("Checked decreased");
					   ResultSet rs1 =  stmt.executeQuery(check);
					   if(rs1.next()){
					    	System.out.println("Updating decreased");
						   String uquery = "UPDATE  losers  SET decrease= "+Double.valueOf(df.format((chng[0]*-1)))+" , percent_dec="+Double.valueOf(df.format((chng[1]*-1)))+" where ticker="+tick;
		                    System.out.println("Updated decreased");
					    	stmt.executeUpdate(uquery);
					    }
					   else{
					String inquery = "INSERT INTO losers values(?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(inquery);
					pstmt.setString(1, tick);
					pstmt.setDouble(2, Double.valueOf(df.format((chng[0]*-1))));
					pstmt.setDouble(3, Double.valueOf(df.format((chng[1]*-1))));
					pstmt.executeUpdate();
					   }
			}
		} 
		}
			catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch(IOException ie){
			ie.printStackTrace();
			
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
			
		}
		}
		try {
			br.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally{
		    try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		}
		
		public static void main(String a[]) throws IOException{
			insertIntoGL();
		}
		
		
	}
		


 
