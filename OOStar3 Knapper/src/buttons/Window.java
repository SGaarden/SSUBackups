package buttons;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements WindowListener, ActionListener {

	private JTextField text = new JTextField(20);
	private JButton bandButton;
	private JButton exceptionButton;
	private int exceptionCounter = 0;
	
	public Window(String title) {
		super(title);
		this.setSize(350,100);
		this.setVisible(true);
		
		this.setLayout(new FlowLayout());
		this.addWindowListener(this);
		
		bandButton = new JButton("Band");
		this.add(bandButton);
		bandButton.addActionListener(this);
		
		exceptionButton = new JButton("Exception");
		this.add(exceptionButton);
		exceptionButton.addActionListener(this);
		
		this.add(text);
		text.setEditable(false);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bandButton) {
			System.out.println("Yndlingsband: Aviators");
		}
		
		if(e.getSource() == exceptionButton) {
			try {
				throw new Exception("Exception reached");
			}
			catch(Exception ex){
				exceptionCounter++;
				text.setText("Antal exceptions: " + exceptionCounter);
			}
		}
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
