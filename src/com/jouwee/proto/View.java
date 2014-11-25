package com.jouwee.proto;

import javax.swing.JComponent;

/**
 * Abstract representation of a view
 * 
 * @author Jouwee
 */
public abstract class View extends JComponent {
    
    /** Model of this view */
    private final Model model;

    /**
     * Creates a new view
     * 
     * @param model 
     */
    public View(Model model) {
        this.model = model;
    }
    
    /**
     * Return the model for this view
     * 
     * @return Model
     */
    public Model getModel() {
        return model;
    }

}
