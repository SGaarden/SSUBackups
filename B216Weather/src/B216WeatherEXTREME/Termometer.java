package B216WeatherEXTREME;

public class Termometer extends VejrData {
    public Termometer(int threadID) {
        super(threadID);
        this.vejrData = 20;
    }

    @Override
    public void run() {
        while(this.keepRunning) {
            try {
                this.vejrData = this.vejrData + ((Math.random() - 0.5)*0.1);
                if (this.vejrData > 50) {
                    this.vejrData = 50;
                } else if (this.vejrData < -20) {
                    this.vejrData = 20;
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
