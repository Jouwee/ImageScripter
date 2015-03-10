
package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.Transformer;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.Property;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Resize canvas
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Resize Canvas", functionality = Functionality.TRANSFORMATION)
public class ResizeCanvas extends Transformer {

    /** New Bounds */
    @Property(name = "New Bounds")
    private Dimension newSize;
    /** Center point */
    private Point pivot;

    /**
     * New resize canvas transformer
     */
    public ResizeCanvas() {
        super();
        newSize = new Dimension(100, 100);
    }

    @Override
    public Image process(Image image) {
        pivot = new Point(image.getWidth() / 2, image.getHeight()/ 2);
        return super.process(image);
    }
    
    @Override
    public void iteratePixel(int x, int y) {
        
        int newX = (pivot.x - newSize.width / 2) - pivot.x + x;
        int newY = (pivot.y - newSize.height / 2) - pivot.y + y;
        
        if (newX < 0 || newX > newSize.width || newY < 0 || newY > newSize.height) {
            return;
        }
        
        getNewImage().setPixelValue(newX, newY, getOriginalImage().getPixelValue(x, y));
    }

    @Override
    public Image buildNewImage() {
        return new Image(newSize.width, newSize.height, getOriginalImage().getChannels());
    }

    /**
     * Returns the new bounds
     * 
     * @return Rectangle
     */
    public Dimension getNewSize() {
        return newSize;
    }

    /**
     * Sets the new bounds
     * 
     * @param newSize 
     */
    public void setNewSize(Dimension newSize) {
        Dimension oldValue = this.newSize;
        this.newSize = newSize;
        getPropertyChangeSupport().firePropertyChange("newBounds", oldValue, newSize);
    }
    
}
