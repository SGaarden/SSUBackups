package race;

public abstract class RunnableBase implements Runnable {
	
	public int id;
	public int stepsTaken = 0;
	Window window;
	
	public RunnableBase(int id, Window window) {
		this.id = id;
		this.window = window;
	}
	
	// Boolean for determining stop
	private boolean doStop = false;
	
	// Make thread stop
	public synchronized void doStop() {
		// TODO Auto-generated method stub
		this.doStop = true;
	}

	// Returns the boolean value of a check for whether or not doStop is false
	public synchronized boolean keepRunning() {
		// TODO Auto-generated method stub
		return this.doStop == false;
	}
	
}
