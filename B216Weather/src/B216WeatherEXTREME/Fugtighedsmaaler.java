package B216WeatherEXTREME;

public class Fugtighedsmaaler extends VejrData {
    Regnmaaler rainsensoraddress;

    public Fugtighedsmaaler(int threadID) {
        super(threadID);
        this.vejrData = 40;
    }

    public void rainSensorAddress(Regnmaaler sensor) {
        this.rainsensoraddress = sensor;
    }

    @Override
    public void run() {
        while(this.keepRunning) {
            try {
                double rain;
                if (rainsensoraddress.isItRaining()) {
                    rain = Math.random()*0.08;
                } else {
                    rain = (Math.random()-0.65)*0.05;
                }

                this.vejrData = this.vejrData + (rain);
                if (this.vejrData > 100) {
                    this.vejrData = 100;
                } else if (this.vejrData < 15) {
                    this.vejrData = 15;
                }

                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
