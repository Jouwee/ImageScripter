package com.jouwee.proto;

import java.util.ArrayList;
import java.util.List;

/**
 * THe project that the user is currently working on
 * 
 * @author Jouwee
 */
public class Project {
    
    /** Action list */
    private final List<Action> actionList;

    /**
     * Creates a new project
     */
    public Project() {
        this.actionList = new ArrayList<>();
    }
    
}
