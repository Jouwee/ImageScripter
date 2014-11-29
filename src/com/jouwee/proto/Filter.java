package com.jouwee.proto;

/**
 * Abstract class for filters
 * 
 * @author Jouwee
 */
public abstract class Filter extends Iterator {

    @Override
    public void iteratePixel(int x, int y) {
        getNewImage().setPixelValue(x, y, processPixel(x, y));
    }
    
    /**
     * Process a pixel, returning the new value for it
     * 
     * @param x
     * @param y
     * @return int
     */
    public abstract int processPixel(int x, int y);
    
}
