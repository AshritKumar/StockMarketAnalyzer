package stockanalysis;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.StringTokenizer;


public class CurrentDetails {
	
	public double close,high,low,open,volume,lowest,highest,totVol;
	public Date cdate;
	public void getCurrentDetails(String ticker) throws IOException{
		try{
		String fileurl = "http://chartapi.finance.yahoo.com/instrument/1.0/"+ticker+"/chartdata;type=quote;range=1d/csv";
		int lineno=0;
		URL url = new URL(fileurl); 
		URLConnection ucon = url.openConnection();
		InputStreamReader in = new InputStreamReader(ucon.getInputStream());
		BufferedReader buff = new BufferedReader(in);
		for(int i=0;i<17;i++){
		buff.readLine(); 
		}
		while(buff.readLine()!=null){
					lineno++;
			//System.out.println(buff.readLine());
		}
		//System.out.println(lineno);
		 in.close();
		 buff.close();
		URLConnection uconn = url.openConnection();
		InputStreamReader ins = new InputStreamReader(uconn.getInputStream());
		BufferedReader br = new BufferedReader(ins);
		//System.out.println(br.readLine());
		for(int i=0;i<17;i++){ 
			br.readLine(); 
			}
		//System.out.println("*********");
		String dtl[] = br.readLine().split(",");
		//System.out.println("++++++++++");
		open = Double.parseDouble(dtl[4]);
		double l1 = Double.parseDouble(dtl[3]);
		double h1 = Double.parseDouble(dtl[2]);
		double vol = Double.parseDouble(dtl[5]);
		//System.out.println("****"+(double)vol);
		//System.out.println(vol);
			for(int j=1;j<lineno-1;j++){
				double h;
				String dt[] = br.readLine().split(",");
			//StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				double l2 = Double.parseDouble(dt[3]);
				double h2 = Double.parseDouble(dt[2]);
				double v = Double.parseDouble(dt[5]);
				vol = vol+v;
				//System.out.println(v);
				//System.out.println(h2);
				if(l2<l1){
					l1=l2;
				}
				
				
				if(h2>h1){
					 h1 = h2;
					//for finding highest of day
				}
				
				//System.out.println("in if  :"+(double)h1);
				}
			//highest = h1;
			//lowest = l1;
			String s = br.readLine();
			
			StringTokenizer str = new StringTokenizer(s, ",");
			long timestamp = 0;
			
			while(str.hasMoreTokens()){
			
			timestamp = Long.parseLong(str.nextToken());
			cdate = new Date(timestamp*1000);
			//System.out.println(d);
 			close = Double.parseDouble(str.nextToken());
			high = Double.parseDouble(str.nextToken());
			low = Double.parseDouble(str.nextToken());
			Double.parseDouble(str.nextToken());
			volume = Double.parseDouble(str.nextToken());		
			}
			if(h1>high){
				highest = h1;
			}
			else{
				highest = high;
			}
			if(l1<low){
				lowest  = l1;
			}
			else
				lowest = low;
			totVol = vol+volume;
			//System.out.println(volume);	
			//System.out.println(br.readLine());
			//System.out.println(s);
			//System.out.println("lowest  "+(double)lowest);
			//System.out.println("highest:  "+(double)highest);
			//System.out.println("total  volume:  "+(double)totVol);
			//System.out.println(cdate);
			//System.out.println(close);
			
			
			ins.close();
			br.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
	  }
	
	  public static void main(String arg[]) throws IOException{
		new CurrentDetails().getCurrentDetails("INFY.NS");
	}
}
