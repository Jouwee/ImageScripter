package com.jouwee.proto;

/**
 * Callback parameter
 * 
 * @author Jouwee
 */
public class Parameter {
    
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