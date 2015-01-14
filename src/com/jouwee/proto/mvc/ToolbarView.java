package com.jouwee.proto.mvc;

import javax.swing.JComponent;

/**
 * Toolbar view
 * 
 * @author Jouwee
 * @param <M> Model
 * @param <V> View
 * @param <C> Controller
 */
public class ToolbarView<M, V, C> extends JComponent {
    
    /** Model of this view */
    private M model;
    // TODO: Think more about MVC. Should it have the view?
    /** View */
    private V view;
    /** Controller */
    private C controller;
    
    /**
     * Returns the model
     * 
     * @return Model
     */
    public M getModel() {
        return model;
    }

    /**
     * Sets the model
     * 
     * @param model 
     */
    public void setModel(M model) {
        this.model = model;
    }

    /**
     * Returns the view
     * 
     * @return V
     */
    public V getView() {
        return view;
    }

    /**
     * Sets the view
     * 
     * @param view 
     */
    public void setView(V view) {
        this.view = view;
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
