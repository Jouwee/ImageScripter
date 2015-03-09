package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.IteratorGatherer;
import com.jouwee.proto.annotations.ActionMeta;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Find the bounds of the actual content of the image, based on a background color
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Find image content bounds", functionality = Functionality.IMAGE_INFO)
public class ContentBounds extends IteratorGatherer {
    
    /** Background color */
    private transient Color backgroundColor;
    /** Bounds of the image content */
    private transient Rectangle bounds;

    @Override
    public void beforeProcessing() {
        backgroundColor = Color.BLACK;
        bounds = new Rectangle(image.getWidth(), image.getHeight(), 0, 0);
    }
    
    @Override
    public void iteratePixel(int x, int y) {
        if (!image.getPixelColor(x, y).equals(backgroundColor)) {
            if (x < bounds.x) {
                bounds.x = x;
            }
            if (y < bounds.y) {
                bounds.y = y;
            }
            if (x > bounds.width) {
                bounds.width = x;
            }
            if (y > bounds.height) {
                bounds.height = y;
            }
        }
    }

    @Override
    public Object getValue() {
        return bounds;
    }
    
}
