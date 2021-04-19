package OO1RW;

public class SortedList {

	String[] exportList;
	
	public SortedList(String[] strArray) {
		
		// Count amounts
		int[] counts = CountNumbers(strArray);
		
		// Write as strings
		exportList = WriteExportList(counts);
		
	}
	
	private static int[] CountNumbers(String[] strArray) {
		// Initialize array to return
		int[] counts = new int[20];
		
		// Run for loop from 1 through 20
		for(int i = 0; i < 20; i++) {
			// Run for loop for length of string array
			for(int j = 0; j < strArray.length; j++) {
				// If that string array has that number, increment
				try {
					if(Integer.parseInt(strArray[j]) == i + 1) {
						counts[i] += 1;
					}
				}
				catch (NumberFormatException e) {
					counts[i] += 1;
				}
			}
		}
		
		return counts;
	}
	
	private static String[] WriteExportList(int[] counts) {
		// Initialize array to return
		String[] exportList = new String[20];
		
		// Run for loop from 1 through 20
		for(int i = 0; i < 20; i++) {
			// Format will be "1: 13"
			exportList[i] = (Integer.toString(i + 1)) + ": " + counts[i];
		}
		
		return exportList;
	}
}
