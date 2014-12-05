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
    
    /**
     * Item removed from the list
     * 
     * @param event 
     */
    public abstract void itemRemoved(ListEvent event);
    
    /**
     * List cleared
     * 
     * @param event 
     */
    public abstract void listCleared(ListEvent event);
    
}
