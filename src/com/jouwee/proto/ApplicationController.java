package com.jouwee.proto;

import com.google.gson.Gson;
import com.jouwee.proto.utils.FileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * Controller for the application
 */
public class ApplicationController {

    /**
     * Create a new project
     */
    public void newPoject() {
        initializeProject();
        initializeState();
    }
    
    /**
     * Initializes the project
     */
    private void initializeProject() {
        Project project = Application.getModel().getProject();
        project.getActionList().clear();
        project.setProjectSaveFile(null);
    }

    /**
     * Initializes the state
     */
    private void initializeState() {
        State state = Application.getModel().getState();
    }
    
    /**
     * Quick saves the project
     */
    public void quickSave() {
        Project project = Application.getModel().getProject();
        if (project.getProjectSaveFile() == null) {
            saveAs();
        } else {
            save(project.getProjectSaveFile());
        }
    }
    
    /**
     * Save project with dialog
     */
    public void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        save(fileChooser.getSelectedFile());
    }
    
    /**
     * Saves the project to the specified file
     * 
     * @param file 
     */
    public void save(File file) {
        Gson gson = new Gson();
        String jsonBuffer = gson.toJson(Application.getModel().getProject());
        try {
            FileUtils.save(jsonBuffer, file);
        } catch(IOException ex) {
            ExceptionHandler.handle(ex, "Error while saving file " + file.getAbsolutePath());
        }
    }
    
    /**
     * Opens the project to the specified file
     */
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        open(fileChooser.getSelectedFile());
    }
    
    /**
     * Opens the project to the specified file
     * 
     * @param file 
     */
    public void open(File file) {
        try {
            String jsonBuffer = FileUtils.load(file);
            Gson gson = new Gson();
            Project p = gson.fromJson(jsonBuffer, Project.class);
        } catch(IOException ex) {
            ExceptionHandler.handle(ex, "Error while saving file " + file.getAbsolutePath());
        }
    }

}
