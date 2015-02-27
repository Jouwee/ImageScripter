/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.PropertyChangeBean;
import com.jouwee.proto.ReflectionUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * Component factory
 * 
 * @author Jouwee
 */
public class ComponentFactory {
    
    /** Scroll's unit increment */
    private static final int SCROLL_UNIT_INCREMENT = 16;
    
    /**
     * Creates a scroll pane
     * 
     * @return JScrollPane
     */
    public static JScrollPane scrollPane() {
        return scrollPane(null);
    }
    
    /**
     * Creates a scroll pane
     * 
     * @param viewPort
     * @return JScrollPane
     */
    public static JScrollPane scrollPane(JComponent viewPort) {
        JScrollPane pane = new JScrollPane(viewPort);
        pane.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        pane.getVerticalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        return pane;
    }
    
    /**
     * Returns a checkbox linked with a action's property
     * 
     * @param text
     * @param bean
     * @param property
     * @return JCheckBox
     */
    public static JCheckBox checkbox(String text, final PropertyChangeBean bean, final String property) {
        final JCheckBox checkbox = new JCheckBox(text);
        bean.addPropertyChangeListener(property, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                checkbox.setSelected((boolean) evt.getNewValue());
            }
        });
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReflectionUtils.setValue(bean, property, checkbox.isSelected());
            }
        });
        return checkbox;
    }
    
}
