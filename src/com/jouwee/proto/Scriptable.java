package com.jouwee.proto;

import java.util.List;

/**
 * Actions that implement this interface allow scripting support
 * 
 * @author Jouwee
 */
public interface Scriptable {
    
    /**
     * Returns the script
     * 
     * @return Script
     */
    public abstract Script getScript();
    
    /**
     * Sets the script
     * 
     * @param script 
     */
    public abstract void setScript(Script script);
    
    /**
     * Returns the list of callbacks that this action allows
     * 
     * @return {@code List<Callback>}
     */
    public abstract List<Callback> getCallbackList();
    
}
