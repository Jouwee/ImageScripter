package com.jouwee.proto;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Exception handler
 * 
 * @author Jouwee
 */
public class ExceptionHandler {
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     */
    public static void handle(Exception ex) {
        handle(ex, null, false, null);
    }
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     * @param quiet {@code true} if the handling should be quiet (No user output)
     */
    public static void handle(Exception ex, boolean quiet) {
        handle(ex, null, quiet, null);
    }
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     * @param errorMessage Extra error message
     */
    public static void handle(Exception ex, String errorMessage) {
        handle(ex, errorMessage, false, null);
    }
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     * @param errorMessage Extra error message
     * @param quiet {@code true} if the handling should be quiet (No user output)
     */
    public static void handle(Exception ex, String errorMessage, boolean quiet) {
        handle(ex, errorMessage, quiet, null);
    }
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     * @param errorMessage Extra error message
     * @param extraData Any extra information
     */
    public static void handle(Exception ex, String errorMessage, Map<String, Object> extraData) {
        handle(ex, errorMessage, false, extraData);
    }
    
    /**
     * Handles an exception
     * 
     * @param ex Exception to handle
     * @param errorMessage Extra error message
     * @param quiet {@code true} if the handling should be quiet (No user output)
     * @param extraData Any extra information
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static void handle(Exception ex, String errorMessage, boolean quiet, Map<String, Object> extraData) {
        System.err.println("An error has occured:");
        if (errorMessage != null) {
            System.err.println(errorMessage);
        }
        if (extraData != null) {
            for (Entry entry : extraData.entrySet()) {
                System.err.println(entry.getKey() + " = " + entry.getValue());
            }
        }
        ex.printStackTrace();
    }
    
}
