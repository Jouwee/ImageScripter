package com.jouwee.proto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Action list
 * 
 * @author Jouwee
 */
public class ActionList implements Iterable<Action> {
    
    /** List of actions */
    private final List<Action> list;

    /**
     * Crates a new action list
     */
    public ActionList() {
        list = new ArrayList<>();
    }

    /**
     * Adds an action to the list
     * 
     * @param action Action
     */
    public void add(Action action) {
        list.add(action);
    }
    
    /**
     * Returns the size of the action list
     * 
     * @return int
     */
    public int getSize() {
        return list.size();
    }
    
    /**
     * Returns the action at the specified position in the list
     * 
     * @param i Index
     * @return Action
     */
    public Action get(int i) {
        return list.get(i);
    }
    
    @Override
    public Iterator<Action> iterator() {
        return list.iterator();
    }
    
}
