package com.jouwee.proto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jouwee.proto.gson.InterfaceAdapter;
import com.jouwee.proto.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * Controller for the application
 */
public class ApplicationController implements CommonStates {

    /**
     * Create a new project
     */
    public void newPoject() {
        Application.setModel(new Model());
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
        Gson gson = new GsonBuilder().registerTypeAdapter(Action.class, new InterfaceAdapter<Action>()).create();
        try {
            String jsonBuffer = gson.toJson(Application.getModel().getProject());
            FileUtils.save(jsonBuffer, file);
        } catch(IOException | IllegalArgumentException | UnsupportedOperationException ex) {
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
            Gson gson = new GsonBuilder().registerTypeAdapter(Action.class, new InterfaceAdapter<Action>()).create();
            Project project = gson.fromJson(jsonBuffer, Project.class);
            Application.setModel(new Model(project));
        } catch(IOException ex) {
            ExceptionHandler.handle(ex, "Error while saving file " + file.getAbsolutePath());
        }
    }
    
    /**
     * Select the input
     */
    public void selectInput() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.showOpenDialog(null);
        openInput(fileChooser.getSelectedFiles());
    }
    
    /**
     * Opens the input
     * 
     * @param file 
     */
    public void openInput(File[] file) {
        try {
            Application.getModel().getState().set(INPUT, new ImageInput(ImageFactory.fromFile(file)));
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        }
    }

}
