/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Reflection utilities
 *
 * @author Jouwee
 */
public class ReflectionUtils {

    /**
     * Returns the value
     *
     * @param <T>
     * @param bean
     * @param property
     * @param type
     * @return T
     */
    public static <T extends Object> T getValue(Object bean, String property, Class<T> type) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(property, bean.getClass());
            return (T) pd.getReadMethod().invoke(bean);
        } catch(IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * Sets the value
     *
     * @param bean
     * @param property
     */
    public static void setValue(Object bean, String property, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(property, bean.getClass());
            pd.getWriteMethod().invoke(bean, value);
        } catch(IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            ExceptionHandler.handle(e);
        }
    }

}
