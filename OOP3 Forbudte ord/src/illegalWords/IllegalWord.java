package illegalWords;

public class IllegalWord {

	private String illegalWord; // The illegal word to look for
	private int severityLevel; // The severity level of said illegal word
	
	public IllegalWord(String illegalWord, int severityLevel) {
		this.illegalWord = illegalWord;
		this.severityLevel = severityLevel;
	}
	
	public String getIllegalWord() {
		return illegalWord;
	}
	
	public int getSeverityLevel() {
		return severityLevel;
	}
	
}
