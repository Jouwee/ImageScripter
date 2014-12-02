package com.jouwee.proto;

/**
 * Callback for scripts
 * 
 * @author Jouwee
 */
public class Callback {

    /** Function name of the callback */
    private final String functionName;
    /** Function's body */
    private String body;

    /**
     * Create a new callback
     * 
     * @param functionName 
     * @param body 
     */
    public Callback(String functionName, String body) {
        this.functionName = functionName;
        this.body = body;
    }

    /**
     * Returns the function name
     * 
     * @return String
     */
    public String getFunctionName() {
        return functionName;
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
