/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import javax.swing.JToggleButton;

/**
 * Pill
 * 
 * @author Jouwee
 * @param <T>
 */
public class Pill<T> extends JToggleButton {
    
    /** Pill group */
    private final PillGroup group;
    /** Value */
    private final T value;
    /** Pill renderer */
    private PillRenderer renderer;
    
    /**
     * Create a new Pill
     * 
     * @param group
     * @param value 
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Pill(PillGroup group, T value) {
        super();
        this.group = group;
        this.value = value;
        setRenderer(new PillDefaultRenderer());
        group.add(this);
    }

    /**
     * Return the group
     * 
     * @return PillGroup
     */
    public PillGroup getGroup() {
        return group;
    }

    /**
     * Return the value
     * 
     * @return Value
     */
    public T getValue() {
        return value;
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
        removeAll();
        this.renderer = renderer;
        add(renderer.getComponent(value));
        revalidate();
        repaint();
    }
    
}
