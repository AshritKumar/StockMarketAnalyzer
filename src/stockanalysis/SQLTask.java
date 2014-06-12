package stockanalysis;

public class SQLTask implements Runnable{
	@Override
    public void run() {
		System.out.println("loaded the download servlet");
		DownloadTicker dt = new DownloadTicker();
		String range="d";
		System.out.println("calling the download data method");
		try {
			dt.getNseTicker(range);
		} catch (Exception e) {
			//responce.sendError(404);
			e.printStackTrace();
		}
	}

}
