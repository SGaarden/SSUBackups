package vektor;

public class Matematik {

	private double a;
	private double b;
	private double result;
	
	public Matematik() {
		
	}
	
	public void calcPythagoras(double a, double b) {
		this.a = a;
		this.b = b;
		this.result = Math.sqrt(this.a*this.a + this.b*this.b);
	}
	
	public double returnResult() {
		return result;
	}
	
	public String returnCoords() {
		String returnString = "x = " + String.valueOf(this.a) + "\n" + "y = " + String.valueOf(this.b);
		return returnString;
	}
	
	public String returnAll() {		
		String returnString = "Result = " + returnResult() + "\n" + returnCoords();
		return returnString;
	}
	
}
