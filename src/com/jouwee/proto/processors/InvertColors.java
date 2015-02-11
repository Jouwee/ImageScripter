/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.processors;

import com.jouwee.proto.Filter;
import com.jouwee.proto.Functionality;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Invert colors
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Invert colors", functionality = Functionality.COLOR_TRANSFORMATION)
public class InvertColors extends Filter {

    @Override
    public int processPixel(int x, int y, int channel) {
        return getOriginalImage().getPixelValue(x, y, channel) * -1 + 255;
    }

}
