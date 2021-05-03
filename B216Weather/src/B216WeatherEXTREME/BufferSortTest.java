package B216WeatherEXTREME;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BufferSortTest {
	private static final int blockSize = 20;
	private static double[] tempData = new double[blockSize];
	
	private static Random r = new Random();
	
	public static void main(String[] args) {
		System.out.println("Starting test.");
		for(int i = 0; i < blockSize; i++) {
			int value = r.nextInt(80);
			tempData[i] = value;
		}
		
		BufferSort tempBufferSorted = new BufferSort(tempData, blockSize);
		
		try {
			writeToFile(tempBufferSorted);
			System.out.println("Test complete.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeToFile(BufferSort sortedBuffer) throws IOException {
		String bufferFilePath = String.format("BufferLog-%s.txt", java.time.LocalDateTime.now()).replace(":", "-");
		FileWriter LogOut = new FileWriter(bufferFilePath);
		
		LogOut.write("IntervalMin,IntervalMax,Counts" + "\n");
		
		for(int i = 0; i < sortedBuffer.getData().length; i++) {
			String lineToAdd = "";
			
			lineToAdd = lineToAdd.concat(Integer.toString(sortedBuffer.getIntervalWidth() * i) + ",");
			
			lineToAdd = lineToAdd.concat(Integer.toString(sortedBuffer.getIntervalWidth() * (i+1)) + ",");
			
			lineToAdd = lineToAdd.concat(Integer.toString(sortedBuffer.getData()[i]));
			
			LogOut.write(lineToAdd + "\n");
		}
		
		LogOut.close();
	}
}
