package illegalWords;

@SuppressWarnings("serial")
public class IllegalWordException extends Exception {
	
	private String errorMessage; // the error message to write in system output
	private String originalInput; // the original input that made the error be thrown
	
	public IllegalWordException(String errorMessage, String originalInput) {
		this.errorMessage = errorMessage; // the error message to write in system output
		this.originalInput = originalInput; // the original input that made the error be thrown
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getOriginalInput() {
		return originalInput;
	}
}
