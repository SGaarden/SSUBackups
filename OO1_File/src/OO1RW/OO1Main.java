package OO1RW;

import java.io.IOException;

public class OO1Main {
	
	public static void main(String args[]) throws IOException {
		
		// Read file
		String[] parsedFile = OO1Read.ReadCSV("intFile.csv");
		
		// Sort file
		SortedList sortedList = new SortedList(parsedFile);
		
		for(int i = 0; i < sortedList.exportList.length; i++) {
			System.out.println(sortedList.exportList[i]);
		}
		
		// Write file
		OO1Write.WriteCSV(sortedList.exportList, "statsFile.txt");
	}
}
