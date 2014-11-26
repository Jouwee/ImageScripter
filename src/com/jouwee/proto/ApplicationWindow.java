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
        // TODO: Load from somewhere
        try {
            // Sets up a few view panels
            ViewPanel pLeft = new ViewPanel();
            pLeft.setView(ViewProvider.getNewInstance(ActionListView.class));
            ViewPanel pCenter = new ViewPanel();
            pCenter.setView(ViewProvider.getNewInstance(ImageView.class));
            ViewPanel pRight = new ViewPanel();
            pRight.setView(ViewProvider.getNewInstance(PropertiesView.class));
            // Build the interface
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(pLeft, BorderLayout.WEST);
            getContentPane().add(pCenter, BorderLayout.CENTER);
            getContentPane().add(pRight, BorderLayout.EAST);
        } catch(Exception e) {
            e.printStackTrace();
        }
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
