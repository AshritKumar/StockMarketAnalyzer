package stockanalysis;
import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;


public class CurrentDayGainerLooser {

	public void insertIntoGL(){
		Connection conn = null;
		//Statement stmt = null;
		BufferedReader br = null;
		PreparedStatement pstmt = null;
		try{
		DecimalFormat df = new DecimalFormat("#.###");	
		PercentChange pchng = new PercentChange();
		String ticker;
		FileReader fr = new FileReader("/home/nani/work/nsetest.txt");
		br = new BufferedReader(fr);
		double chng[] = new double[2];
		
		//ResultSet rs = null;
		
		while((ticker=br.readLine())!=null){
			String tick = ticker.concat(".NS");
			//System.out.println(tick);
			chng = pchng.getCurrentDayChng(tick);
			double amtChng = Double.valueOf(df.format(chng[0]));
			double percntChng = Double.valueOf(df.format(chng[1]));
			//System.out.println("change :"+amtChng);
			//System.out.println("%change :"+percntChng);
			//System.out.println();
			 conn = GetDBConnection.getConnection("STOCKT_NSE");
			ResultSet rs1  = conn.getMetaData().getTables(null, null , "gainlose" , null);
			if(!rs1.next()){
				System.out.println("creating table gainlose..");
				String ctquery = "create table gainlose(ticker text, chng double, percent_chng double)";
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate(ctquery);
			}
			System.out.println("checking whether ticker values exists or not "+tick);
			String check = "select * from gainlose where ticker='"+tick+"'";
			//System.out.println("check query :"+check);
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(check);
			 if(rs.next()){
				 System.out.println("Updating current values of"+tick);
				 String uquery = "UPDATE  gainlose  SET chng= "+amtChng+" , percent_chng="+percntChng+" where ticker='"+tick+"'";
			     //System.out.println("update query :"+uquery);
				 Statement stmt1 = conn.createStatement();
			     stmt.executeUpdate(uquery);
			     System.out.println("updated values of "+tick);
			 }
			 else{
				 System.out.println("Inserting values into  "+tick); 
				 //String inquery = "INSERT INTO gainlose values('"+tick+"',"+amtChng+","+percntChng+")";
					String inquery = "INSERT INTO gainlose values(?,?,?)";
					//System.out.println("insert query "+inquery);
					//System.out.println(Double.valueOf(df.format((chng[0]))));
					pstmt = conn.prepareStatement(inquery);
					pstmt.setString(1, tick);
					pstmt.setDouble(2, amtChng);
					pstmt.setDouble(3, percntChng);
					pstmt.executeUpdate(); 
					//Statement stmt2 = conn.createStatement();
					//stmt2.executeUpdate(inquery);
					System.out.println("inserted into "+tick);
			 }
			
			
		}
		}
		catch(IOException ie){
			ie.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
		finally{
			try {

				//stmt.close();
				conn.close();
				br.close();
				if(pstmt!=null)
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		
		}
	//	public static void main(String[] args) throws IOException, SQLException {
		//	insertIntoGL();
			//}
		
	}
		


