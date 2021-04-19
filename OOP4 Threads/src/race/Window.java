package race;

import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Window extends JFrame implements WindowListener, ActionListener {

	private JTextArea timerText;
	private JTextArea raceText;
	private JButton raceButton;
	private String newline = "\n";
	
	private int time = 0;
	
	TimerRunnable timer;
	Thread timerThread;
	
	protected int racerAmount = 2;
	Thread racer1Thread;
	Thread racer2Thread;
	
	RunnableBase[] racerArray = new RunnableBase[racerAmount];
	
	public Window(String title) {
		super(title);
		
		// BoxLayout with Y_AXIS puts components top to bottom
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addWindowListener(this);
		
		// Initialize timerText, and place in its own JPanel with FlowLayout to center it
		JPanel timerPanel = new JPanel();
		timerPanel.setLayout(new FlowLayout());
		
		timerText = new JTextArea(1, 20);
		timerText.setEditable(false);
		timerPanel.add(timerText);
		this.add(timerPanel);
		
		// Initialize raceText and place it after timerPanel
		raceText = new JTextArea(racerAmount, 20);
		raceText.setEditable(false);
		this.add(raceText);
		
		// Initialize raceButton, place in its own JPanel with FlowLayout to center it, then place it after raceText
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		raceButton = new JButton("Race!");
		raceButton.addActionListener(this);
		
		buttonPanel.add(raceButton);
		this.add(buttonPanel);
		
		// Add a few standard values just so it looks pretty from the start
		timerText.setText("Press the \"Race!\" button to start the race!");
		
		String startText = "";
		for(int i = 0; i < racerAmount; i++) {
			startText += "Racer " + (i+1) + ": " + newline;
		}
		raceText.setText(startText);
		
		// pack makes the window the size the components want
		this.pack();
		this.setVisible(true);
	}
	
	// Update timer text. This should only ever be called by timer thread
	public synchronized void updateTimer(int time) {
		this.time = time;
		timerText.setText(this.time + " seconds have passed since start");
	}
	
	public synchronized void updateRace() {
		String raceString = "";
		
		for(int i = 0; i < racerArray.length; i++) {
			raceString += "Racer " + racerArray[i].id + ": ";
			
			for(int j = 0; j < 11; j++) {
				if(j == 10) {
					raceString += "|";
				}
				else if(j <= racerArray[i].stepsTaken) {
					raceString += "o";
				}
				else {
					raceString += " ";
				}
			}
			
			raceString += newline;
		}
		
		raceText.setText(raceString);
	}
	
	// Show winner, show final race setup, then make button visible
	public void updateWin(int winner) {
		System.out.println("Someone has won.");
		
		for(RunnableBase racer : racerArray) {
			racer.doStop();
		}
		timer.doStop();
		
		updateRace();
		
		timerText.setText("Racer " + winner + " wins in " + time + "seconds!");
		raceButton.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer = new TimerRunnable(3, this);
		timerThread = new Thread(timer);
		
		racerArray[0] = new ConsistentRacerRunnable(1, this);
		racerArray[1] = new InconsistentRacerRunnable(2, this);
		
		racer1Thread = new Thread(racerArray[0]);
		racer2Thread = new Thread(racerArray[1]);
		
		
		racer1Thread.start();
		racer2Thread.start();
		timerThread.start();
		
		raceButton.setVisible(false);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		System.exit(0);
	}

	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
