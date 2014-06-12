package stockanalysis;

import java.io.IOException;
import java.sql.SQLException;

public class PercentChange {
	
	public  double[] getCurrentDayChng(String ticker) throws SQLException, IOException{
		CurrentDetails cd = new CurrentDetails();
		PreviousDayDetails pdd = new PreviousDayDetails();
		cd.getCurrentDetails(ticker);
		//System.out.println(ticker);
		double curClose = cd.close;
		double prevClose = pdd.getPreDayClose(ticker);
		//System.out.println(curClose);
		//System.out.println(prevClose);
		//System.out.println();
		double chng[] = new double[2];
		chng[0] = (curClose-prevClose);
		chng[1] = ((curClose-prevClose)*100)/prevClose;
		return chng;
	}
	

	//public static void main(String[] args) throws SQLException, IOException {
		
		//double chng = getCurrentDayChng("HCLTECH.NS");
		//System.out.println(chng);
	//}

}
