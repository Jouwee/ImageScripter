package com.jouwee.proto;

import java.awt.image.BufferedImage;

/**
 * Representation of an image
 * 
 * @author Jouwee
 */
public class Image {

    /** Width */
    private int width;
    /** Height */
    private int height;
    /** Number of channels */
    private int channels;
    /** Image data */
    private BufferedImage data;

    /**
     * Creates a new image
     */
    public Image() {
    }

    /**
     * Creates a new image
     * 
     * @param data
     */
    public Image(BufferedImage data) {
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.data = data;
    }

    /**
     * Creates a new empty image
     * 
     * @param width
     * @param height 
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    /**
     * Returns the width of the image
     * 
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the image
     * 
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the image
     * 
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the image
     * 
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the number of channels of the image
     * 
     * @return int
     */
    public int getChannels() {
        return channels;
    }

    /**
     * Set the number of channels of the image
     * 
     * @param channels 
     */
    public void setChannels(int channels) {
        this.channels = channels;
    }

    /**
     * Returns the value of a pixel
     * 
     * @param x
     * @param y
     * @return 
     */
    public int getPixelRGB(int x, int y) {
        return data.getRGB(x, y);
    }
    
    /**
     * Sets the value of a pixel
     * 
     * @param x
     * @param y
     * @param rgb 
     */
    public void setPixelRGB(int x, int y, int rgb) {
        data.setRGB(x, y, rgb);
    }
    
    /**
     * Returns a buffered image
     * 
     * @return BufferedImage
     */
    public BufferedImage getBufferedImage() {
        return data;
    }
    
}
