package com.jouwee.proto;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * Main application window
 * 
 * @author Jouwee
 */
public class ApplicationWindow extends JFrame {

    /**
     * Creates a new main Application Window
     * 
     * @throws HeadlessException 
     */
    public ApplicationWindow() {
        super();
        // Inits the graphical user interface
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupWindow();
        // Sets up a few view panels
        ViewPanel pLeft = new ViewPanel();
        ViewPanel pCenter = new ViewPanel();
        ViewPanel pRight = new ViewPanel();
        // Build the interface
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pLeft, BorderLayout.WEST);
        getContentPane().add(pCenter, BorderLayout.CENTER);
        getContentPane().add(pRight, BorderLayout.EAST);
    }
    
    /**
     * Sets up the window properties
     */
    private void setupWindow() {
        setTitle(LocaleBundle.def().getString("mainwindow.title"));
        setExtendedState(MAXIMIZED_BOTH);
        setSize(800, 600);
    }
    
}
