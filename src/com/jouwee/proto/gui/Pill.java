/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Pill
 * 
 * @author Jouwee
 * @param <T>
 */
public class Pill<T> extends JComponent implements ListDataListener, ActionListener {
    
    /** Model */
    private PillModel<T> model;
    /** Pill renderer */
    private PillRenderer renderer;
    /** Selected pill */
    private PillCellContainer selectedCell;
    
    /**
     * New pill group
     */
    public Pill() {
        this(new PillListModel<T>());
    }
    
    /**
     * New pill group
     * 
     * @param model
     */
    public Pill(PillModel<T> model) {
        super();
        selectedCell = null;
        setModel(model);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setRenderer(new PillDefaultRenderer());
    }
    
    /**
     * Adds a pill selection listener
     * 
     * @param listener 
     */
    public void addPillSelectionListener(PillSelectionListener listener) {
        listenerList.add(PillSelectionListener.class, listener);
    }

    /**
     * Removes a pill selection listener
     * 
     * @param listener 
     */
    public void removePillSelectionListener(PillSelectionListener listener) {
        listenerList.remove(PillSelectionListener.class, listener);
    }

    /**
     * Fires a pill selection changed event
     * 
     * @param index 
     */
    private void firePillSelectionChanged(int index) {
        PillSelectionEvent event = new PillSelectionEvent(index, this);
        for (PillSelectionListener listener : listenerList.getListeners(PillSelectionListener.class)) {
            listener.valueChanged(event);
        }
    }
    
    /**
     * Rebuild the pills
     */
    private void rebuild() {
        removeAll();
        for (int i = 0; i < getModel().getSize(); i++) {
            PillCellContainer cell = new PillCellContainer(getModel().getElementAt(i), renderer);
            cell.addActionListener(this);
            add(cell);
        }
        revalidate();
        repaint();
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        rebuild();
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        rebuild();
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        rebuild();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedCell != null) {
            selectedCell.setSelected(false);
        }
        selectedCell = (PillCellContainer) e.getSource();
        firePillSelectionChanged(getIndexOf(selectedCell));
    }
    
    /**
     * Returns the index of the cell container
     * 
     * @param cell
     * @return int
     */
    private int getIndexOf(PillCellContainer cell) {
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i) == selectedCell) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the selected pill
     * 
     * @return Pill
     */
    public T getSelectedValue() {
        return (T) selectedCell.getValue();
    }
    
    /**
     * Returns the current renderer
     * @return 
     */
    public final PillRenderer getRenderer() {
        return renderer;
    }

    /**
     * Sets a new renderer
     * 
     * @param renderer 
     */
    public final void setRenderer(PillRenderer renderer) {
        this.renderer = renderer;
        rebuild();
    }
   
    /**
     * Returns the PillModel
     * 
     * @return PillModel
     */
    public final PillModel<T> getModel() {
        return model;
    }

    /**
     * Sets the pill model
     * 
     * @param model 
     */
    public final void setModel(PillModel<T> model) {
        if (this.model != null) {
            this.model.removeListDataListener(this);
        }
        this.model = model;
        this.model.addListDataListener(this);
    }
}
