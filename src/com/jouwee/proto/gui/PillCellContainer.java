/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto.gui;

import com.jouwee.proto.Interface;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * Cell container
 *
 * @author Jouwee
 */
public class PillCellContainer extends JComponent implements MouseListener, Interface {

    /** Value */
    private final Object value;
    /** Selected */
    private boolean selected;
    
    /**
     * New container
     *
     * @param value
     * @param renderer
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public PillCellContainer(Object value, PillRenderer renderer) {
        super();
        this.value = value;
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        setOpaque(true);
        addMouseListener(this);
        add(renderer.getComponent(value));
    }

    /**
     * Adds a action listener 
     * 
     * @param listener 
     */
    public void addActionListener(ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }
    
    /**
     * Return if is selected
     * 
     * @return boolean
     */
    public final boolean isSelected() {
        return selected;
    }

    /**
     * Sets if is selected
     * 
     * @param selected 
     */
    public final void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    /**
     * Returns the value
     * 
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setSelected(true);
        ActionEvent ex = new ActionEvent(this, 0, "");
        for (ActionListener l : listenerList.getListeners(ActionListener.class)) {
            l.actionPerformed(ex);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(HIGHLIGHT_COLOR);
        revalidate();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(BASE_COLOR);
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            if (selected) {
                g.setColor(SELECTION_COLOR);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getBounds().width, getBounds().height);
        }
        super.paintComponent(g);
    }

}
