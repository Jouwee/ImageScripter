/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

import java.awt.Color;

/**
 * TODO: This is just a test filter, must be redone
 * @author Nicolas
 */
public class TestFilterBinarize extends Iterator {
    
    /** Threshold */
    @Property(name = "Threshold")
    private int threshold;

    @Override
    public Image process(Image image) {
        System.out.println("threshold: " + threshold);
        return super.process(image); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public void iteratePixel(int x, int y) {
        Color c = new Color(getOriginalImage().getPixelRGB(x, y));
        int m = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
        if(m < threshold) {
            getNewImage().setPixelRGB(x, y, 0);
        } else {
            getNewImage().setPixelRGB(x, y, 0xFFFFFF);
        }
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
}
