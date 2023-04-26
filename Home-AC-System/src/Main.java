/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){
        int mode = 1;
        int temp = 24;

        temp = mode == 1 ? 30 : mode == 2 ? 10 : 24;

        //create instance of main component TempController
        TempController tempController = new TempController(temp);

        //create instances of all controller class
        FanController fanController = new FanController();
        TempInputController tempInputController = new TempInputController(tempController);
        ModeController modeController = new ModeController(tempController);

        //create instances for data collectors
        HumidityCollector humidityCollector = new HumidityCollector();
        TempCollector tempCollector = new TempCollector(tempController);

        Thermostat thermostat = new Thermostat(temp, modeController, tempInputController, fanController);

        EnviroSim simulator = new EnviroSim(temp, temp, tempCollector, tempController, modeController, tempInputController, humidityCollector, thermostat, fanController);

        // Start the simulation
        simulator.environmentSim(5);
    }
}