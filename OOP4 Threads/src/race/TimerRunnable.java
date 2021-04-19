package race;

public class TimerRunnable extends RunnableBase implements Runnable {

	private int time = 0;
	
	public TimerRunnable(int id, Window window) {
		super(id, window);
	}
	
	// Sleep for 1 second
	public void doSleep() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(keepRunning()) {
			window.updateTimer(time);
			doSleep();
			time++;
		}
	}
}
