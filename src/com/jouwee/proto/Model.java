package com.jouwee.proto;

import java.io.File;
import javax.imageio.ImageIO;

/**
 * Model for the application
 * 
 * @author Jouwee
 */
public class Model {
    
    // TODO: Should not be a singleton, but who would have it?
    /** Model singleton */
    private static final Model instance = new Model();
    /** Project */
    private final Project project;
    /** State of the application */
    private final State state;

    /**
     * Creates a new Model
     */
    public Model() {
        this.project = new Project();
        this.state = new State();
        
        // TODO: Test data
        createTestData();
        
    }
    
    // TODO: Test data
    private void createTestData() {
        project.getActionList().add(new Action() {});
        project.getActionList().add(new Action() {});
        project.getActionList().add(new Action() {});
        
        try {
            state.set("inputImage", ImageIO.read(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
            state.set("outputImage", ImageIO.read(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
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
    
}
