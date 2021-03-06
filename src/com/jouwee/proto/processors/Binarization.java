/*
 * TCProto
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.jouwee.proto.processors;

import com.jouwee.proto.Filter;
import com.jouwee.proto.Image;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.Property;
import com.jouwee.proto.properties.BinaryThreshold;

/**
 * Binarization filter
 *
 * @author Jouwee
 */
@ActionMeta(name = "Binarization")
public class Binarization extends Filter {

    /** Threshold */
    @Property(name = "Threshold")
    private BinaryThreshold threshold;

    /**
     * Creates a new binarization filter
     */
    public Binarization() {
        threshold = new BinaryThreshold(128);
    }
    
    @Override
    public int processPixel(int x, int y, int channel) {
        return threshold.apply(getOriginalImage().getPixelValue(x, y, channel));
    }

    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
        getNewImage().setChannels(Image.CHANNELS_GRAYSCALE);
    }

    /**
     * Returns the threshold
     * 
     * @return Threshold
     */
    public BinaryThreshold getThreshold() {
        return threshold;
    }

    /**
     * Sets the threshold
     * 
     * @param threshold 
     */
    public void setThreshold(BinaryThreshold threshold) {
        BinaryThreshold oldValue = this.threshold;
        this.threshold = threshold;
        getPropertyChangeSupport().firePropertyChange("threshold", oldValue, threshold);
    }

}
