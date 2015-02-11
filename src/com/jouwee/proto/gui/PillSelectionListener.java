/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto.gui;

import java.util.EventListener;

/**
 * Pill selection listener
 * 
 * @author Jouwee
 */
public interface PillSelectionListener extends EventListener {

    /**
     * Called whenever the value of the selection changes
     *
     * @param e the event that characterizes the change
     */
    void valueChanged(PillSelectionEvent e);

}
