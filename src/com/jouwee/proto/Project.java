package com.jouwee.proto;

import java.io.File;

/**
 * The project. This is non-volatile data
 * 
 * @author Jouwee
 */
public class Project {
    
    /** Action list */
    private final ActionList actionList;
    /** Save file */
    private transient File projectSaveFile;

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

    /**
     * Returns the project save file
     * 
     * @return File
     */
    public File getProjectSaveFile() {
        return projectSaveFile;
    }

    /**
     * Sets the project save file
     * 
     * @param projectSaveFile 
     */
    public void setProjectSaveFile(File projectSaveFile) {
        this.projectSaveFile = projectSaveFile;
    }
    
}
