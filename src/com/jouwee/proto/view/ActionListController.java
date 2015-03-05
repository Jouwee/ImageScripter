package com.jouwee.proto.view;

import com.jouwee.proto.Action;
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
    
    /**
     * Move selected actions up
     */
    public void moveSelectionUp() {
        Action action = getView().getSelectedAction();
        getModel().moveUp(action);
        getView().setSelectedAction(action);
    }
    
    /**
     * Move selected actions down
     */
    public void moveSelectionDown() {
        Action action = getView().getSelectedAction();
        getModel().moveDown(action);
        getView().setSelectedAction(action);
    }
    
}
