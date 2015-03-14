package com.jouwee.proto.properties;

import com.jouwee.proto.Action;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.gui.ValuedComponent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;

/**
 * Property editor factory
 * 
 * @author Jouwee
 */
public class PropertyEditorFactory {
    
    /** Registered editors */
    private static final Map<Class, Class<? extends JComponent>> EDITORS;
    static {
        EDITORS = new HashMap<>();
        EDITORS.put(Dimension.class, DimensionEditor.class);
        EDITORS.put(Rectangle.class, RectangleEditor.class);
        EDITORS.put(BinaryThreshold.class, BinaryThresholdEditor.class);
    }
    
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
            JComponent component;
            Class editorClass = EDITORS.get(property.getType());
            component = (JComponent) editorClass.newInstance();
            if (component != null && component instanceof ValuedComponent) {
                new EditorWrapper(component, descriptor, bean);
            }
            return component;
        } catch(IntrospectionException | InstantiationException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }
        return null;
    }
    
    /**
     * Editor wrapper
     */
    private static class EditorWrapper implements PropertyChangeListener {

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
