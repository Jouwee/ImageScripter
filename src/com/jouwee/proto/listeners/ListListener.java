package com.jouwee.proto.listeners;

import java.util.EventListener;

/**
 * Listener for list events
 * 
 * @author Jouwee
 */
public interface ListListener extends EventListener {
    
    /**
     * Item added to the list
     * 
     * @param event 
     */
    public abstract void itemAdded(ListEvent event);
    
}
