/**
 * Main class - Initialize simulation
 */

public class Main {
    public static void main(String[] args){

        //create instance of main component TempController
        TempController temp_controller = new TempController();

        //create instances of all controller class
        FanController fan_controller = new FanController(temp_controller);
        TempInputController temp_input_controller = new TempInputController();
        ModeController mode_controller = new ModeController();

        //create instances for data collectors
        HumidityCollector humidity_collector = new HumidityCollector();
        TempController tempController = new TempController();



    }
}