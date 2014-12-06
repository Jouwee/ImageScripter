package com.jouwee.proto;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Script runner
 * 
 * @author Jouwee
 */
public class ScriptRunner implements CommonStates, PropertyChangeListener {
   
    /** Model */
    private final Model model;

    /**
     * Creates a new Script Runner
     * 
     * @param model 
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScriptRunner(Model model) {
        this.model = model;
        model.getState().addPropertyChangeListener(INPUT_IMAGE, this);
    }

    /**
     * Runs the project
     */
    public void run() {
        setupExecution();
        for (Action action : model.getProject().getActionList()) {
            if(action instanceof Processor) {
                action.run();
            }
        }
        endExecution();
    }
    
    /**
     * Sets up the execution
     */
    private void setupExecution() {
        model.getState().set(WORK_IMAGE, model.getState().get(INPUT_IMAGE));
    }
    
    /**
     * Ends the execution
     */
    private void endExecution() {
        model.getState().set(OUTPUT_IMAGE, model.getState().get(WORK_IMAGE));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        run();
    }

}
