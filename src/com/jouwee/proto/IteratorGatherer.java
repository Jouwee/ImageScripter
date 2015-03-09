/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

/**
 * Pixel iterator for gatherers
 * 
 * @author Jouwee
 */
public abstract class IteratorGatherer extends Gatherer {
    
    /** Original image */
    protected transient Image image;
    
    @Override
    public void process(Image image) {
        compileCallbacks();
        this.image = image;
        beforeProcessing();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                iteratePixel(x, y);
            }
        }
        afterProcessing();
    }
    
    /**
     * Iterates the pixel
     * 
     * @param x
     * @param y 
     */
    public abstract void iteratePixel(int x, int y);
    
    /**
     * Returns the image
     * 
     * @return Image
     */
    protected Image getImage() {
        return image;
    }

    /**
     * Callback for before processing
     */
    public void beforeProcessing() {
    }
    
    /**
     * Callback for after processing
     */
    public void afterProcessing() {
    }

}
