/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){

        //create instance of main component TempController
        TempController tempController = new TempController();

        //create instances of all controller class
        FanController fanController = new FanController();
        TempInputController tempInputController = new TempInputController();
        ModeController modeController = new ModeController();

        //create instances for data collectors
        HumidityCollector humidityCollector = new HumidityCollector();
        TempCollector tempCollector = new TempCollector(tempController);

        EnviroSim simulator = new EnviroSim(25, 20, 25, tempCollector, tempController);

        //define threads


        //start while loop for 5 min

        simulator.environmentSim(5);

    }
}