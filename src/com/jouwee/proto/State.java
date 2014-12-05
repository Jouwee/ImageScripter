package com.jouwee.proto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * State of the application. This is the volatile data
 * 
 * @author Jouwee
 */
public class State {
    
    /** Key-value pairs of data */
    private final Map<String, Object> keyValue;
    /** Property change support */
    private final PropertyChangeSupport propertyChangeSupport;
    
    /**
     * Creates a new state for the application
     */
    public State() {
        keyValue = new HashMap<>();
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    /**
     * Clears the state
     */
    public void clear() {
        // Old entry set for event firing
        Set<Entry<String, Object>> oldEntrySet = keyValue.entrySet();
        keyValue.clear();
        // Fires the events
        for (Entry<String, Object> entry : oldEntrySet) {
            propertyChangeSupport.firePropertyChange(entry.getKey(), entry.getValue(), null);
        }
    }
    
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
     * Sets a value for the state
     * 
     * @param key Key
     * @param value Value
     */
    public void set(String key, Object value) {
        Object oldValue = keyValue.get(key);
        keyValue.put(key, value);
        propertyChangeSupport.firePropertyChange(key, oldValue, value);
    }
    
    /**
     * Returns a value in the state
     * 
     * @param key Key
     * @return Object
     */
    public Object get(String key) {
        return keyValue.get(key);
    }
    
}
