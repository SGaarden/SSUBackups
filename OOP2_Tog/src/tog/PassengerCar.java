package tog;

public class PassengerCar extends Vehicle implements RailVehicle {

	public PassengerCar(int number) {
		this.type = "Passenger car";
		this.number = number;
	}

	public void print() {
		String outString = "";
		
		outString = outString.concat("Type: " + this.type);
		outString = outString.concat(" Number: " + this.number);
		outString = outString.concat(" Cargo: " + this.cargo);
		
		System.out.println(outString);
		
	}

	public void setCargo(String load) {
		this.cargo = load;
	}
}
