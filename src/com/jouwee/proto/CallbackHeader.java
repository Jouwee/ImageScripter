package com.jouwee.proto;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.functionName);
        hash = 19 * hash + Arrays.deepHashCode(this.parameters);
        hash = 19 * hash + Objects.hashCode(this.callbackReturn);
        hash = 19 * hash + Objects.hashCode(this.body);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CallbackHeader other = (CallbackHeader) obj;
        if (!Objects.equals(this.functionName, other.functionName)) {
            return false;
        }
        if (!Arrays.deepEquals(this.parameters, other.parameters)) {
            return false;
        }
        if (!Objects.equals(this.callbackReturn, other.callbackReturn)) {
            return false;
        }
        return !Objects.equals(this.body, other.body);
    }
    
    
    
}
