/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

/**
 * Functionality category
 * 
 * @author Jouwee
 */
public enum Functionality {
    
    /** All (Used only for filters) */
    ALL("All"),
    /** Transformation */
    TRANSFORMATION("Transformation"),
    /** Color transformation */
    COLOR_TRANSFORMATION("Color transformation"),
    /** Grayscale */
    GRAYSCALE("Grayscale"),
    /** Image information */
    IMAGE_INFO("Image information"),
    /** Undefined (Default) */
    UNDEFINED("Undefined"),
    ;
    
    /** Functionality name */
    private final String name;

    /**
     * Creates a new functionality
     * 
     * @param name 
     */
    private Functionality(String name) {
        this.name = name;
    }

    /**
     * Returns the functionality name
     * 
     * @return String
     */
    public String getName() {
        return name;
    }
    
}
