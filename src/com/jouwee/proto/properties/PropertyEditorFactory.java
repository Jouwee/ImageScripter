package com.jouwee.proto.properties;

import com.jouwee.proto.Action;
import com.jouwee.proto.ExceptionHandler;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
    public static JComponent getEditor(Action bean, Field property) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(property.getName(), bean.getClass());
            if (property.getType().equals(BinaryThreshold.class)) {
                return new BinaryThresholdEditor(bean, property, descriptor);
            }
        } catch(IntrospectionException ex) {
            ExceptionHandler.handle(ex);
        }
        return null;
    }
    
}
