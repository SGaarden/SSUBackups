package illegalWords;

import java.awt.event.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
public class Window extends JFrame implements WindowListener, ActionListener {

	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public Window(String title) {
		super(title);
		
		// BoxLayout with Y_AXIS puts components top to bottom
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addWindowListener(this);
		
		// textField is the place to write stuff in
		textField = new JTextField(20);
		textField.addActionListener(this);
		this.add(textField);
		
		// textArea is the place to read
		textArea = new JTextArea(10, 20); // 10, 20 denotes height and width of text area
		textArea.setEditable(false); // set to false to avoid confusion
		this.add(textArea);
		
		// Scroll appears when you write enough
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		
		// pack makes the window the size the components want
		this.pack();
		this.setVisible(true);
	}
	
	public void checkWords(String input) throws IllegalWordException {
		// Array of illegal words
		IllegalWord[] illegalWords = new IllegalWord[3];
		illegalWords[0] = new IllegalWord("Biologi", 1);
		illegalWords[1] = new IllegalWord("Biotech", 2);
		illegalWords[2] = new IllegalWord("Kemi", 3);
		
		String illegalWordsUsed = ""; // Initialized to populate in for loop
		int highestSeverityLevel = 0;
		for(IllegalWord iw : illegalWords) { // Runs through whole array
			if(input.toLowerCase().contains(iw.getIllegalWord().toLowerCase())) { // If the input matches one of the words (made lowercase to ensure it is found)
				if(illegalWordsUsed.equals("")) {
					illegalWordsUsed += iw.getIllegalWord();
				}
				else {
					illegalWordsUsed += ", " + iw.getIllegalWord();
				}
				
				if(iw.getSeverityLevel() > highestSeverityLevel) {
					highestSeverityLevel = iw.getSeverityLevel();
				}
			}
		}
		
		if(illegalWordsUsed.isEmpty() == false) { // If illegalWordUsed is populated, throw exception
			throw new IllegalWordException(
					"User entered illegal word(s): " + 
					illegalWordsUsed + 
					" Severity level: " + 
					highestSeverityLevel
					, input);
		}
	}
	
	// Simple text writer
	public void addToLog(String input) throws IOException {
		FileWriter out = new FileWriter("log.txt", true);
		
		out.write(input + "\n");
		
		out.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(textField.getText() == "") { // If textField is empty
			System.out.println("textField is empty.");
		}
		else {
			try {
				String input = textField.getText(); // Grab text from textField
				
				checkWords(input); // check if any of the words are illegal
				
				textArea.append(input + "\n"); // Write in textArea
				textField.setText(""); // Make textField empty
			}
			catch(IllegalWordException iwex) { // If an illegal word is found
				System.out.println(iwex.getErrorMessage()); // Print error message to system
				
				textArea.append(iwex.getOriginalInput() + "\n"); // Write in textArea
				textField.setText(""); // Make textField empty
				
				try {
					addToLog(iwex.getErrorMessage()); // Write to log
				}
				catch(IOException ioex) {
					System.out.println(ioex); // Write io exception
				}
				
				textField.setText(""); // Make textField empty
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
