package com.jouwee.proto.properties;

import com.jouwee.proto.gui.ValuedComponent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Binary threshold editor
 * 
 * @author Jouwee
 */
public class BinaryThresholdEditor extends JComponent implements ChangeListener, ValuedComponent<BinaryThreshold> {
    
    /** Value */
    private BinaryThreshold value;
    /** Threshold slider */
    private JSlider slider;
    
    /**
     * Creates the new Editor
     */
    public BinaryThresholdEditor() {
        super();
        // Initializes the graphical user interface
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        setupSlider();
        add(slider);        
    }
    
    /**
     * Sets up the slider
     */
    private void setupSlider() {
        slider = new JSlider(0, 255);
        slider.addChangeListener(this);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        setValue(new BinaryThreshold(slider.getValue()));
    }

    @Override
    public BinaryThreshold getValue() {
        return value;
    }

    @Override
    public void setValue(BinaryThreshold value) {
        BinaryThreshold oldValue = this.value;
        this.value = value;
        firePropertyChange("value", oldValue, value);
    }
    
}
