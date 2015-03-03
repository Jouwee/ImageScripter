/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

/**
 * Component with a value
 * 
 * @author Jouwee
 * @param <T>
 */
public interface ValuedComponent<T> {
    
    /**
     * Returns the value
     * 
     * @return T
     */
    public T getValue();
    
    /**
     * Sets the value
     * 
     * @param value 
     */
    public void setValue(T value);
    
}
