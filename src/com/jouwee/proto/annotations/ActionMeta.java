package com.jouwee.proto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action metadata
 * 
 * @author Jouwee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActionMeta {

    /**
     * Name of the action
     * 
     * @return String
     */
    public String name();
 
    /**
     * Category
     * 
     * @return String
     */
    public String category();
    
}
