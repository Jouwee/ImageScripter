/*
 * TCProto
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.jouwee.proto.processors;

import com.jouwee.proto.Filter;
import com.jouwee.proto.Image;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Binarization filter
 *
 * @author Jouwee
 */
@ActionMeta(name = "Binarization")
public class Binarization extends Filter {

    @Override
    public int processPixel(int x, int y) {
        return getOriginalImage().getPixelValue(x, y, Image.CHANNEL_GRAY) > 128 ? 0 : 255;
    }

    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
        getNewImage().setChannels(Image.CHANNELS_GRAYSCALE);
    }

}
