package com.jouwee.proto.properties;

import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.ExceptionHandler;
import java.awt.BorderLayout;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Binary threshold editor
 * 
 * @author Jouwee
 */
public class BinaryThresholdEditor extends JComponent implements ChangeListener {
    
    /** Bean */
    private final Action bean;
    /** Field */
    private final Field field;
    /** Property Descriptor */
    private final PropertyDescriptor descriptor;
    /** Threshold slider */
    private JSlider slider;
    
    /**
     * Creates the new Editor
     * 
     * @param bean
     * @param field
     * @param descriptor
     */
    public BinaryThresholdEditor(Action bean, Field field, PropertyDescriptor descriptor) {
        super();
        this.bean = bean;
        this.field = field;
        this.descriptor = descriptor;
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
        try {
            descriptor.getWriteMethod().invoke(BinaryThresholdEditor.this.bean, new BinaryThreshold(slider.getValue()));
            // TODO: Should not be here
            Application.getModel().getScriptRunner().run();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ExceptionHandler.handle(ex);
        }
    }
    
    /**
     * Returns the bean
     * 
     * @return Action
     */
    public Action getBean() {
        return bean;
    }

    /**
     * Returns the field
     * 
     * @return Field
     */
    public Field getField() {
        return field;
    }

    /**
     * Returns the property descriptor
     * 
     * @return PropertyDescriptor
     */
    public PropertyDescriptor getDescriptor() {
        return descriptor;
    }
    
}
