package com.jouwee.proto.view;

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
 */
public abstract class View<T> extends JComponent implements PropertyChangeListener {

    /** Model of this view */
    private T model;

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

}
