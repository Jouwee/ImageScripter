package com.jouwee.proto.view;

import com.jouwee.proto.ActionList;
import com.jouwee.proto.mvc.Controller;

/**
 * Action list controller
 * 
 * @author Jouwee
 */
public class ActionListController extends Controller<ActionList, ActionListView> {
    
    /**
     * Removes the selected action
     */
    public void removeSelectedAction() {
        getModel().remove(getView().getSelectedAction());
    }
    
}
