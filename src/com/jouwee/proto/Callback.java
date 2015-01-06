package com.jouwee.proto;

/**
 * Callback for scripts
 * 
 * @author Jouwee
 */
public class Callback {

    /** Function name of the callback */
    private final String functionName;
    /** Parameters */
    private final Parameter[] parameters;
    /** return */
    private final Return callbackReturn;
    /** Function's default body */
    private String body;

    /**
     * Create a new callback
     * 
     * @param functionName 
     * @param body 
     */
    public Callback(String functionName, String body) {
        this(functionName, body, (Return) null, (Parameter) null);
    }

    /**
     * Create a new Callback
     * 
     * @param functionName
     * @param parameters 
     */
    public Callback(String functionName, Parameter... parameters) {
        this(functionName, "", null, parameters);
    }
    
    /**
     * Create a new Callback
     * 
     * @param functionName
     * @param body
     * @param parameters 
     */
    public Callback(String functionName, String body, Parameter... parameters) {
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
    public Callback(String functionName, String body, Return callbackReturn, Parameter... parameters) {
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
     * Sets the function body
     * 
     * @param body 
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the callback parameters
     * 
     * @return Parameter[]
     */
    public Parameter[] getParameters() {
        return parameters;
    }
    
    /**
     * Callback parameter
     */
    public static class Parameter {
    
        /** Parameter name */
        private final String name;
        /** Parameter type */
        private final Class type;
        /** Parameter description */
        private final String description;

        /**
         * Creates a new parameter
         * @param name
         * @param type
         * @param description 
         */
        public Parameter(String name, Class type, String description) {
            this.name = name;
            this.type = type;
            this.description = description;
        }

        /**
         * Returns the parameter name
         * 
         * @return String
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the parameter type
         * 
         * @return Class
         */
        public Class getType() {
            return type;
        }

        /**
         * Returns the parameter description
         * 
         * @return String
         */
        public String getDescription() {
            return description;
        }
        
    }
    
    /**
     * Callback return
     */
    public static class Return {
    
    }
    
}
