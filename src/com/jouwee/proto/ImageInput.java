package com.jouwee.proto;

import java.util.Arrays;
import java.util.List;

/**
 * Input for images
 * 
 * @author Jouwee
 */
public class ImageInput implements Input {

    /** Images */
    private final List<Image> images;

    /**
     * Create a new input
     * 
     * @param image 
     */
    public ImageInput(Image image) {
        this(new Image[] {image});
    }

    /**
     * Create a new input
     * 
     * @param images 
     */
    public ImageInput(Image[] images) {
        this.images = Arrays.asList(images);
    }
    
    @Override
    public int getFrameCount() {
        return images.size();
    }

    @Override
    public Image getFrame(int frame) {
        return images.get(frame);
    }
    
}
