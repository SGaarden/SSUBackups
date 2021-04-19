package B216WeatherEXTREME;

import java.io.*;
import java.util.Scanner;

public class Vejrstation {
	// Variable arrays to store data
	private static final int blockSize = 20;
	private static double[] tempData = new double[blockSize];
	private static double[] regnData = new double[blockSize];
	private static double[] fugtData = new double[blockSize];
	public static int entryCounter = 0;
	
	private int TmpWriteCntr = 0;
	
	private String TmpFilePath = "TmpLog.txt";

	private Thread vejrstationThread;
	
	public Vejrstation(Runnable runnable) {
		// Make thread and start it
		vejrstationThread = new Thread(runnable);
		vejrstationThread.start();
	}

	public void deleteTemp() throws IOException {
		File tempFile = new File(TmpFilePath);
		if(tempFile.delete()) {
			System.out.println("Temp-file deleted.");
		} else {
			System.out.println("Couldn't delete temp-file.");
		}
	}

	private void appendTmpLog() throws IOException {
		// Make and initialize FileWriter, passing true means append
		FileWriter TmpOut = new FileWriter(TmpFilePath, true);

		// Initialize a temporary string
		String TmpString = "";

		for(int i = 0; i < blockSize; i++) {
			// Write the reading number to the temporary string, and count it one up
			TmpString = TmpString.concat(Integer.toString(this.TmpWriteCntr) + ",");
			this.TmpWriteCntr++;

			// Add the values to the temporary string
			TmpString = TmpString.concat(Double.toString(tempData[i]) + ",");
			TmpString = TmpString.concat(Double.toString(regnData[i]) + ",");
			TmpString = TmpString.concat(Double.toString(fugtData[i]) + "\n");
		}

		// Write to the temporary file
		TmpOut.write(TmpString + "\n");

		// Close the connection to the temporary file
		TmpOut.close();
		System.out.println("Saved to temp-file.");
	}

	public String saveLogAs() throws IOException {
		// Initialize scanner, make it use delimiter separating lines
		Scanner sc = new Scanner(new File(TmpFilePath));
		sc.useDelimiter("\n");

		// Initialize FileWriter
		String logFilePath = String.format("WeatherLog-%s.txt", java.time.LocalDateTime.now()).replace(":", "-");
		FileWriter LogOut = new FileWriter(logFilePath);

		// Write a header to the file
		LogOut.write("LogCounter,tempData,regnData,fugtData" + "\n");

		// Read the temporary log file, and write it into a String
		String tmpFileData = "";

		while(sc.hasNext()) {
			tmpFileData = tmpFileData.concat(sc.next() + "\n");
		}

		// Write the string to the file
		LogOut.write(tmpFileData);

		// Close scanner and FileWriter
		sc.close();
		LogOut.close();

		return logFilePath;
	}

	public void addData(double[] dataArray) {
		tempData[entryCounter] = dataArray[0];
		regnData[entryCounter] = dataArray[1];
		fugtData[entryCounter] = dataArray[2];

		entryCounter++;

		if(entryCounter > blockSize - 1) {
			try {
				appendTmpLog();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			entryCounter = 0;
		}
	}
}
