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
     * @param callback 
     * @return String
     */
    public String getFullBody(Callback callback) {
        StringBuilder sb = new StringBuilder();
        sb.append("function ").append(callback.getFunctionName()).append("(");
        boolean first = true;
        for (Callback.Parameter parameter : callback.getParameters()) {
            if(!first) {
                sb.append(", ");
            }
            sb.append(parameter.getName());
            first = false;
        }
        sb.append(") {\n");
        sb.append(callback.getBody());
        sb.append("\n}");
        return sb.toString();
    }
    
}
