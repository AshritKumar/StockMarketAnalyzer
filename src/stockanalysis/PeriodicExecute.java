package stockanalysis;

import java.util.concurrent.*;

public class  PeriodicExecute {
	public static final long everyDayDelay = 24*60*60*1000;
	public  PeriodicExecute(){
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		long timeToExecute = System.currentTimeMillis()+3*1000;
		service.scheduleWithFixedDelay(new SQLTask(), getTimeToLaunch(timeToExecute), everyDayDelay, TimeUnit.MILLISECONDS);
	}
	private long getTimeToLaunch(long timeToExecute){
        long current = System.currentTimeMillis();
        return timeToExecute + current;
    }

}
