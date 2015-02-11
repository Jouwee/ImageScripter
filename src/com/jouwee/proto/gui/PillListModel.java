/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Pill list model
 * 
 * @author Jouwee
 * @param <T>
 */
public class PillListModel<T> implements PillModel<T> {

    /** List */
    private final List<T> list;
    /** Listeners */
    private final List<ListDataListener> listeners;
    
    /**
     * New models
     */
    public PillListModel() {
        list = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    /**
     * Adds a element tot the model.
     * 
     * @param value
     */
    public void add(T value) {
        list.add(value);
        for (ListDataListener listener : listeners) {
            listener.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, list.size() - 1, list.size() - 1));
        }
    }
    
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public T getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
    
}
