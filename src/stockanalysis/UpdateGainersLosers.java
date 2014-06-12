package stockanalysis;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UpdateGainersLosers {
	
	public static final long delay = 3*60*1000;
	public  UpdateGainersLosers(){
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		//long timeToExecute = System.currentTimeMillis()+3*1000;
		service.scheduleWithFixedDelay(new UpdateTask(), 3000, delay, TimeUnit.MILLISECONDS);
	}
	//3000--private long getTimeToLaunch(long timeToExecute){
       // long current = System.currentTimeMillis();
       // return  timeToExecute - current;
    //}


}
