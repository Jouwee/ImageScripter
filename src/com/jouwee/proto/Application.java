package com.jouwee.proto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class for the application
 * 
 * @author Jouwee
 */
public class Application {
    
    /**
     * Main application method
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // Sets up the Look and Feel for the application
        setupLookAndFeel();
        // Build the main application window
        ApplicationWindow window = new ApplicationWindow();
        window.setDefaultCloseOperation(ApplicationWindow.EXIT_ON_CLOSE);
        // Displays the window
        window.setVisible(true);
    }
    
    /**
     * Sets up the Look and Feel for the application
     */
    private static void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
