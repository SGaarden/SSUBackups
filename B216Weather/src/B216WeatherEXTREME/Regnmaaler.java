package B216WeatherEXTREME;

public class Regnmaaler extends VejrData {
    private boolean rainingStatus = false;

    public Regnmaaler(int threadID) {
        super(threadID);
        this.vejrData = 0;
    }

    // Returnere om det regner pt.
    public boolean isItRaining() {
        return this.rainingStatus;
    }

    @Override
    public void run() {
        while(this.keepRunning) {
            int x = 0;
            double rainPower;

            if (Math.random() > 0.7) {
                rainPower = Math.random();
                this.rainingStatus = true;
            } else {
                rainPower = 0;
                this.rainingStatus = false;
            }

            while (x < 600) {
                try {
                    this.vejrData = this.vejrData + ((Math.random())*0.01)*rainPower;
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong");
                }
                x++;
            }
            System.out.println("New raining period started.");
            this.vejrData = 0;
        }
    }
}
