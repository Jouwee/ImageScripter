/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.Transformer;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.Property;
import java.awt.Dimension;

/**
 * Resize image
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Resize image", functionality = Functionality.TRANSFORMATION)
public class ResizeImage extends Transformer {

    /** New size */
    @Property(name = "New size")
    private Dimension newSize;
    /** Horizontal scale */
    private float horizontalScale;
    /** Vertical scale */
    private float verticalScale;
    
    public ResizeImage() {
        super();
        newSize = new Dimension(100, 100);
    }

    @Override
    public Image process(Image image) {
        horizontalScale = (float)newSize.width / (float)image.getWidth();
        verticalScale = (float)newSize.height / (float)image.getHeight();
        return super.process(image);
    }
    
    @Override
    public void iteratePixel(int x, int y) {
        getNewImage().setPixelValue((int)Math.floor(x * horizontalScale), (int)Math.floor(y * verticalScale), getOriginalImage().getPixelValue(x, y));
    }

    @Override
    public Image buildNewImage() {
        return new Image(newSize.width, newSize.height);
    }

    /**
     * Returns the new image size
     * 
     * @return Rectangle
     */
    public Dimension getNewSize() {
        return newSize;
    }

    /**
     * Sets the new size
     * 
     * @param newSize 
     */
    public void setNewSize(Dimension newSize) {
        Dimension oldValue = this.newSize;
        this.newSize = newSize;
        getPropertyChangeSupport().firePropertyChange("newSize", oldValue, newSize);
    }
    
}
