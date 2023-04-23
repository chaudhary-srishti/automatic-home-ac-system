/**
 * TempController - Handle temperature logic and activate AC hardware
 */

public class TempController {
    //state variables
    private int mode;           //Mode indicator, defined as an enum (0 - Off, 1 - Cool, 2 - Heat, 3 - Auto)
    private int temp;           //Current room temperature
    private boolean coolerAck;  //Event to be sent to the cooler
    private boolean heaterAck;  //Event to be sent to the heater
    //inputs
    private int avgTemp;

    /**
     * Class constructor
     */
    public TempController(){
        this.mode = 1;
        this.temp = 25;
        this.coolerAck = false;
        this.heaterAck = false;
    }

    /**
     * Update function, called any time setMode, setTemp, or avgTemp are changed
     * @return      A boolean array with the elements representing coolerOn and heaterOn respectively
     */
    public boolean[] update(){
        //if in cool or auto mode
        if(mode == 1 || mode == 3){
            if(temp <= avgTemp - 1.5 && !coolerAck){
                coolerAck = true;
                heaterAck = false;
                return (new boolean[]{true, false}); //turn on cooler, turn off heater
            } else if (coolerAck) {
                coolerAck = false;
            }
        }

        //if in heat or auto mode
        if(mode == 2 || mode == 3){
            if(temp >= avgTemp + 1.5 && !heaterAck){
                coolerAck = false;
                heaterAck = true;
                return (new boolean[]{false, true}); //turn off cooler, turn on heater
            } else if (heaterAck) {
                heaterAck = false;
            }
        }

        //this statement is only reachable if mode == 0
//        coolerAck = false;
//        heaterAck = false;
        return (new boolean[]{false, false}); //turn off cooler, turn on heater
    }

    public void setMode(int setMode){
        this.mode = setMode;
        update();
    }

    public void setTemp(int setTemp){
        this.temp = setTemp;
        update();
    }

    public void setAvgTemp(int temp){
        avgTemp = temp;
        update();
    }

    public int getMode(){
        return this.mode;
    }

    public int getTemp(){
        return this.temp;
    }

    public boolean getCoolerState() { return this.coolerAck; }

    public boolean getHeaterState() { return this.heaterAck; }
}