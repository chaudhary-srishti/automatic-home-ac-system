/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){

        //create instance of main component TempController
        TempController tempController = new TempController();

        //create instances of all controller class
        FanController fanController = new FanController();
        TempInputController tempInputController = new TempInputController(tempController);
        ModeController modeController = new ModeController(tempController);

        //create instances for data collectors
        HumidityCollector humidityCollector = new HumidityCollector();
        TempCollector tempCollector = new TempCollector(tempController);

        Thermostat thermostat = new Thermostat(modeController, tempInputController);

        EnviroSim simulator = new EnviroSim(25, 20, 25, tempCollector, tempController, modeController, tempInputController, humidityCollector, thermostat);


        // Start the simulation
        simulator.environmentSim(5);

    }
}