/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

import java.util.HashSet;
import java.util.Set;

/**
 * Set of user preferences
 * 
 * @author Jouwee
 */
public class Preferences {
    
    /** Recent projects */
    private Set<String> recentProjects;
    /** Recent inputs */
    private Set<String> recentInputs;

    /**
     * Set of user preferences
     */
    public Preferences() {
        recentProjects = new HashSet<>();
        recentInputs = new HashSet<>();
    }

    /**
     * Returns the recent projects set
     * 
     * @return {@code Set<String>}
     */
    public Set<String> getRecentProjects() {
        return recentProjects;
    }

    /**
     * Sets the recent projects set
     * 
     * @param recentProjects 
     */
    public void setRecentProjects(Set<String> recentProjects) {
        this.recentProjects = recentProjects;
    }

    /**
     * Returns the recent input set
     * 
     * @return {@code Set<String>}
     */
    public Set<String> getRecentInputs() {
        return recentInputs;
    }

    /**
     * Sets the recent input set
     * 
     * @param recentInputs 
     */
    public void setRecentInputs(Set<String> recentInputs) {
        this.recentInputs = recentInputs;
    }
    
}
