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
    private final transient ScriptRunner scriptRunner;
    /** Script engine */
    private final transient ScriptEngine scriptEngine;

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
        this.scriptEngine = new ScriptEngine();
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

    /**
     * Returns the script engine
     * 
     * @return ScriptEngine
     */
    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

}
