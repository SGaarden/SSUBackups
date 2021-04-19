package OO1RW;
import java.io.*;

public class OO1Read {
	
	public OO1Read() {
		
	}
	
	public static String[] ReadCSV(String filePath) throws IOException {
		// Make and initialize FileReader
		FileReader in = new FileReader(filePath);
		
		// String for the whole line
		String fullString = "";
		
		// Read full file
		int readAscii;
		while((readAscii = in.read()) != -1) {
			char c = (char) readAscii;
					
			fullString += c;
		}
		
		// Split full file by comma
		String[] splitStats = fullString.split(",");
		
		for (String str : splitStats){
			System.out.println(str);
		}
		
		// Close FileReader
		in.close();
		
		return splitStats;
	}
	
}
