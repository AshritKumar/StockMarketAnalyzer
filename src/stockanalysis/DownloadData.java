package stockanalysis;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.net.*;
import java.sql.*;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class DownloadData {
	
private FileToDB objFDB = new FileToDB();

//private String ticker;


	public String createUrl(String ticker,String range) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		//System.out.println("month"+month);
		int day = c.get(Calendar.DATE);
		return "http://ichart.finance.yahoo.com/table.csv?s=" + ticker
				+"&a=10&b=01&c=2013&d="+ month+"&e="+day+"&f="+ year
				+"&g="+range+"&ignore=.csv";
	}
	
	public void download(String fileurl, String ticker) throws Exception {
		URL url = new URL(fileurl);
		URLConnection ucon = url.openConnection();
		InputStreamReader in = new InputStreamReader(ucon.getInputStream());
		BufferedReader buff = new BufferedReader(in);
		buff.readLine();
		String stringLine;
		while ((stringLine = buff.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(stringLine, ",");
			//Date date  = new SimpleDateFormat("yyyy-mm-dd").parse(str.nextToken().trim());
			String date =  str.nextToken().trim();
			double open = Double.parseDouble(str.nextToken().trim());
			double high = Double.parseDouble(str.nextToken().trim());
			double low = Double.parseDouble(str.nextToken().trim());
			double close = Double.parseDouble(str.nextToken().trim());
			int volume = Integer.parseInt(str.nextToken().trim());
			String adjclose = str.nextToken().trim();
			System.out.println("calling exportToDB()");
			int i =objFDB.exportToDB(date,open,high,low,close,volume,ticker);
			if(i==0){
				return;
		
			}
			
			/*long  i = check(ticker);
			for(int j=0;j<i;j++){
				   String det = buff.readLine();
				   StringTokenizer str1 = new StringTokenizer(det, ",");
					
				    String date1 =  str1.nextToken().trim();
					double open1 = Double.parseDouble(str1.nextToken().trim());
					double high1 = Double.parseDouble(str1.nextToken().trim());
					double low1 = Double.parseDouble(str1.nextToken().trim());
					double close1 = Double.parseDouble(str1.nextToken().trim());
					int volume1 = Integer.parseInt(str1.nextToken().trim());
				
				objFDB.exportToDB(date1,open1,close1,high1,low1,volume1,ticker);
			}
				return;*/
		}
		
		return;
}
	/*public long check (String ticker)throws SQLException{
		String date = "2013-11-12";
		Connection con = GetDBConnection.getConnection();
		Statement stmt = con.createStatement();
		String q = "SELECT DATE FROM "+ticker+" ORDER BY DATE  DESC LIMIT 1";
		ResultSet rs = stmt.executeQuery(q);
		rs.next();
		String dbDate = rs.getString(1);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime lstDt = formatter.parseDateTime(date);
	    DateTime dbd = formatter.parseDateTime(dbDate);
	    Duration duration = new Duration(lstDt, dbd);
	    long diff = duration.getStandardDays();
	    System.out.println(diff);
	    if(diff == 0){
	    	return 0;
	    }
	    else{
	    	return diff;
	    }
	    
	}*/
	
	/*public static void main(String ar[]) {
		//System.out.println("hello worldjjjjjjjj");
		 try{
		System.out.println("in try");
		 DownloadData dd = new DownloadData();
		 String url = dd.createUrl("INFY.NS");
		 dd.download(url, "INFY_NS");
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}*/

	
}
	
	


