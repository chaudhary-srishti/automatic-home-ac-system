/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){

        // Change the testCaseNum to test different case:
        // Test Case 1: COOL Mode
        // Test Case 2: COOL, series of temperature, Fan on/off
        // Test Case 3: HEAT, series of temperature, Fan on/off
        // Test Case 4: AUTO, series of temperature, Fan on/off

        int testCaseNum = 3;
        String testFile = "Home-AC-System/src/dataFiles/testCase" + testCaseNum + ".txt";
        int testMode = (testCaseNum ==1 || testCaseNum == 2) ? 1 : testCaseNum == 3 ? 2 : 3;
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
        Thread thermostatThread = new ThermostatThread(testFile, tempController, thermostat);
        thermostatThread.start();
        simulator.environmentSim(5);
    }
}