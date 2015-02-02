/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pill group
 * 
 * @author Jouwee
 * @param <T>
 */
public class PillGroup<T> implements ActionListener {

    /** Selected pill */
    private Pill selected;
    
    /**
     * New pill group
     */
    public PillGroup() {
        selected = null;
    }
    
    /**
     * Adds a new pill
     * 
     * @param pill 
     */
    public void add(Pill<T> pill) {
        pill.addActionListener(this);
    }

    /**
     * Returns the selected pill
     * 
     * @return Pill
     */
    public Pill<T> getSelected() {
        return selected;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (selected != null) {
            selected.setSelected(false);
        }
        selected = (Pill) e.getSource();
    }
    
}
