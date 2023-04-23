/**
 * @author srishtichaudhary
 */
public class TempCollectorThread extends Thread {

    private Thread thread;
    private int temp;
    private TempCollector tempCollector;
    private TempController tempController;

    TempCollectorThread(int temp, TempCollector tempCollector, TempController tempController) {
        this.temp = temp;
        this.tempCollector = tempCollector;
        this.tempController = tempController;
    }

    public void run() {
        synchronized (tempController) {
            // Get avTemp every 30 secs
            int avTemp = this.tempCollector.sendTempValue(temp);
            if (avTemp != -1) {
                // Set avTemp to trigger cooler and heater
                this.tempController.setAvgTemp(avTemp);
            }
        }

    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "temp");
            thread.start();
        }
    }

}
