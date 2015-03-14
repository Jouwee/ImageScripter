package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.Transformer;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.Property;
import java.awt.Rectangle;

/**
 * Resize canvas
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Crop image", functionality = Functionality.TRANSFORMATION)
public class CropImage extends Transformer {

    /** New Bounds */
    @Property(name = "New Bounds")
    private Rectangle newBounds;

    
    public CropImage() {
        super();
        newBounds = new Rectangle(0, 0, 100, 100);
    }

    @Override
    public void iteratePixel(int x, int y) {
        // Ignore pixel outside of bounds
        if (x < newBounds.x || y < newBounds.y || x >= newBounds.width || y >= newBounds.width ) {
            return;
        }
        getNewImage().setPixelValue(x - newBounds.x, y - newBounds.y, getOriginalImage().getPixelValue(x, y));
    }

    @Override
    public Image buildNewImage() {
        return new Image(newBounds.width - newBounds.x, newBounds.height - newBounds.y, getOriginalImage().getChannels());
    }

    /**
     * Returns the new bounds
     * 
     * @return Rectangle
     */
    public Rectangle getNewBounds() {
        return newBounds;
    }

    /**
     * Sets the new bounds
     * 
     * @param newBounds 
     */
    public void setNewBounds(Rectangle newBounds) {
        Rectangle oldValue = this.newBounds;
        this.newBounds = newBounds;
        getPropertyChangeSupport().firePropertyChange("newBounds", oldValue, newBounds);
    }
    
}
