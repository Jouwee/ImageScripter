package com.jouwee.proto;

import java.util.ResourceBundle;

/**
 *
 * @author Jouwee
 */
public final class LocaleBundle {

    /** Resource bundle for the locales */
    private static final ResourceBundle bundle = ResourceBundle.getBundle("com.jouwee.proto.locale.Locale");
        
    /**
     * Get the default bundle instance
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle def() {
        return bundle;
    }
    
}
