package race;

public class ConsistentRacerRunnable extends RunnableBase implements Runnable, Racer {
	
	public ConsistentRacerRunnable(int racerID, Window window) {
		super(racerID, window);
	}
	
	// Sleep for 1 second
	@Override
	public void racerSleep() {
		// TODO Auto-generated method stub
		try {
            Thread.sleep(1L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	// Add a step to the Runnable's own tally, then send it to the window
	@Override
	public void addStep() {
		// TODO Auto-generated method stub
		stepsTaken++;
		try {
			window.updateRace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(stepsTaken >= 10) {
			window.updateWin(id);
		}
	}

	// This run code is the actual code the given racer performs
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		// Runs for as long as keepRunning returns true
		while(keepRunning()) {
			racerSleep();
			addStep();
		}
	}
}
