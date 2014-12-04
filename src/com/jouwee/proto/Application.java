package com.jouwee.proto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

/**
 * Main class for the application
 *
 * @author Jouwee
 */
public class Application {

    /** Current model for the application */
    private static Model model;
    /** Application controller */
    private static ApplicationController controller;

    /**
     * Main application method
     *
     * @param args
     */
    public static void main(String[] args) {
        // Creates the model
        model = new Model();
        controller = new ApplicationController();
        // Sets up the Look and Feel for the application
        setupLookAndFeel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Build the main application window
                ApplicationWindow window = new ApplicationWindow();
                window.setDefaultCloseOperation(ApplicationWindow.EXIT_ON_CLOSE);
                // Displays the window
                window.setVisible(true);
            }
        });
    }

    /**
     * Sets up the Look and Feel for the application
     */
    private static void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns the model
     *
     * @return Model
     */
    public static Model getModel() {
        return model;
    }

    /**
     * Sets the model
     *
     * @param model
     */
    public static void setModel(Model model) {
        Application.model = model;
    }

    /**
     * Return the applcation controller
     *
     * @return ApplicationController
     */
    public static ApplicationController getController() {
        return controller;
    }

}
