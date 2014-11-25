package com.jouwee.proto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provider class for views
 * 
 * @author Jouwee
 */
public final class ViewProvider {

    /** List of avaliable views */
    private static final List<Class> availableViews = new ArrayList<>();

    // TODO: Find a way to correctly load the available view
    static {
        availableViews.add(PropertiesView.class);
        availableViews.add(ActionListView.class);
    }
    
    /**
     * Returns an unmodifiable list of available views
     * 
     * @return {@code List<Class>}
     */
    public static List<Class> getAvailableViews() {
        return Collections.unmodifiableList(availableViews);
    }
    
    /**
     * Returns a new instance of the specified {@code viewType}
     * 
     * @param <T> View type
     * @param viewType View type
     * @return View
     * @throws InstantiationException if there was an exception while creating the view
     * @throws IllegalAccessException if the view constructor is private or protected
     */
    public static <T extends View> T getNewInstance(Class<T> viewType) throws InstantiationException, IllegalAccessException {
        return viewType.newInstance();
    }
    
}
