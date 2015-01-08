package com.jouwee.proto;

/**
 * Callback header for scripts
 * 
 * @author Jouwee
 */
public class CallbackHeader {

    /** Function name of the callback */
    private final String functionName;
    /** Parameters */
    private final Parameter[] parameters;
    /** return */
    private final Return callbackReturn;
    /** Function's default body */
    private final String body;

    /**
     * Create a new callback
     * 
     * @param functionName 
     * @param body 
     */
    public CallbackHeader(String functionName, String body) {
        this(functionName, body, (Return) null, (Parameter) null);
    }

    /**
     * Create a new Callback
     * 
     * @param functionName
     * @param parameters 
     */
    public CallbackHeader(String functionName, Parameter... parameters) {
        this(functionName, "", null, parameters);
    }
    
    /**
     * Create a new Callback
     * 
     * @param functionName
     * @param body
     * @param parameters 
     */
    public CallbackHeader(String functionName, String body, Parameter... parameters) {
        this(functionName, body, null, parameters);
    }
    
    /**
     * Create a new Callback
     * 
     * @param functionName
     * @param callbackReturn
     * @param body
     * @param parameters 
     */
    public CallbackHeader(String functionName, String body, Return callbackReturn, Parameter... parameters) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.callbackReturn = callbackReturn;
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
     * Returns the callback parameters
     * 
     * @return Parameter[]
     */
    public Parameter[] getParameters() {
        return parameters;
    }
    
}
