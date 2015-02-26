/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * Component factory
 * 
 * @author Jouwee
 */
public class ComponentFactory {
    
    /** Scroll's unit increment */
    private static final int SCROLL_UNIT_INCREMENT = 16;
    
    /**
     * Creates a scroll pane
     * 
     * @return JScrollPane
     */
    public static JScrollPane scrollPane() {
        return scrollPane(null);
    }
    
    /**
     * Creates a scroll pane
     * 
     * @param viewPort
     * @return JScrollPane
     */
    public static JScrollPane scrollPane(JComponent viewPort) {
        JScrollPane pane = new JScrollPane(viewPort);
        pane.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        pane.getVerticalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        return pane;
    }
    
}
