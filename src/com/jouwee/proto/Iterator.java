package com.jouwee.proto;

/**
 * First concrete level of image processor, going towards image filters and transformers
 * 
 * @author Jouwee
 */
public abstract class Iterator extends Processor {

    /** Original image */
    private transient Image originalImage;
    /** New image */
    private transient Image newImage;
    
    public Iterator() {
        super();
    }
    
    @Override
    public Image process(Image image) {
        compileCallbacks();
        originalImage = image;
        newImage = new Image(image.getWidth(), image.getHeight());
        beforeProcessing();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                iteratePixel(x, y);
            }
        }
        afterProcessing();
        return newImage;
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