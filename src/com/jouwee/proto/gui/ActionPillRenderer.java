/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Action pill
 * 
 * @author Jouwee
 */
public class ActionPillRenderer implements PillRenderer<Class>{

    @Override
    public JComponent getComponent(Class value) {
        JLabel l = new JLabel(value.getSimpleName());
        return l;
    }
    
    
    
}
