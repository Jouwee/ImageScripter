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
    
}
