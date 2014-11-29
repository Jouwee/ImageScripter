package com.jouwee.proto;

import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
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
    /** List listeners */
    private final List<ListListener> listListeners;

    /**
     * Crates a new action list
     */
    public ActionList() {
        list = new ArrayList<>();
        listListeners = new ArrayList<>();
    }

    /**
     * Add a list listener
     * 
     * @param listener 
     */
    public void addListListener(ListListener listener) {
        listListeners.add(listener);
    }

    /**
     * Remove a list listener
     * 
     * @param listener 
     */
    public void removeListListener(ListListener listener) {
        listListeners.remove(listener);
    }
    
    /**
     * Adds an action to the list
     * 
     * @param action Action
     */
    public void add(Action action) {
        int index = list.size();
        list.add(action);
        fireItemAdded(action, index);
    }
    
    /**
     * Fires the event for item added
     * 
     * @param action 
     * @param index
     */
    private void fireItemAdded(Action action, int index) {
        ListEvent event = new ListEvent(this, action, index);
        for (ListListener listener : listListeners) {
            listener.itemAdded(event);
        }
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
