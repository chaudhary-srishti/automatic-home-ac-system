/**
 * Simulates the environment
 * This class will not handle heater/cooler logic, it is up to the caller to make sure the heater and cooler cannot be on at the same time
 * It is also up to the caller to turn on/off the cooler/heater when the temperature is at a certain degree
 * There is a simulated highest/lowest temperature that the AC can reach F: (60/100) C:(15.56/37.78)
 */

import java.util.*;
import java.lang.*;
import java.lang.Thread;

public class EnviroSim {
    private double roomTemp;
    private double roomHumidity;
    private double outsideTemp;
    private double tempChange;

    private boolean heaterOn;
    private boolean coolerOn;

    private TempCollector tempCollector;
    private TempController tempController;
    private ModeController modeController;
    private TempInputController tempInputController;
    private HumidityCollector humidityCollector;
    private FanController fanController;
    private Thermostat thermostat;

    /**
     * Class Constructor
     */
    public EnviroSim(int temp, int outsideTemp, TempCollector tempCollector, TempController tempController, ModeController modeController, TempInputController tempInputController, HumidityCollector humidityCollector, Thermostat thermostat, FanController fanController){
        this.roomTemp = temp;
        this.outsideTemp = outsideTemp;
        this.tempCollector = tempCollector;
        this.tempController = tempController;
        this.modeController = modeController;
        this.tempInputController = tempInputController;
        this.humidityCollector = humidityCollector;
        this.fanController = fanController;
        this.thermostat = thermostat;
        tempChange = 0.00;
        heaterOn = false;
        coolerOn = false;
    }

    /**
     * The environment simulator, which runs on a 2Hz clock, updating every half second
     * @param time      Time to run the simulation for, in minutes
     */
    public void environmentSim(int time){

        Thread thermostatThread = new ThermostatThread(this.tempController, this.thermostat);
        thermostatThread.start();

        Random rand = new Random();
        int humidityOffset = rand.nextInt(20);

        try{
            for(int i = 0; i < time * 120; i++){

                System.out.println("");
                System.out.println("Iteration: " + (int)(i + 1));

                //The room temperature should always rise/fall to meet the outside temperature, but not too quickly
                //Using min will limit the rate at which the temperature changes
                if((double)(outsideTemp - roomTemp) >= 0){
                    tempChange = Math.min((double)(outsideTemp - roomTemp), 5) / 100.0;
                }
                else{
                    tempChange = Math.min((double)(outsideTemp - roomTemp), -5) / 100.0;
                }

                //heater can only affect the room temp if it is on and if the room temp isn't higher than the max heat of the heater
                if(heaterOn && roomTemp < 37.78){
                    tempChange = 0.1;
                }
                //cooler can only affect the room temp if it is on and if the room temp isn't lower than the max cooling of the cooler
                if(coolerOn && roomTemp > 15.56){
                    tempChange = -0.1;
                }

                //change the room temperature
                roomTemp += tempChange;

                //humidity runs on a sin wave
                roomHumidity = 20 * Math.sin(0.007 * i - humidityOffset) + 40;

                // Start Temperature change thread
                Thread tempThread = new TempCollectorThread(roomTemp, (int) roomHumidity, this.tempCollector, this.tempController, this.humidityCollector, this.thermostat);
                tempThread.start();

                try {
                    thermostatThread.join();
                    tempThread.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }

                // Get the state of the cooler from TempController
                boolean coolerState = this.tempController.getCoolerState();
                // Get the state of the heater from TempController
                boolean heaterState = this.tempController.getHeaterState();

                setCooler(coolerState);
                setHeater(heaterState);

                thermostat.printState();

                //print environment state
                System.out.println("================= Env State ======================================");
                System.out.printf("Room Temperature: %.2f \n", roomTemp);
                System.out.printf("Room Humidity: %.2f \n", roomHumidity);
                System.out.println("Cooler: " + coolerState + "    Heater: " + heaterState + "    Fan: " + fanController.getFanState());
                System.out.println("==================================================================");

                Thread.sleep(500);       //update every half second
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
