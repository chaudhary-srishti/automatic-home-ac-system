/**
 * @author srishtichaudhary
 */
public class TempCollectorThread extends Thread {
    private Thread thread;
    private double temp;
    private int humidity;
    private TempCollector tempCollector;
    private TempController tempController;
    private HumidityCollector humidityCollector;
    private Thermostat thermostat;

    TempCollectorThread(double temp, int humidity, TempCollector tempCollector, TempController tempController, HumidityCollector humidityCollector, Thermostat thermostat) {
        this.temp = temp;
        this.humidity = humidity;
        this.tempCollector = tempCollector;
        this.tempController = tempController;
        this.thermostat = thermostat;
        this.humidityCollector = humidityCollector;
    }

    public void run() {
        synchronized (tempController) {
            // Get avTemp every 30 secs
            int avTemp = this.tempCollector.sendTempValue(temp);
            if (avTemp != -1) {
                // Set avTemp to trigger cooler and heater
                this.tempController.setAvgTemp(avTemp);
                this.thermostat.setRoomTemp(avTemp);
            }

            int avHumidity = this.humidityCollector.sendHumidityValue(humidity);
            if (avHumidity != -1) {
                // Set avTemp to trigger cooler and heater
                this.thermostat.setHumidity(avHumidity);
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
