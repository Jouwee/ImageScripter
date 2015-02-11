/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto.gui;

import javax.swing.event.ListDataListener;

/**
 * Pill model
 *
 * @author Jouwee
 * @param <T>
 */
public interface PillModel<T> {

    /**
     * Returns the length of the list.
     *
     * @return the length of the list
     */
    public abstract int getSize();

    /**
     * Returns the value at the specified index.
     *
     * @param index the requested index
     * @return the value at <code>index</code>
     */
    public abstract T getElementAt(int index);

    /**
     * Adds a listener to the list that's notified each time a change to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be added
     */
    public abstract void addListDataListener(ListDataListener l);

    /**
     * Removes a listener from the list that's notified each time a change to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be removed
     */
    public abstract void removeListDataListener(ListDataListener l);

}
