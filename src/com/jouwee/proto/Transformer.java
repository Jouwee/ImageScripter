/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

/**
 * Image transformer
 * 
 * @author Jouwee
 */
public abstract class Transformer extends Processor {
    
    /** Original image */
    private transient Image originalImage;
    /** New image */
    private transient Image newImage;
    
    @Override
    public Image process(Image image) {
        compileCallbacks();
        originalImage = image;
        newImage = buildNewImage();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                iteratePixel(x, y);
            }
        }
        return newImage;
    }
    
    /**
     * Build the new image
     * 
     * @return Image
     */
    public abstract Image buildNewImage();
    
    /**
     * Iterate a pixel of the image
     * 
     * @param x 
     * @param y 
     */
    public abstract void iteratePixel(int x, int y);
    
    /**
     * Get the original image
     * 
     * @return Image
     */
    public Image getOriginalImage() {
        return originalImage;
    }

    /**
     * Get the new image
     * 
     * @return Image
     */
    public Image getNewImage() {
        return newImage;
    }
    
}
