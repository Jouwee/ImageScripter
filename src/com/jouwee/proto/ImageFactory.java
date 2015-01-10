package com.jouwee.proto;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image factory
 * 
 * @author Jouwee
 */
public final class ImageFactory {
    
    /**
     * Creates a new image from an external file
     * 
     * @param file
     * @return Image
     * @throws IOException 
     */
    public static Image fromFile(File file) throws IOException {
        return new Image(ImageIO.read(file));
    }
    
    /**
     * Creates a new image from an external file
     * 
     * @param file
     * @return Image
     * @throws IOException 
     */
    public static Image[] fromFile(File[] file) throws IOException {
        Image[] ret = new Image[file.length];
        for (int i = 0; i < file.length; i++) {
            ret[i] = fromFile(file[i]);
        }
        return ret;
    }
    
}
