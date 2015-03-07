/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.actions;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * Abstract action
 * 
 * @author Jouwee
 */
public abstract class AbstractProjectAction extends AbstractAction {

    /**
     * New action
     * 
     * @param name 
     */
    public AbstractProjectAction(String name) {
        this(name, null);
    }
    
    /**
     * New action
     * 
     * @param icon 
     */
    public AbstractProjectAction(Icon icon) {
        super(null, icon);
    }
    
    /**
     * New action
     * 
     * @param name
     * @param icon 
     */
    public AbstractProjectAction(String name, Icon icon) {
        super(name, icon);
    }
    
    
    
}
