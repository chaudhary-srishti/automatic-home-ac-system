/**
 * Simulates the environment
 * This class will not handle heater/cooler logic, it is up to the caller to make sure the heater and cooler cannot be on at the same time
 * It is also up to the caller to turn on/off the cooler/heater when the temperature is at a certain degree
 * There is a simulated highest/lowest temperature that the AC can reach F: (60/100) C:(15.56/37.78)
 */

import java.io.*;
import java.lang.Thread;

public class EnviroSim {
    private double roomTemp;
    private double roomHumidity;
    private double outsideTemp;
    private double tempChange;

    private boolean heaterOn;
    private boolean coolerOn;

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
        heaterOn = false;
        coolerOn = false;
    }

    /**
     * The environment simulator, which runs on a 2Hz clock, updating every half second
     * @param time      Time to run the simulation for, in minutes
     */
    public void environmentSim(int time){
        try{
            for(int i = 0; i < time  * 120; i++){
                //The room temperature should always rise/fall to meet the outside temperature, but not too quickly
                //Using min will limit the rate at which the temperature changes
                if((double)(outsideTemp - roomTemp) >= 0){
                    tempChange = Math.min((double)(outsideTemp - roomTemp), 5) / 100.0;
                }
                else{
                    tempChange = Math.min((double)(outsideTemp - roomTemp), -5) / 100.0;
                }

                //heater can only affect the room temp if it is on and if the room temp isn't higher than the max heat of the heater
                if(heaterOn && roomTemp > 37.78){
                    tempChange = 0.1;
                }
                //cooler can only affect the room temp if it is on and if the room temp isn't lower than the max cooling of the cooler
                if(coolerOn && roomTemp < 15.56){
                    tempChange = -0.1;
                }

                //change the room temperature
                roomTemp += tempChange;

                System.out.println(roomTemp);   //for display purposes in the console
                Thread.sleep(5000);       //update every half second
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
    public void setHeater(boolean heaterOn){
        this.heaterOn = heaterOn;
    }

    /**
     * Turns the cooler on and off
     * @param coolerOn      cooler power state | true - on | false - off |
     */
    public void setCooler(boolean coolerOn){
        this.coolerOn = coolerOn;
    }

    public double getTemp(){
        return this.roomTemp;
    }

    public double getHumidity(){
        return this.roomHumidity;
    }
}
