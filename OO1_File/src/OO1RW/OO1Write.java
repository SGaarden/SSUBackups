package OO1RW;
import java.io.*;

public class OO1Write {

	public OO1Write() {
		
	}
	
	public static void WriteCSV(String[] exportList, String filePath) throws IOException {
		// Make and initialize FileWriter
		FileWriter out = new FileWriter(filePath);
		
		for(String str : exportList) {
			out.write(str + "\n");
		}
		
		out.close();
	}
	
}
