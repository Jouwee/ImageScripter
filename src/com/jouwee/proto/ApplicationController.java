package com.jouwee.proto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jouwee.proto.gson.InterfaceAdapter;
import com.jouwee.proto.utils.FileUtils;
import com.jouwee.proto.utils.SystemUtils;
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
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            open(fileChooser.getSelectedFile());
        }
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
            // Saves recent projects
            Application.getPreferences().getRecentProjects().add(file.getAbsolutePath());
            // TODO: I still have to auto-update the menu
            savePreferences();
        } catch(IOException ex) {
            ExceptionHandler.handle(ex, "Error while opening file " + file.getAbsolutePath());
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
     * @param files 
     */
    public void openInput(File[] files) {
        try {
            Application.getModel().getState().set(INPUT, new ImageInput(ImageFactory.fromFile(files)));
            // Saves recent projects
            for (File file : files) {
                Application.getPreferences().getRecentInputs().add(file.getAbsolutePath());
            }
            // TODO: I still have to auto-update the menu
            savePreferences();
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    /**
     * Load the user preferences
     * 
     * @return Preferences
     */
    public Preferences loadPreferences() {
        String localDataPath = SystemUtils.getLocalDataPath();
        String appDir = "ImageScripter";
        FileUtils.mkdirIfNotExists(localDataPath + appDir);
        String prefFile = localDataPath + appDir + File.separator + "pref.json";
        if (FileUtils.exists(prefFile)) {
            try {
                String jsonBuffer = FileUtils.load(new File(prefFile));
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(jsonBuffer, Preferences.class);
            } catch(IOException ex) {
                ExceptionHandler.handle(ex, "Error while opening file " + prefFile);
            }
        }
        return new Preferences();
    }

    /**
     * Saves the user preferences
     */
    public void savePreferences() {
        String localDataPath = SystemUtils.getLocalDataPath();
        String appDir = "ImageScripter";
        FileUtils.mkdirIfNotExists(localDataPath + appDir);
        String prefFile = localDataPath + appDir + File.separator + "pref.json";
        System.out.println("save: " + prefFile);
        Gson gson = new GsonBuilder().create();
        try {
            String jsonBuffer = gson.toJson(Application.getPreferences());
            FileUtils.save(jsonBuffer, new File(prefFile));
        } catch(IOException | IllegalArgumentException | UnsupportedOperationException ex) {
            ExceptionHandler.handle(ex, "Error while saving file " + prefFile);
        }
    }
    
}
