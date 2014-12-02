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
        // Build the interface
        getContentPane().setLayout(new BorderLayout());
        
        ViewPanelContainer container = new ViewPanelContainer();
        container.loadFrom(Application.class.getResourceAsStream("layout_default.xml"));
        getContentPane().add(container);
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
