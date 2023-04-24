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
            int[] data = new int[2];
            String file = "/Users/aakankshadesai/Documents/GitHub/automatic-home-ac-system/Home-AC-System/Data/inputOne.txt";//file path
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while(line != null)
            {
                List<String> lineData = Arrays.asList(line.split(","));//splitting lines
                System.out.println("mode :" + data[0]);
                line = br.readLine();
            }
            br.close();
        } catch(Exception e)
        {
            System.out.print(e);
        }

        try {
            for (int i=1; i<=3; i++) {


//                if (i==1) {
//                    thermostat.setSetTemp(autoSetting1[1]);
//                } else if (i==2) {
//                    thermostat.setSetTemp(autoSetting2[1]);
//                }
////                if (i==3) {
////                    thermostat.setSetTemp(heatSetting3[1]);
////                }

                Thread.sleep(1000 * 30);

            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "temp");
            thread.start();
        }
    }
}
