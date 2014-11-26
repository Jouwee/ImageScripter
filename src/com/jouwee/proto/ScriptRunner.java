package com.jouwee.proto;

/**
 * Script runner
 * 
 * @author Jouwee
 */
public class ScriptRunner {
   
    /** Model */
    private final Model model;

    /**
     * Creates a new Script Runner
     * 
     * @param model 
     */
    public ScriptRunner(Model model) {
        this.model = model;
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
        model.getState().set("workImage", model.getState().get("inputImage"));
    }
    
    /**
     * Ends the execution
     */
    private void endExecution() {
        model.getState().set("outputImage", model.getState().get("workImage"));
    }

}
