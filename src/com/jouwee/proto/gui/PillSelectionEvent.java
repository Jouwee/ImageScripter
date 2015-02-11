/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import java.util.EventObject;

/**
 * Pill selection event
 *
 * @author Jouwee
 */
public class PillSelectionEvent extends EventObject {
    
    /** Index altered */
    private int index;

    /**
     * New event
     * 
     * @param index
     * @param source 
     */
    public PillSelectionEvent(int index, Object source) {
        super(source);
        this.index = index;
    }

    /**
     * Returns the index
     * 
     * @return int
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index
     * 
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
}
