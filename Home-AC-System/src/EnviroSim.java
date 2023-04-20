/**
 * Simulates the environment
 */

import java.io.*;
import java.lang.Thread;

public class EnviroSim {
    private double roomTemp;
    private double roomHumidity;
    private double outsideTemp;
    private double tempChange;

    /**
     * Class Constructor
     * @param temp          initial room temp
     * @param humidity      initial room humidity
     * @param outsideTemp   the temperature outside the room
     */
    public EnviroSim(int temp, int humidity, int outsideTemp){
        this.roomTemp = temp;
        this.roomHumidity = humidity;
        this.outsideTemp = outsideTemp;
        tempChange = 0.00;
    }

    /**
     * The environment simulator, which runs on a 2Hz clock, updating every half second
     * @param time      Time to run the simulation for, in minutes
     */
    public void environmentSim(int time){
        try{
            for(int i = 0; i < time  * 120; i++){
                tempChange = (double)(outsideTemp - roomTemp) / 100.0;
                roomTemp += tempChange;
                System.out.println(roomTemp);
                Thread.sleep(5000);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Turns the heater on and off
     * @param heaterOn      heater power state | true - on | false - off |
     */
    public void heaterOn(boolean heaterOn){
        if(heaterOn){
            //TODO
        }
    }

    /**
     * Turns the cooler on and off
     * @param coolerOn      cooler power state | true - on | false - off |
     */
    public void coolerOn(boolean coolerOn){
        if(coolerOn){
            //TODO
        }
    }
}
