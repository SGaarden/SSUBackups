package B216WeatherEXTREME;

public class VejrData extends Thread {
    protected double vejrData;
    protected boolean keepRunning = true;
    protected int threadID = 0;

    public VejrData(int id) {
        this.threadID = id;
    }

    // Kode til afrunding af decimaler.
    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    // Returnerer måleværdi fra sensor.
    public double getData() {
        return roundAvoid(this.vejrData,2);
    }

    // Luk tråd pænt ned.
    public void stopThread() {
        this.keepRunning = false;
    }

}
