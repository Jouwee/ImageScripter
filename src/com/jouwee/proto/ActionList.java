package com.jouwee.proto;

import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import java.beans.PropertyChangeListener;
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
    private final transient List<ListListener> listListeners;
    /** Global property change listeners */
    private final transient List<PropertyChangeListener> globalPropertyChangeListeners;
    
    /**
     * Crates a new action list
     */
    public ActionList() {
        list = new ArrayList<>();
        listListeners = new ArrayList<>();
        globalPropertyChangeListeners = new ArrayList<>();
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
        insert(action, index);
    }

    /**
     * Adds an action to the list
     *
     * @param action Action
     * @param index
     */
    public void insert(Action action, int index) {
        list.add(index, action);
        fireItemAdded(action, index);
        for (PropertyChangeListener listener : globalPropertyChangeListeners) {
            action.addPropertyChangeListener(listener);
        }
    }
    
    /**
     * Removes the specified action
     * 
     * @param action 
     */
    public void remove(Action action) {
        int index = list.indexOf(action);
        list.remove(index);
        fireItemRemoved(action, index);
    }
    
    /**
     * Move the action up
     * 
     * @param action
     * @return {@code true} if the action was moved (Was not the first)
     */
    public boolean moveUp(Action action) {
        int index = list.indexOf(action);
        if (index == 0) {
            return false;
        }
        remove(action);
        insert(action, index - 1);
        return true;
    }
    
    /**
     * Move the action Down
     * 
     * @param action
     * @return {@code true} if the action was moved (Was not the last)
     */
    public boolean moveDown(Action action) {
        int index = list.indexOf(action);
        if (index == list.size() - 1) {
            return false;
        }
        remove(action);
        insert(action, index + 1);
        return true;    
    }

    /**
     * Clears the list
     */
    public void clear() {
        list.clear();
        fireListCleared();
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
     * Fires the event for item removed
     *
     * @param action
     * @param index
     */
    private void fireItemRemoved(Action action, int index) {
        ListEvent event = new ListEvent(this, action, index);
        for (ListListener listener : listListeners) {
            listener.itemRemoved(event);
        }
    }

    /**
     * Fires the event for the list being cleared
     *
     * @param action
     * @param index
     */
    private void fireListCleared() {
        ListEvent event = new ListEvent(this, null, 0);
        for (ListListener listener : listListeners) {
            listener.listCleared(event);
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
    
    /**
     * Add a property change listener to every existing Action, and also register it for future Actions
     * 
     * @param listener 
     */
    public void addGlobalPropertyChangeListener(PropertyChangeListener listener) {
        // TODO: Add support for property specific and class specific listeners
        for (Action action : this) {
            action.addPropertyChangeListener(listener);
        }
        globalPropertyChangeListeners.add(listener);
    }

}
