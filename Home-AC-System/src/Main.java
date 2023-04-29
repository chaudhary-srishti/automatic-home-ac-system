/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){

        // Change the mode to the mode you want to test it for, this will allow the simulator to generate lower temperature for heat mode
        // and higher temperature for cool mode ( COOL = 1, HEAT = 2, AUTO = 3)
        int testMode = 1;
        int initialTemp = testMode == 1 ? 30 : testMode == 2 ? 10 : 24;

        //create instance of main component TempController
        TempController tempController = new TempController(initialTemp);

        //create instances of all controller class
        FanController fanController = new FanController();
        TempInputController tempInputController = new TempInputController(tempController);
        ModeController modeController = new ModeController(tempController);

        //create instances for data collectors
        HumidityCollector humidityCollector = new HumidityCollector();
        TempCollector tempCollector = new TempCollector(tempController);

        Thermostat thermostat = new Thermostat(initialTemp, modeController, tempInputController, fanController);

        EnviroSim simulator = new EnviroSim(initialTemp, initialTemp, tempCollector, tempController, modeController, tempInputController, humidityCollector, thermostat, fanController);

        // Start the simulation
        simulator.environmentSim(5);
    }
}