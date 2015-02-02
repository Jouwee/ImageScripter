/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto;

/**
 * Action type filter
 * 
 * @author Jouwee
 */
public interface ActionTypeFilter {
    
    /** Empty filter that accepts every action type */
    public static final ActionTypeFilter EMPTY_FILTER = new ActionTypeFilter() {

        @Override
        public boolean accept(Class<? extends Action> actionType) {
            return true;
        }
    };
    
    /**
     * Check if accepts the specified action
     * 
     * @param actionType Action type
     * @return boolean
     */
    public abstract boolean accept(Class<? extends Action> actionType);
    
}
