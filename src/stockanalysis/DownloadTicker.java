package stockanalysis;


import java.io.BufferedReader;
import java.io.FileReader;

public class DownloadTicker {
	
	
	private DownloadData down = new DownloadData();
	
	//method for getting NSE tickers from file and calling download function
	
	public void getNseTicker(String range)throws Exception{
		String ticker;
		FileReader fr = new FileReader("/home/nani/work/nsetest.txt");
		//System.out.println(fr); 
		String url;
		BufferedReader br = new BufferedReader(fr);
		while((ticker = br.readLine())!=null){
			String tick = ticker.concat(".NS");
			url = down.createUrl(tick,range);
			if(range.toLowerCase() =="w"){
				down.download(url, "STOCKT_NSE."+ticker.concat("_NS_W").replace('&', 'n'));
				}
			else if(range.toLowerCase()=="m"){
				down.download(url, "STOCKT_NSE."+ticker.concat("_NS_M").replace('&', 'n'));
			}
			else{
				down.download(url, "STOCKT_NSE."+ticker.concat("_NS").replace('&', 'n'));
			}
			
			 
		} 
	br.close();
	
	}
	
	public void getBseTicker(String range)throws Exception{
		String ticker;
		FileReader fr = new FileReader("/home/nani/workspace/marketanalysis/bseticker.txt");
		String url;
		BufferedReader br = new BufferedReader(fr);
		while((ticker = br.readLine())!=null){
			String tick = ticker.concat(".BO").trim();
			url = down.createUrl(tick,range);
			if(range.toLowerCase() == "w"){
				down.download(url, "STOCK_BSE."+ticker.concat("_BO_W").replace('&', 'n'));
			}
			else if(range.toLowerCase() == "m"){
				down.download(url, "STOCK_BSE."+ticker.concat("_BO_M").replace('&', 'n'));
			}
			else{
				down.download(url, "STOCK_BSE."+ticker.concat("_BO").replace('&', 'n'));
			}
			  
		} 
	br.close();
	}
	
	
	public static void main(String ar[]) throws Exception{
		DownloadTicker dt = new DownloadTicker();
		String range="d";
		dt.getNseTicker(range);
	}

}
