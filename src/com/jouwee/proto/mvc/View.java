package com.jouwee.proto.mvc;

import com.jouwee.proto.Application;
import com.jouwee.proto.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;

/**
 * Abstract representation of a view
 *
 * @author Jouwee
 * @param <T> Model type
 * @param <C> Controller type
 */
public abstract class View<T, C> extends JComponent implements PropertyChangeListener {

    /** Model of this view */
    private T model;
    /** Controller */
    private C controller;

    /**
     * Creates a new view
     *
     * @param model
     */
    public View(Model model) {
        // TODO: I don't think this should be called here, but I have to change the views
        updateModel(model);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Application.PROP_MODEL)) {
            updateModel(Application.getModel());
        }
    }
    
    /**
     * Update the model
     *
     * @param model
     */
    public abstract void updateModel(Model model);
    
    /**
     * Return the model for this view
     *
     * @return Model
     */
    public T getModel() {
        return model;
    }

    /**
     * Updates the model of this view
     * 
     * @param model 
     */
    public void setModel(T model) {
        this.model = model;
    }

    /**
     * Returns the controller
     * 
     * @return C
     */
    public C getController() {
        return controller;
    }

    /**
     * Sets the controller 
     * 
     * @param controller 
     */
    public void setController(C controller) {
        this.controller = controller;
    }

}
