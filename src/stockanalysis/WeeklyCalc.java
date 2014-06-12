package stockanalysis;

import org.joda.time.*;
import org.joda.time.format.*;

import java.sql.*;;
public class WeeklyCalc {
	
	public void getWeekly(String sdt, String edt, String ticker)throws Exception{
		try{
			
		
	System.out.println("in week calc function");
	String sc = "STOCK";
	Connection con = GetDBConnection.getConnection(sc);
    String query = "SELECT * FROM ACC_NS WHERE DATE BETWEEN '"+sdt+"'  AND '"+edt+"'";
	Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	ResultSet rs = stmt.executeQuery(query);
	//System.out.println(rs.next());
	int  rc = 0;
	if(rs!=null){
		rs.last();
		rc = rs.getRow();
		rs.first();
	}
	System.out.println("no of rows = "+(int)rc);
	int k = 1;
	while(k<=rc){
		String date = rs.getString(1);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = formatter.parseDateTime(date);
		int i = dt.getDayOfWeek();
		System.out.println("date = "+rs.getString(1));
		System.out.println("day of week = "+i);
		double open = rs.getDouble("open");
		double high = rs.getDouble("high");
		double low = rs.getDouble("low");
		//System.out.println("************");
		//System.out.println("ooopen = "+open);
		double close=0;
		int avgVol = 0;
		
		for(int j=i;j<=5; j++){
			//calculating highiest of week
			if(rs.getDouble("high") > high){
				high = rs.getDouble("high");
			}
			//calculating lowest of week
			if(rs.getDouble("low")<low){
				low = rs.getDouble("low");
			}
			//calculating avg volume of week
			avgVol = avgVol+rs.getInt("volume");
			close = rs.getDouble("close");
			int cr = rs.getRow();
			if(cr<rc)
			rs.next();
			k++;
			
			
		}
		System.out.println("open = "+open);
		System.out.println("high = "+high);
		System.out.println("low = "+low);
		System.out.println("close = "+close);
		//System.out.println(avgVol);
		System.out.println("\n\n");
		//rs.previous();
		
		
	}
	//System.out.println("ssssss");
		}
	catch(Exception e){
		e.printStackTrace();
	}
		
	
	}

	public static void main(String[] args) {
		WeeklyCalc wc = new WeeklyCalc();
		try{
			wc.getWeekly("2013-11-12", " 2014-01-06", "ACC_NS");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		

	}

}

