package com.jouwee.proto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The most abstract representation of an action that can be used
 * 
 * @author Jouwee
 */
public abstract class Action {

    /** Enabled */
    public static final String PROP_ENABLED = "PROP_ENABLED";
    /** Property change support */
    private transient final PropertyChangeSupport propertyChangeSupport;
    /** Callback headers */
    private transient final Map<String, CallbackHeader> callbackHeaders;
    /** Callbacks */
    private final Map<String, Callback> callbacks;
    /** Enabled */
    private boolean enabled;
    
    /**
     * New action
     */
    public Action() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        callbackHeaders = new HashMap<>();
        callbacks = new HashMap<>();
        enabled = true;
    }
    
    /**
     * Runs the action
     */
    public abstract void run();
    
    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called
     * as many times as it is added.
     * If <code>listener</code> is null, no exception is thrown and no action
     * is taken.
     *
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     * If <code>listener</code> was added more than once to the same event
     * source, it will be notified one less time after being removed.
     * If <code>listener</code> is null, or was never added, no exception is
     * thrown and no action is taken.
     *
     * @param listener The PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Add a PropertyChangeListener for a specific property.  The listener
     * will be invoked only when a call on firePropertyChange names that
     * specific property.
     * The same listener object may be added more than once.  For each
     * property,  the listener will be invoked the number of times it was added
     * for that property.
     * If <code>propertyName</code> or <code>listener</code> is null, no
     * exception is thrown and no action is taken.
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     * If <code>listener</code> was added more than once to the same event
     * source for the specified property, it will be notified one less time
     * after being removed.
     * If <code>propertyName</code> is null,  no exception is thrown and no
     * action is taken.
     * If <code>listener</code> is null, or was never added for the specified
     * property, no exception is thrown and no action is taken.
     *
     * @param propertyName The name of the property that was listened on.
     * @param listener The PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Returns the property change support
     * 
     * @return PropertyChangeSupport
     */
    protected PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    
    /**
     * Register a callback
     * 
     * @param header
     * @param callback 
     */
    protected void registerCallback(CallbackHeader header, Callback callback) {
        callbackHeaders.put(header.getFunctionName(), header);
        callbacks.put(header.getFunctionName(), callback);
    }
    
    /**
     * Returns the callback key set
     * 
     * @return 
     */
    public Collection<String> getCallbackKeys() {
        return callbackHeaders.keySet();
    }
    
    /**
     * Returns a callback
     * 
     * @param key 
     * @return Callback
     */
    public Callback getCallback(String key) {
        return callbacks.get(key);
    }
    
    /**
     * Returns a callback
     * 
     * @param key 
     * @return CallbackHeader
     */
    public CallbackHeader getCallbackHeader(String key) {
        return callbackHeaders.get(key);
    }
    
    /**
     * Compile all callbacks
     */
    public void compileCallbacks() {
        for (String string : callbackHeaders.keySet()) {
            Application.getModel().getScriptEngine().compile(callbackHeaders.get(string), callbacks.get(string));
        }
    }
    
    /**
     * Invoke a callback
     * 
     * @param callbackKey
     * @param parameters
     * @return Object
     */
    public Object invoke(String callbackKey, Object... parameters) {
        CallbackHeader header = callbackHeaders.get(callbackKey);
        Callback callback = callbacks.get(callbackKey);
        return Application.getModel().getScriptEngine().invoke(header, callback, parameters);
    }

    /**
     * Returns if the action is enabled
     * 
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets if the action is enabled
     * 
     * @param enabled 
     */
    public void setEnabled(boolean enabled) {
        boolean oldEnabled = this.enabled;
        this.enabled = enabled;
        propertyChangeSupport.firePropertyChange(PROP_ENABLED, oldEnabled, enabled);
    }

}
