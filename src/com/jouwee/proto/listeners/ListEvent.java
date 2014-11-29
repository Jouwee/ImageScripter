package com.jouwee.proto.listeners;

/**
 * List event
 * 
 * @author Jouwee
 */
public class ListEvent {
    
    /** Source */
    private final Object source;
    /** Element affected */
    private final Object element;
    /** Index affected */
    private final int index;

    /**
     * Create a new List event
     * 
     * @param source
     * @param element
     * @param index
     */
    public ListEvent(Object source, Object element, int index) {
        this.source = source;
        this.element = element;
        this.index = index;
    }

    /**
     * Returns the source of the event
     * 
     * @return Object
     */
    public Object getSource() {
        return source;
    }

    /**
     * Returns the element affected
     * 
     * @return Object
     */
    public Object getElement() {
        return element;
    }

    /**
     * Returns the index affected
     * 
     * @return int
     */
    public int getIndex() {
        return index;
    }

}
