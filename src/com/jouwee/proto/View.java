package com.jouwee.proto;

import javax.swing.JComponent;

/**
 * Abstract representation of a view
 * 
 * @author Jouwee
 * @param <T> Class represented by this view
 */
public abstract class View<T> extends JComponent {
    
    /** Model of this view */
    private T model;

    /**
     * Return the model for this view
     * 
     * @return T
     */
    public T getModel() {
        return model;
    }

    /**
     * Sets the model for this view
     * 
     * @param model 
     */
    public void setModel(T model) {
        this.model = model;
    }

}
