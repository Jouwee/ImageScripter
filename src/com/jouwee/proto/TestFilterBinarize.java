/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

import java.awt.Color;

/**
 *
 * @author Nicolas
 */
public class TestFilterBinarize extends Iterator {
    
    @Override
    public void iteratePixel(int x, int y) {
        Color c = new Color(getOriginalImage().getPixelRGB(x, y));
        int m = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
        if(m < 128) {
            getNewImage().setPixelRGB(x, y, 0);
        } else {
            getNewImage().setPixelRGB(x, y, 0xFFFFFF);
        }
    }
    
}
