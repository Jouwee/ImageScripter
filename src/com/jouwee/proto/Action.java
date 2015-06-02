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
public abstract class Action implements PropertyChangeBean {

    /** Enabled */
    public static final String PROP_ENABLED = "PROP_ENABLED";
    /** Callback - Initialize */
    private static final String CALLBACK_INITIALIZE = "initialize";
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
        registerCallback(new CallbackHeader(CALLBACK_INITIALIZE, new Parameter("value", Object.class, "Initialize")), new Callback());
    }
    
    /**
     * Runs the action
     */
    public void run() {
        compileCallbacks();
        if (isCallbackEnabled(CALLBACK_INITIALIZE)) {
            invoke(CALLBACK_INITIALIZE);
        }
        doRun();
    }

    /**
     * Runs the action
     */
    public abstract void doRun();
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
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
    protected final void registerCallback(CallbackHeader header, Callback callback) {
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
        if (!callback.isEnabled()) {
            throw new RuntimeException("Calling a disabled callback! The call to invoke should be conditioned!");
        }
        return Application.getModel().getScriptEngine().invoke(this, header, callback, parameters);
    }
    
    /**
     * Returns if the callback is enabled
     * 
     * @param callbackKey
     * @return boolean
     */
    public boolean isCallbackEnabled(String callbackKey) {
        return callbacks.get(callbackKey).isEnabled();
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
