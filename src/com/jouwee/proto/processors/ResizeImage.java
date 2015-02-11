/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.processors;

import com.jouwee.proto.Functionality;
import com.jouwee.proto.Image;
import com.jouwee.proto.Transformer;
import com.jouwee.proto.annotations.ActionMeta;

/**
 * Resize image
 * 
 * @author Jouwee
 */
@ActionMeta(name = "Resize image", functionality = Functionality.TRANSFORMATION)
public class ResizeImage extends Transformer {

    private float horizontalScale = 0.5f;
    private float verticalScale = 0.5f;
    
    @Override
    public void iteratePixel(int x, int y) {
        getNewImage().setPixelValue((int)Math.floor(x * horizontalScale), (int)Math.floor(y * verticalScale), getOriginalImage().getPixelValue(x, y));
    }

    @Override
    public Image buildNewImage() {
        return new Image((int)Math.floor(getOriginalImage().getWidth() * horizontalScale), (int)Math.floor(getOriginalImage().getHeight() * verticalScale));
    }
    
}
