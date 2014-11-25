package com.jouwee.proto;

/**
 * The project. This is non-volatile data
 * 
 * @author Jouwee
 */
public class Project {
    
    /** Action list */
    private final ActionList actionList;

    /**
     * Creates a new project
     */
    public Project() {
        this.actionList = new ActionList();
    }
    
    /**
     * Returns the list of actions
     * 
     * @return ActionList
     */
    public ActionList getActionList() {
        return actionList;
    }
    
}
