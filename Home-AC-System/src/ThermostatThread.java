/**
 * @author srishtichaudhary
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class ThermostatThread extends Thread {

    private Thread thread;

    private TempController tempController;
    private Thermostat thermostat;


    ThermostatThread(TempController tempController, Thermostat thermostat) {
        this.tempController = tempController;
        this.thermostat = thermostat;
    }

    public void run() {


        try {
            String file = "/Users/aakankshadesai/Documents/GitHub/automatic-home-ac-system/Home-AC-System/Data/inputOne.txt";//file path
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while(line != null)
            {
                String[] data = line.split(", ");
                thermostat.setMode(Integer.parseInt(data[0]));
                thermostat.setSetTemp(Integer.parseInt(data[0]));
                line = br.readLine();
            }
            br.close();
        } catch(Exception e)
        {
            System.out.print(e);
        }

    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "temp");
            thread.start();
        }
    }
}
