import java.util.*;

/**
 * TempController - Middleman between temperature sensor and backend, provides average temperature
 */

public class TempCollector {

    // state variables
    private int sum;
    private int round;
    private List<Integer> temperatures;

    //output
    private int avTemp;

    /**
     * Class constructor
     */
    public TempCollector(){
        this.sum = 0;
        this.round = 0;
        temperatures = Collections.emptyList();
    }

    /**
     * Function to get the current room temperature and calculate the average temperature over 30 rounds or 30 secs
     * @return   The avTemp at every 30th round and -1 otherwise
     */
    public int sendTempValue(int temp) {
        this.round += 1;
        this.sum += temp;

        if (this.round == 30) {
            this.avTemp = this.sum/this.round;
            this.round = 0;

            return this.avTemp;
        }

        return -1;
    }

}
