package stockanalysis;

public class UpdateTask implements Runnable {
	CurrentDayGainerLooser cd = new CurrentDayGainerLooser();
	@Override
    public void run() {
		System.out.println("calling insertTOGL()");
		cd.insertIntoGL();
	}

}
