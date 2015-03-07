/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.properties;

import com.jouwee.proto.gui.ValuedComponent;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Dimension editor
 * 
 * @author Jouwee
 */
public class DimensionEditor extends JComponent implements DocumentListener, ValuedComponent<Dimension> {
    
    /** Value */
    private Dimension value;
    /** Width */
    private JTextField width;
    /** Height */
    private JTextField height;

    /**
     * New editor
     */
    public DimensionEditor() {
        this(null);
    }
    
    /**
     * Creates the new Editor
     * 
     * @param value
     */
    public DimensionEditor(Dimension value) {
        super();
        this.value = value;
        // Initializes the graphical user interface
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setupFields();
        add(width);        
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
        if (!width.getText().isEmpty() && !height.getText().isEmpty()) {
            setValue(new Dimension(Integer.parseInt(width.getText()), Integer.parseInt(height.getText())));
        }
    }
    
    @Override
    public final Dimension getValue() {
        return value;
    }
    
    @Override
    public final void setValue(Dimension value) {
        Dimension oldValue = this.value;
        this.value = value;
        firePropertyChange("value", oldValue, value);
    }
    
}
