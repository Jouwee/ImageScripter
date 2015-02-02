/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import javax.swing.JComponent;

/**
 * Pill renderer
 * 
 * @author Jouwee
 */
public interface PillRenderer<T> {
    
    /**
     * Returns the renderer component
     * 
     * @param value
     * @return JComponent
     */
    public abstract JComponent getComponent(T value);
    
}
