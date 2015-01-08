package com.jouwee.proto;

/**
 *
 * @author Jouwee
 */
public class ScriptProvider {
    
    /** Singleton's instance */
    private static ScriptProvider instance;
    
    /**
     * Returns the singleton's instance
     * 
     * @return ScriptProvider
     */
    public static final ScriptProvider def() {
        if (instance == null) {
            instance = new ScriptProvider();
        }
        return instance;
    }
    
    /**
     * Returns the callback's full body
     * 
     * @param callbackHeader 
     * @param callback
     * @return String
     */
    public String getFullBody(Callback callback, CallbackHeader callbackHeader) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader(callbackHeader));
        sb.append(callback.getBody());
        sb.append("\n}");
        return sb.toString();
    }
    
    /**
     * Get the header
     * 
     * @param callbackHeader
     * @return String
     */
    public String getHeader(CallbackHeader callbackHeader) {
        StringBuilder sb = new StringBuilder();
        sb.append("function ").append(callbackHeader.getFunctionName()).append("(");
        boolean first = true;
        for (Parameter parameter : callbackHeader.getParameters()) {
            if(!first) {
                sb.append(", ");
            }
            sb.append(parameter.getName());
            first = false;
        }
        sb.append(") {\n");
        return sb.toString();
    }
    
}
