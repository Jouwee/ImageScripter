package com.jouwee.proto.properties;

/**
 * Binary threshold
 * 
 * @author Jouwee
 */
public class BinaryThreshold implements Threshold {

    /** Threshold value */
    private final int threshold;

    /**
     * Create a new binary threshold 
     * 
     * @param threshold 
     */
    public BinaryThreshold(int threshold) {
        this.threshold = threshold;
    }
    
    @Override
    public int apply(int value) {
       return value >= threshold ? 0xFF : 0x00;
    }

    /**
     * Returns the threshold value
     * 
     * @return int
     */
    public int getThreshold() {
        return threshold;
    }
    
}
