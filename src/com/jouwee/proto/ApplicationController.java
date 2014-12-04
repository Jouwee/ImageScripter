package com.jouwee.proto;

/**
 * Controller for the application
 */
public class ApplicationController {

    /**
     * Create a new project
     */
    public void newPoject() {
        Application.setModel(new Model());
    }

}
