/**
 * @author srishtichaudhary
 */
public class Thermostat {

    private int mode;
    private int setTemp;
    private int humidity;
    private boolean fanON;

    private ModeController modeController;
    private TempInputController tempInputController;

    Thermostat(ModeController modeController, TempInputController tempInputController) {
        this.mode = 1;
        this.setTemp = 24;
        this.humidity = 10;
        this.fanON = true;
        this.modeController = modeController;
        this.tempInputController = tempInputController;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setSetTemp(int setTemp) {
        this.setTemp = setTemp;
        tempInputController.setTemp(setTemp);
    }

    public int getSetTemp() {
        return setTemp;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setFanON(boolean fanON) {
        this.fanON = fanON;
    }

    public boolean isFanON() {
        return fanON;
    }

    public void printState() {
        System.out.println("");
        System.out.println("=========== Thermostat ==========");
        System.out.println("Desired Temp: " + getSetTemp());
        System.out.println("Desired Mode: " + getMode());
        System.out.println("Fan: " + isFanON());
        System.out.println("Humidity: " + getHumidity());
        System.out.println("=================================");
        System.out.println("");
    }


}
