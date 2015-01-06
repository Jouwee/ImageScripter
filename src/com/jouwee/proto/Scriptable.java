package com.jouwee.proto;

import java.util.List;

/**
 * Actions that implement this interface allow scripting support
 * 
 * @author Jouwee
 */
public interface Scriptable {
    
    /**
     * Returns the list of callbacks that this action allows
     * 
     * @return {@code List<Callback>}
     */
    public abstract List<Callback> getCallbackList();
    
    /**
     * Compile all callbacks
     */
    public abstract void compileCallbacks();
    
}
