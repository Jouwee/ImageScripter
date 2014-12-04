package com.jouwee.proto;

import com.jouwee.proto.processors.AverageGrayscale;
import com.jouwee.proto.processors.Binarization;
import java.io.File;

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
        this.project = new Project();
        this.state = new State();
        this.scriptRunner = new ScriptRunner(this);

        // TODO: Test data
        createTestData();
    }

    // TODO: Test data
    private void createTestData() {
        project.getActionList().add(new AverageGrayscale());
        project.getActionList().add(new Binarization());
        project.getActionList().add(new AverageGrayscale());

        try {
            state.set("inputImage", ImageFactory.fromFile(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
            state.set("outputImage", ImageFactory.fromFile(new File("D:\\Imagens\\Wallpaper\\VideoGames\\BF4-Menu.jpg")));
        } catch(Exception ex)  {
            try {
                state.set("inputImage", ImageFactory.fromFile(new File("c:\\TMP\\criancas_brincando.jpeg")));
                state.set("outputImage", ImageFactory.fromFile(new File("c:\\TMP\\criancas_brincando.jpeg")));
            } catch(Exception ex2)  {
                ex.printStackTrace();
            }
        }
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
