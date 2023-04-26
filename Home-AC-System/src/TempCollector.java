import java.util.*;

/**
 * TempController - Middleman between temperature sensor and backend, provides average temperature
 */

public class TempCollector {
    // state variables
    private double sum;
    private int round;
    private List<Integer> temperatures;

    //output
    private int avTemp;

    /**
     * Class constructor
     */
    public TempCollector(TempController tempController){
        this.sum = 0;
        this.round = 0;
        temperatures = Collections.emptyList();
    }

    /**
     * Function to get the current room temperature and calculate the average temperature over 30 rounds or 30 secs
     * @return   The avTemp at every 30th round and -1 otherwise
     */
    public int sendTempValue(double temp) {
        round += 1;
        sum += temp;

        if (round == 5) {
            avTemp = (int) (sum/5);
            round = 0;
            sum = 0;
            return avTemp;
        } else {
            return -1;
        }
    }
}
