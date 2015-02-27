package com.jouwee.proto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Callback for scripts
 * 
 * @author Jouwee
 */
public class Callback implements PropertyChangeBean {

    /** Property - Enabled */
    public static final String PROP_ENABLED = "enabled";
    /** Property - Body */
    public static final String PROP_BODY = "body";
    /** Callback enabled */
    private boolean enabled;
    /** Function's default body */
    private String body;
    /** PropertyChange support */
    private PropertyChangeSupport propertyChangeSupport;

    /**
     * New callback
     */
    public Callback() {
        this("");
    }

    /**
     * New callback with default body
     * 
     * @param body 
     */
    public Callback(String body) {
        this.body = body;
        enabled = false;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
    
    /**
     * Returns the function's body
     * 
     * @return String
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the function body
     * 
     * @param body 
     */
    public void setBody(String body) {
        String oldValue = this.body;
        this.body = body;
        propertyChangeSupport.firePropertyChange(PROP_BODY, oldValue, body);
    }

    /**
     * Returns if the callback is enabled
     * 
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set if the callback is enabled
     * 
     * @param enabled 
     */
    public void setEnabled(boolean enabled) {
        boolean oldValue = this.enabled;
        this.enabled = enabled;
        propertyChangeSupport.firePropertyChange(PROP_ENABLED, oldValue, enabled);
    }
    
}
