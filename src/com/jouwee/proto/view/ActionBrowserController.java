package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.ActionProvider;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.Model;
import com.jouwee.proto.mvc.Controller;
import java.util.HashMap;
import java.util.Map;

/**
 * Action browser controller
 * 
 * @author Jouwee
 */
public class ActionBrowserController extends Controller<Model, ActionBrowserView> {
    
    /**
     * Adds the selected action
     * 
     * @param actionType
     */
    public void addAction(Class<? extends Action> actionType) {
        try {
            getModel().getProject().getActionList().add(ActionProvider.getNewInstance(actionType));
        } catch (IllegalAccessException | InstantiationException ex) {
            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("actionClass", actionType);
            ExceptionHandler.handle(ex, "Was not able to add an action to the list", errorInfo);
        }
    }
    
}
