package com.jouwee.proto.processors;

import com.jouwee.proto.Gatherer;
import com.jouwee.proto.Image;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Average image value
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Average image value", category = "math")
public class AverageImageValue extends Gatherer {

    /** Sum of the values */
    private long sum;
    private double average;

    @Override
    public void process(Image image) {
        sum = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                iteratePixel(image, x, y);
            }
        }
        average = (double)sum / (double)(image.getWidth() * image.getHeight());
    }
    
    public void iteratePixel(Image image, int x, int y) {
        sum += image.getPixelValue(x, y, Image.CHANNEL_GRAY);
    }

    @Override
    public Object getValue() {
        return average;
    }
    
    
}
