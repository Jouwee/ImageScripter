package com.jouwee.proto;

/**
 * Input for the program
 * 
 * @author Jouwee
 */
public interface Input {
    
    /**
     * Return the frame count
     * 
     * @return number of frames
     */
    public abstract int getFrameCount();
    
    /**
     * Return a frame
     * 
     * @param frame frame index
     * @return frame image
     */
    public abstract Image getFrame(int frame);
    
}
