package com.jouwee.proto.annotations;

import com.jouwee.proto.mvc.Controller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * View metadata
 * 
 * @author Jouwee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ViewMeta {
    
    /**
     * Name of the view
     * 
     * @return String
     */
    public String name();
    
    /**
     * Icon
     * 
     * @return String
     */
    public String icon() default "";
    
    /** 
     * Controller
     * 
     * @return Controller
     */
    public Class<? extends Controller> controller();
    
}
