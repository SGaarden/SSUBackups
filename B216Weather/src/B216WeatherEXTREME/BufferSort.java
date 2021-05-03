package B216WeatherEXTREME;

public class BufferSort {
	
	private int intervalCount;
	private int dataRange;
	private int intervalWidth;
	
	private int[] occurrenceIntervals;
	
	public BufferSort(double[] data, int blockSize) {
		this.intervalWidth = 8;
		this.intervalCount = (int) Math.ceil(getMaxValue(data) / (double) 8);
		// this.dataRange = getMaxValue(data) - getMinValue(data);
				
		System.out.print("Interval count: "); System.out.println(intervalCount);
		System.out.print("Data range: "); System.out.println(dataRange);
		System.out.print("Interval width: "); System.out.println(intervalWidth);
		
		this.occurrenceIntervals = sortData(data, blockSize);
	}
	
	public int getIntervalCount() {
		return this.intervalCount;
	}
	
	public int getDataRange() {
		return this.dataRange;
	}
	
	public int getIntervalWidth() {
		return this.intervalWidth;
	}
	
	public int[] getData() {
		return this.occurrenceIntervals;
	}
	
	private static int getMaxValue(double[] data) {
		int max = (int) data[0];
		
		for (double dataPoint : data) {
			int intData = (int) dataPoint;
			if(intData > max) {
				max = intData;
			}
		}
		
		System.out.print("Maximum value: "); System.out.println(max);
		return max;
	}
	
	private static int getMinValue(double[] data) {
		int min = (int) data[0];
		
		for (double dataPoint : data) {
			int intData = (int) dataPoint;
			if(intData < min) {
				min = intData;
			}
		}
		
		System.out.print("Minimum value: "); System.out.println(min);
		return min;
	}
	
	private int[] sortData(double[] data, int blockSize) {
		int[] occurrenceIntervals = new int[this.intervalCount];
		
		for (double dataPoint : data) {
			occurrenceIntervals[(int) dataPoint / this.intervalWidth] += 1;
		}
		
		return occurrenceIntervals;
	}
}
