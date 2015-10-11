package com.kalah.model;

import java.util.HashMap;
import java.util.Map;

public class KalahMap<E extends Long, F extends Integer> extends HashMap<Long, Integer> {

    private static final long serialVersionUID = -7401150451791395614L;

    private final static int DEFAULT_NUMBER_OF_STONES = 6;

    private final static int DEFAULT_ZERO = 0;

    public KalahMap() {
        put(0L, DEFAULT_NUMBER_OF_STONES);
        put(1L, DEFAULT_NUMBER_OF_STONES);
        put(2L, DEFAULT_NUMBER_OF_STONES);
        put(3L, DEFAULT_NUMBER_OF_STONES);
        put(4L, DEFAULT_NUMBER_OF_STONES);
        put(5L, DEFAULT_NUMBER_OF_STONES);
        put(6L, DEFAULT_ZERO); // Player 1
        put(7L, DEFAULT_NUMBER_OF_STONES);
        put(8L, DEFAULT_NUMBER_OF_STONES);
        put(9L, DEFAULT_NUMBER_OF_STONES);
        put(10L, DEFAULT_NUMBER_OF_STONES);
        put(11L, DEFAULT_NUMBER_OF_STONES);
        put(12L, DEFAULT_NUMBER_OF_STONES);
        put(13L, DEFAULT_ZERO); // Player 2
    }

    public Integer getTotalStonesInPits(){
        Integer totalStones = 0;
        for (Map.Entry<Long, Integer> entry : this.entrySet()) {
            if (entry.getKey() != 6) {
                totalStones+= entry.getValue();
            }
        }
        return totalStones;
    }

    /**
     * Get the total number of stones in Player 1's pits
     * @return
     */
    public Integer getPlayer1TotalStones(){
        Integer total = 0;
        for (Map.Entry<Long, Integer> entry : this.entrySet()) {
            if (entry.getKey() < 6) {
                total += entry.getValue();
            }
        }
        return total;
    }

    /**
     * Get the total number of stones in Player 2's pits
     * @return
     */
    public Integer getPlayer2TotalStones(){
        Integer total = 0;
        for (Map.Entry<Long, Integer> entry : this.entrySet()) {
            if (entry.getKey() > 6 && entry.getKey() < 13) {
                total += entry.getValue();
            }
        }
        return total;
    }
}
