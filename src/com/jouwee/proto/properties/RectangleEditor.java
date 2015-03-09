/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.properties;

import com.jouwee.proto.gui.ValuedComponent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Rectangle editor
 * 
 * @author Jouwee
 */
public class RectangleEditor extends JComponent implements DocumentListener, PropertyChangeListener, ValuedComponent<Rectangle> {
    
    /** Value */
    private Rectangle value;
    /** x */
    private JTextField fx;
    /** y */
    private JTextField fy;
    /** Width */
    private JTextField width;
    /** Height */
    private JTextField height;

    /**
     * New editor
     */
    public RectangleEditor() {
        this(null);
    }
    
    /**
     * Creates the new Editor
     * 
     * @param value
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public RectangleEditor(Rectangle value) {
        super();
        this.value = value;
        addPropertyChangeListener("value", this);
        // Initializes the graphical user interface
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setLayout(new GridLayout(0, 2));
        setupFields();
        add(new JLabel("X", JLabel.RIGHT));        
        add(fx);        
        add(new JLabel("Y", JLabel.RIGHT));        
        add(fy);        
        add(new JLabel("Width", JLabel.RIGHT));        
        add(width);        
        add(new JLabel("Height", JLabel.RIGHT));        
        add(height);        
    }
    
    /**
     * Sets up the slider
     */
    private void setupFields() {
        width = new JTextField();
        width.getDocument().addDocumentListener(this);
        height = new JTextField();
        height.getDocument().addDocumentListener(this);
        fx = new JTextField();
        fx.getDocument().addDocumentListener(this);
        fy = new JTextField();
        fy.getDocument().addDocumentListener(this);
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }
    
    /**
     * Update the bean
     */
    private void update() {
        if (!fx.getText().isEmpty() && !fy.getText().isEmpty() && 
                !width.getText().isEmpty() && !height.getText().isEmpty()) {
            int ix = Integer.parseInt(fx.getText());
            int iy = Integer.parseInt(fy.getText());
            int iw = Integer.parseInt(width.getText());
            int ih = Integer.parseInt(height.getText());
            setValue(new Rectangle(ix, iy, iw, ih));
        }
    }
    
    @Override
    public final Rectangle getValue() {
        return value;
    }
    
    @Override
    public final void setValue(Rectangle value) {
        Rectangle oldValue = this.value;
        this.value = value;
        firePropertyChange("value", oldValue, value);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        width.setText(String.valueOf((int)value.getWidth()));
        height.setText(String.valueOf((int)value.getHeight()));
    }
    
}
