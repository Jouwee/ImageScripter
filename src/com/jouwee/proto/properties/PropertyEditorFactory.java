package com.jouwee.proto.properties;

import com.jouwee.proto.Action;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.gui.ValuedComponent;
import java.awt.Dimension;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JComponent;

/**
 * Property editor factory
 * 
 * @author Jouwee
 */
public class PropertyEditorFactory {
    
    /**
     * Get the editor for the property
     * 
     * @param bean
     * @param property
     * @return JComponent
     */
    public static JComponent getEditor(final Action bean, Field property) {
        try {
            final PropertyDescriptor descriptor = new PropertyDescriptor(property.getName(), bean.getClass());
            if (property.getType().equals(BinaryThreshold.class)) {
                // TODO: Fix this guy
                return new BinaryThresholdEditor(bean, property, descriptor);
            }
            JComponent component = null;
            if (property.getType().equals(Dimension.class)) {
                component = new DimensionEditor();
            }
            if (component != null && component instanceof ValuedComponent) {
                new EditorWrapper(component, descriptor, bean);
            }
            return component;
        } catch(IntrospectionException ex) {
            ExceptionHandler.handle(ex);
        }
        return null;
    }
    
    /**
     * Editor wrapper
     */
    private static class EditorWrapper implements PropertyChangeListener {
        
        /** Component */
        private final JComponent component;
        /** Editor */
        private final ValuedComponent editor;
        /** Property descriptor */
        private final PropertyDescriptor descriptor; 
        /** Bean */
        private final Action bean; 

        /**
         * New wrapper
         */
        @SuppressWarnings("LeakingThisInConstructor")
        public EditorWrapper(JComponent component, PropertyDescriptor descriptor, Action bean) {
            this.component = component;
            this.editor = (ValuedComponent) component;
            this.descriptor = descriptor;
            this.bean = bean;
            component.addPropertyChangeListener(this);
            try {
                editor.setValue(descriptor.getReadMethod().invoke(bean));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ExceptionHandler.handle(ex);
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            try {
                descriptor.getWriteMethod().invoke(bean, editor.getValue());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ExceptionHandler.handle(ex);
            }
        }
        
    }
    
}
