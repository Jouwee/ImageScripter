package com.jouwee.proto;

/**
 * Model for the application
 *
 * @author Jouwee
 */
public class Model {

    /** Project */
    private final Project project;
    /** State of the application */
    private final State state;
    /** Script runner */
    private final ScriptRunner scriptRunner;

    /**
     * Creates a new Model
     */
    public Model() {
        this(new Project());
    }
    
    /**
     * Creates a new Model based on a project
     * 
     * @param project
     */
    public Model(Project project) {
        this.project = project;
        this.state = new State();
        this.scriptRunner = new ScriptRunner(this);
    }

    /**
     * Returns the project
     *
     * @return Project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Returns the state of the application
     *
     * @return State
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the script runner
     *
     * @return ScriptRunner
     */
    public ScriptRunner getScriptRunner() {
        return scriptRunner;
    }

}
