/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Pill default renderer
 * 
 * @author Jouwee
 */
public class PillDefaultRenderer implements PillRenderer<Object> {

    @Override
    public JComponent getComponent(Object value) {
        return new JLabel(value.toString());
    }
    
}
