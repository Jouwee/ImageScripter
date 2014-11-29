package com.jouwee.proto;

import com.jouwee.proto.processors.AverageGrayscale;
import java.io.File;

/**
 * Model for the application
 * 
 * @author Jouwee
 */
public class Model {
    
    // TODO: Should not be a singleton, but who would have it?
    /** Model singleton */
    private static final Model instance;
    static{
        instance = new Model();
        instance.scriptRunner.run();
    }
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
        this.project = new Project();
        this.state = new State();
        this.scriptRunner = new ScriptRunner(this);
        
        // TODO: Test data
        createTestData();
    }
    
    // TODO: Test data
    private void createTestData() {
        project.getActionList().add(new AverageGrayscale());
        
        try {
            state.set("inputImage", ImageFactory.fromFile(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
            state.set("outputImage", ImageFactory.fromFile(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
        } catch(Exception ex)  {
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns the singleton's instance
     * 
     * @return Model
     */
    public static Model def() {
        return instance;
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
