/**
 * @author srishtichaudhary
 */

import java.util.*;

public class ThermostatThread extends Thread {

    private Thread thread;

    private TempController tempController;
    private Thermostat thermostat;

    private int[] coolSetting1 = new int[]{1, 18};
    private int[] coolSetting2 = new int[]{1, 20};
    private int[] coolSetting3 = new int[]{1, 24};

    private int[] heatSetting1 = new int[]{2, 26};
    private int[] heatSetting2 = new int[]{2, 25};
    private int[] heatSetting3 = new int[]{2, 28};

    private int[] autoSetting1 = new int[]{3, 20};
    private int[] autoSetting2 = new int[]{3, 24};

    ThermostatThread(TempController tempController, Thermostat thermostat) {
        this.tempController = tempController;
        this.thermostat = thermostat;
    }

    public void run() {

        thermostat.setMode(1);

        try {
            for (var i=1; i<=3; i++) {

                if (i==1) {
                    thermostat.setSetTemp(coolSetting1[1]);
                } else if (i==2) {
                    thermostat.setSetTemp(coolSetting2[1]);
                } if (i==3) {
                    thermostat.setSetTemp(coolSetting3[1]);
                }

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
