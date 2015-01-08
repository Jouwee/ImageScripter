package com.jouwee.proto;

/**
 * Callback for scripts
 * 
 * @author Jouwee
 */
public class Callback {

    /** Function's default body */
    private String body;

    /**
     * New callback
     */
    public Callback() {
        body = "";
    }

    /**
     * New callback with default body
     * 
     * @param body 
     */
    public Callback(String body) {
        this.body = body;
    }
    
    /**
     * Returns the function's body
     * 
     * @return String
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the function body
     * 
     * @param body 
     */
    public void setBody(String body) {
        this.body = body;
    }
    
}
