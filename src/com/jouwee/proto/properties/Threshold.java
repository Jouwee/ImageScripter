package com.jouwee.proto.properties;

/**
 * Threshold
 * 
 * @author Jouwee
 */
public interface Threshold {
    
    /**
     * Apply the threshold over the value
     * 
     * @param value
     * @return int
     */
    public abstract int apply(int value);
    
}
