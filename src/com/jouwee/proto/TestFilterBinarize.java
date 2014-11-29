/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

import com.jouwee.proto.annotations.Property;
import java.awt.Color;

/**
 * TODO: This is just a test filter, must be redone
 * @author Nicolas
 */
public class TestFilterBinarize extends Iterator {
    
    /** Threshold */
    @Property(name = "Threshold")
    private int threshold;

    public TestFilterBinarize() {
        threshold = 128;
    }

    
    
    @Override
    public Image process(Image image) {
        return super.process(image);
    }

    
    
    @Override
    public void iteratePixel(int x, int y) {
        Color c = new Color(getOriginalImage().getPixelValue(x, y));
        int m = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
        if(m < threshold) {
            getNewImage().setPixelValue(x, y, 0);
        } else {
            getNewImage().setPixelValue(x, y, 0xFFFFFF);
        }
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
}
