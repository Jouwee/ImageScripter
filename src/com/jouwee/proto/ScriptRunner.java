package com.jouwee.proto;

import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.SwingWorker;

/**
 * Script runner
 * 
 * @author Jouwee
 */
public class ScriptRunner implements CommonStates {
   
    /** Model */
    private final Model model;
    /** Thread */
    private Thread thread;
    /** Swing worker */
    private Worker worker;

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
        if (model.getState().get(WORK_IMAGE) == null) {
            return;
        }
        for (Action action : model.getProject().getActionList()) {
            if (action.isEnabled()) {
                action.run();
            }
        }
        endExecution();
    }
    
    /**
     * Runs the project in the background
     */
    public void runInBackground() {
        if (worker != null && !worker.isDone()) {
            worker.setUpdatePending(true);
            return;
        }
        worker = new Worker();
        worker.execute();
    }
    
    /**
     * Sets up the execution
     */
    private void setupExecution() {
        model.getState().set(WORK_IMAGE, model.getState().get(INPUT_IMAGE));
        model.getScriptEngine().init();
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
            runInBackground();
        }

        @Override
        public void itemAdded(ListEvent event) {
            runInBackground();
        }

        @Override
        public void itemRemoved(ListEvent event) {
            runInBackground();
        }

        @Override
        public void listCleared(ListEvent event) {
            runInBackground();
        }
    
    }
    
    /**
     * Worker for running the project
     */
    private class Worker extends SwingWorker<Object, Object> {

        /** Flag set to true if the project has been modified while this was running, so that it should run once more */
        private boolean updatePending;

        /**
         * New worker
         */
        public Worker() {
            updatePending = false;
        }
        
        @Override
        protected Object doInBackground() throws Exception {
            do {
                updatePending = false;
                ScriptRunner.this.run();
            } while(updatePending);
            return null;
        }

        /**
         * Returns if it has a pending update
         * 
         * @return boolean
         */
        public synchronized boolean isUpdatePending() {
            return updatePending;
        }

        /**
         * Sets if it has a pending update
         * 
         * @param updatePending 
         */
        public synchronized void setUpdatePending(boolean updatePending) {
            this.updatePending = updatePending;
        }
        
    }

}
