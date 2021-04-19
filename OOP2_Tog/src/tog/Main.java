package tog;

public class Main {
	
	public static void main(String[] args) {
		RailVehicle[] train1 = new RailVehicle[4];
		train1[0] = new Locomotive(1);
		train1[1] = new FreightCar(2);
		train1[2] = new FreightCar(3);
		train1[3] = new FreightCar(4);
		
		RailVehicle[] train2 = new RailVehicle[4];
		train2[0] = new Locomotive(1);
		train2[1] = new PassengerCar(2);
		train2[2] = new PassengerCar(3);
		train2[3] = new PassengerCar(4);
		
		train1[0].setCargo("Lokomotivfører Kaj");
		train2[0].setCargo("Lokomotivfører Torben");
		
		train1[1].setCargo("22 passagerer");
		train1[2].setCargo("25 passagerer");
		train1[3].setCargo("30 passagerer");
		
		train2[1].setCargo("100 diamanter");
		train2[2].setCargo("100 smaragder");
		train2[3].setCargo("100 ametyster");
		
		for(int i = 0; i < train1.length; i++) {
			train1[i].print();
		}
		
		for(int i = 0; i < train2.length; i++) {
			train2[i].print();
		}
		
	}
	
}
