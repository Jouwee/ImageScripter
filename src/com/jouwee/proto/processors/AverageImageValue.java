package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.IteratorGatherer;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Average image value
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Average image value", functionality = Functionality.IMAGE_INFO)
public class AverageImageValue extends IteratorGatherer {

    /** Sum of the values */
    private long sum;
    /** Average value */
    private double average;

    @Override
    public void beforeProcessing() {
        sum = 0;
    }

    @Override
    public void afterProcessing() {
        average = (double)sum / (double)(image.getWidth() * image.getHeight());
    }

    @Override
    public void iteratePixel(int x, int y) {
        sum += image.getPixelValue(x, y, Image.CHANNEL_GRAY);
    }

    @Override
    public Object getValue() {
        return average;
    }
    
    
}
