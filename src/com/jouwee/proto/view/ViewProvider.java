package com.jouwee.proto.view;

import com.jouwee.proto.Model;
import com.jouwee.proto.view.PropertiesView;
import com.jouwee.proto.view.ActionListView;
import com.jouwee.proto.view.ActionBrowserView;
import com.jouwee.proto.view.ScriptEditorView;
import com.jouwee.proto.view.ImageView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    // TODO: Find a way to correctly load the available views
    static {
        availableViews.add(PropertiesView.class);
        availableViews.add(ActionListView.class);
        availableViews.add(ImageView.class);
        availableViews.add(ActionBrowserView.class);
        availableViews.add(ScriptEditorView.class);
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
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static <T extends View> T getNewInstance(Class<T> viewType) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Constructor<T> constructor = viewType.getConstructor(Model.class);
        T instance = constructor.newInstance(Model.def());
        return instance;
    }
    
}
