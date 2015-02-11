package com.jouwee.proto.processors;

import com.jouwee.proto.Filter;
import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Grayscale filter using the average method
 *
 * @author Jouwee
 */
@ActionMeta(name = "Average grayscale", functionality = Functionality.GRAYSCALE)
public class AverageGrayscale extends Filter {

    @Override
    public int processPixel(int x, int y, int channel) {
        return (getOriginalImage().getPixelValue(x, y, Image.CHANNEL_RED) +
                getOriginalImage().getPixelValue(x, y, Image.CHANNEL_GREEN) +
                getOriginalImage().getPixelValue(x, y, Image.CHANNEL_BLUE)) / 3;
    }

    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
        getNewImage().setChannels(Image.CHANNELS_GRAYSCALE);
    }

}
