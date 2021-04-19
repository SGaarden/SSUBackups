package vektor;

public class Vektor {
	public static void main(String[] args) {
		String x = args[0];
		String y = args[1];
		
		Matematik mat = new Matematik();
		
		// Test 1
		double a = Double.parseDouble(x);
		double b = Double.parseDouble(y);
		mat.calcPythagoras(a, b);
		
		System.out.println("Test 1 (2, 3):");
		System.out.println(mat.returnAll() + "\n");
		
		// Test 2
		a = -3;
		b = 23;
		mat.calcPythagoras(a, b);
				
		System.out.println("Test 2 (-3, 23):");
		System.out.println(mat.returnAll() + "\n");
		
		// Test 3
		a = -5;
		b = -2;
		mat.calcPythagoras(a, b);
				
		System.out.println("Test 3 (-5, -2):");
		System.out.println(mat.returnAll() + "\n");
	}

}
