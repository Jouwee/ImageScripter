package com.jouwee.proto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that the specified attribute is editable by the user in the editor panel. Also contains
 * some metadata regarding the characteristics of the attribute
 * 
 * @author Jouwee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    
    /**
     * Property name
     * 
     * @return 
     */
    public String name();
    
}
