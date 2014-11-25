package com.jouwee.proto;

import java.util.HashMap;
import java.util.Map;

/**
 * State of the application. This is the volatile data
 * 
 * @author Jouwee
 */
public class State {
    
    /** Key-value pairs of data */
    public final Map<String, Object> keyValue;

    /**
     * Creates a new state for the application
     */
    public State() {
        keyValue = new HashMap<>();
    }
    
    /**
     * Sets a value for the state
     * 
     * @param key Key
     * @param value Value
     */
    public void set(String key, Object value) {
        keyValue.put(key, value);
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
