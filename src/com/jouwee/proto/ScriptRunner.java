package com.jouwee.proto;

import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Script runner
 * 
 * @author Jouwee
 */
public class ScriptRunner implements CommonStates {
   
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
        // Register the autorunner
        AutoRunner autoRunner = new AutoRunner();
        model.getState().addPropertyChangeListener(INPUT_IMAGE, autoRunner);
        model.getProject().getActionList().addListListener(autoRunner);
        model.getProject().getActionList().addGlobalPropertyChangeListener(autoRunner);
    }

    /**
     * Runs the project
     */
    public void run() {
        setupExecution();
        for (Action action : model.getProject().getActionList()) {
            action.run();
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

    /**
     * Auto runner for when it notices a significant change in the model
     */
    private class AutoRunner implements PropertyChangeListener, ListListener {
    
    @Override
        public void propertyChange(PropertyChangeEvent evt) {
            run();
        }

        @Override
        public void itemAdded(ListEvent event) {
            run();
        }

        @Override
        public void itemRemoved(ListEvent event) {
            run();
        }

        @Override
        public void listCleared(ListEvent event) {
            run();
        }
    
    }

}
