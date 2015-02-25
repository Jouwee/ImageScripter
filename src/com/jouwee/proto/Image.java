package com.jouwee.proto;

import java.awt.image.BufferedImage;

/**
 * Representation of an image
 * 
 * @author Jouwee
 */
public class Image {

    /** Number of channels for grayscale images */
    public static final int CHANNELS_GRAYSCALE = 1;
    /** Number of channels for alpha grayscales */
    public static final int CHANNELS_ALPHA_GRAYSCALE = 2;
    /** Number of channels for RGB images */
    public static final int CHANNELS_RGB = 3;
    /** Number of channels for RGBA images */
    public static final int CHANNELS_RGBA = 4;
    /** Channel - Red */
    public static final int CHANNEL_RED = 0;
    /** Channel - Green */
    public static final int CHANNEL_GREEN = 1;
    /** Channel - Blue */
    public static final int CHANNEL_BLUE = 2;
    /** Channel - Alpha */
    public static final int CHANNEL_ALPHA = 3;
    /** Channel - Gray (For grayscales) */
    public static final int CHANNEL_GRAY = 0;
    /** Width */
    private int width;
    /** Height */
    private int height;
    /** Number of channels */
    private int channels;
    /** Image data */
    private int[][][] data;
    /** Cached image */
    private BufferedImage cachedImage;

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
        this.channels = CHANNELS_RGBA;
        this.data = new int[channels][height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixelValue(x, y, data.getRGB(x, y));
            }
        }
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
        this.channels = CHANNELS_RGBA;
        this.data = new int[channels][height][width];
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
     * @return int
     */
    public int getPixelValue(int x, int y) {
        if (channels == CHANNELS_GRAYSCALE) {
            return data[CHANNEL_GRAY][y][x];
        } else {
            int v = (data[CHANNEL_RED][y][x] << 16) | (data[CHANNEL_GREEN][y][x] << 8) | (data[CHANNEL_BLUE][y][x]);
            return v;
        }
    }
    
    /**
     * Sets the value of a pixel
     * 
     * @param x
     * @param y
     * @param value 
     */
    public void setPixelValue(int x, int y, int value) {
        if (channels == CHANNELS_GRAYSCALE) {
            data[CHANNEL_GRAY][y][x] = value;
        } else {
            data[CHANNEL_RED][y][x] = (value >> 16 & 0xFF);
            data[CHANNEL_GREEN][y][x] = (value >> 8 & 0xFF);
            data[CHANNEL_BLUE][y][x] = (value & 0xFF);
        }
    }

    /**
     * Returns the value of a pixel
     * 
     * @param x
     * @param y
     * @param channel
     * @return int
     */
    public int getPixelValue(int x, int y, int channel) {
        return data[channel][y][x];
    }
    
    /**
     * Sets the value of a pixel
     * 
     * @param x
     * @param y
     * @param channel
     * @param v 
     */
    public void setPixelValue(int x, int y, int channel, int v) {
        data[channel][y][x] = v;
    }
    

    /**
     * Returns the red value of a pixel
     * 
     * @param x
     * @param y
     * @return int
     */
    public int getPixelRed(int x, int y) {
        return getPixelValue(x, y, CHANNEL_RED);
    }
    
    /**
     * Sets the red value of a pixel
     * 
     * @param x
     * @param y
     * @param r 
     */
    public void setPixelRed(int x, int y, int r) {
        setPixelValue(x, y, CHANNEL_RED, r);
    }
    
    /**
     * Returns the green value of a pixel
     * 
     * @param x
     * @param y
     * @return int
     */
    public int getPixelGreen(int x, int y) {
        return getPixelValue(x, y, CHANNEL_GREEN);
    }
    
    /**
     * Sets the green value of a pixel
     * 
     * @param x
     * @param y
     * @param g 
     */
    public void setPixelGreen(int x, int y, int g) {
        setPixelValue(x, y, CHANNEL_GREEN, g);
    }
    
    /**
     * Returns the blue value of a pixel
     * 
     * @param x
     * @param y
     * @return 
     */
    public int getPixelBlue(int x, int y) {
        return getPixelValue(x, y, CHANNEL_BLUE);
    }
    
    /**
     * Sets the blue value of a pixel
     * 
     * @param x
     * @param y
     * @param b 
     */
    public void setPixelBlue(int x, int y, int b) {
        setPixelValue(x, y, CHANNEL_BLUE, b);
    }
    
    /**
     * Returns a buffered image
     * 
     * @return BufferedImage
     */
    public BufferedImage getBufferedImage() {
        if (cachedImage == null) {
            cachedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    if (getChannels() == CHANNELS_GRAYSCALE) {
                        int v = getPixelValue(x, y);
                        cachedImage.setRGB(x, y, ((v << 16) | (v << 8) | v) | 0xFF000000);
                    } else {
                        cachedImage.setRGB(x, y, getPixelValue(x, y) | 0xFF000000);
                    }
                }
            }
        }
        return cachedImage;
    }
    
}
