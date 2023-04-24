/**
 * @author srishtichaudhary
 */
public class Thermostat {

    private int mode;
    private int setTemp;
    private int roomTemp;
    private int humidity;
    private boolean fanON;

    private ModeController modeController;
    private TempInputController tempInputController;

    Thermostat(int avTemp, ModeController modeController, TempInputController tempInputController) {
        this.mode = 1;
        this.setTemp = 24;
        this.roomTemp = avTemp;
        this.humidity = 30;
        this.fanON = true;
        this.modeController = modeController;
        this.tempInputController = tempInputController;
    }

    public void setMode(int mode) {
        this.mode = mode;
        modeController.setMode(mode);
    }

    public int getMode() {
        return mode;
    }

    public String getModeState() {
        int intMode = getMode();

        if (intMode == 1) {
            return "COOL";
        } else if (intMode == 2) {
            return "HEAT";
        } else {
            return "AUTO";
        }
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

    public void setRoomTemp(int roomTemp) {
        this.roomTemp = roomTemp;
    }

    public int getRoomTemp() {
        return roomTemp;
    }

    public void printState() {
        System.out.println("");
        System.out.println("================ Thermostat ======================================");
        System.out.println("Desired Temp: " + getSetTemp());
        System.out.println("Desired Mode: " + getModeState());
        System.out.println("Fan: " + isFanON());
        System.out.println("Room Temperature: " + getRoomTemp());
        System.out.println("Room Humidity: " + getHumidity());
        System.out.println("==================================================================");
        System.out.println("");
    }


}
