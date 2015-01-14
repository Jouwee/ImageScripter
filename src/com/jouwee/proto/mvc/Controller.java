package com.jouwee.proto.mvc;

/**
 * Controller
 * 
 * @author Jouwee
 * @param <T> Model type
 * @param <V> View type
 */
public abstract class Controller<T, V> {

    /** Model of this view */
    private T model;
    /** View */
    private V view;
    
    /**
     * Returns the model
     * 
     * @return Model
     */
    public T getModel() {
        return model;
    }

    /**
     * Sets the model
     * 
     * @param model 
     */
    public void setModel(T model) {
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
    
}
