package com.jouwee.proto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Property dictionary for quick data extraction. This class is contained as a singleton since the action's properties
 * will always be the same.
 * 
 * @author Jouwee
 */
public class PropertyDictionary {
   
    /** Singleton instance */
    private static final PropertyDictionary instance = new PropertyDictionary();
    /** Cache of the property names */
    private final Map<Class, List<String>> cachePropertyNames;
    
    /**
     * Creates a new dictionary
     */
    private PropertyDictionary() {
        cachePropertyNames = new HashMap<>();
    }
    
    /**
     * Returns the singleton's instance
     * 
     * @return PropertyDictionary;
     */
    public static PropertyDictionary def() {
        return instance;
    }
    
    /**
     * Get the names of the properties for an action
     * 
     * @param action
     * @return {@code List<String>}
     */
    public List<String> getPropertyNames(Action action) {
        return getPropertyNames(action.getClass());
    }
    
    /**
     * Get the names of the properties for an action type
     * 
     * @param actionType
     * @return {@code List<String>}
     */
    public List<String> getPropertyNames(Class<? extends Action> actionType) {
        if (cachePropertyNames.containsKey(actionType)) {
            return cachePropertyNames.get(actionType);
        } else {
            List<String> propertyNames = loadPropertyNames(actionType);
            cachePropertyNames.put(actionType, propertyNames);
            return propertyNames;
        }
    }
    
    /**
     * Get the names of the properties for an action type, ignoring cache
     * 
     * @param actionType
     * @return {@code List<String>}
     */
    private List<String> loadPropertyNames(Class<? extends Action> actionType) {
        List<String> propertyNames = new ArrayList<>();
        for (Field field : actionType.getDeclaredFields()) {
            if (field.isAnnotationPresent(Property.class)) {
                propertyNames.add(field.getName());
            }
        }
        return propertyNames;
    }
    
}
