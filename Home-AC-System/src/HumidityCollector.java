import java.util.Collections;
import java.util.List;

/**
 * HumidityCollector - Middleman between humidity sensor and backend, provides average humidity
 */

public class HumidityCollector {
    // state variables
    private int sum;
    private int round;

    //output
    private int avHumidity;

    /**
     * Class constructor
     */
    public HumidityCollector(){
        this.sum = 0;
        this.round = 0;
    }

    /**
     * Function to get the current room humidity and calculate the average humidity over 30 rounds or 30 secs
     * @return   The avHumidity at every 30th round and -1 otherwise
     */
    public int sendHumidityValue(int temp) {
        this.round += 1;
        this.sum += temp;

        if (this.round == 30) {
            this.avHumidity = this.sum/this.round;
            this.round = 0;

            return this.avHumidity;
        }

        return -1;
    }
}
