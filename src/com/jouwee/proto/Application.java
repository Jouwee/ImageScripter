package com.jouwee.proto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

    /** Property name - Model */
    public static final String PROP_MODEL = "PROP_MODEL";
    /** Current model for the application */
    private static Model model;
    /** User preferences */
    private static Preferences preferences;
    /** Application controller */
    private static ApplicationController controller;
    /** Property change support */
    private static transient PropertyChangeSupport propertyChangeSupport;

    /**
     * Main application method
     *
     * @param args
     */
    public static void main(String[] args) throws AWTException, InterruptedException {
        
        Robot r = new Robot();
        r.delay(3000);
        r.mouseMove(930, 500);
        for (int i = 0; i < 100; i++) {
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(20);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            r.delay(20);
        }
        
        
        if(true) return;
        controller = new ApplicationController();
        propertyChangeSupport = new PropertyChangeSupport(controller);
        // Load the user preferences
        preferences = controller.loadPreferences();
        // Creates the model
        setModel(new Model());
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
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called
     * as many times as it is added.
     * If <code>listener</code> is null, no exception is thrown and no action
     * is taken.
     *
     * @param listener The PropertyChangeListener to be added
     */
    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     * If <code>listener</code> was added more than once to the same event
     * source, it will be notified one less time after being removed.
     * If <code>listener</code> is null, or was never added, no exception is
     * thrown and no action is taken.
     *
     * @param listener The PropertyChangeListener to be removed
     */
    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Add a PropertyChangeListener for a specific property.  The listener
     * will be invoked only when a call on firePropertyChange names that
     * specific property.
     * The same listener object may be added more than once.  For each
     * property,  the listener will be invoked the number of times it was added
     * for that property.
     * If <code>propertyName</code> or <code>listener</code> is null, no
     * exception is thrown and no action is taken.
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The PropertyChangeListener to be added
     */
    public static void addPropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     * If <code>listener</code> was added more than once to the same event
     * source for the specified property, it will be notified one less time
     * after being removed.
     * If <code>propertyName</code> is null,  no exception is thrown and no
     * action is taken.
     * If <code>listener</code> is null, or was never added for the specified
     * property, no exception is thrown and no action is taken.
     *
     * @param propertyName The name of the property that was listened on.
     * @param listener The PropertyChangeListener to be removed
     */
    public static void removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
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
        Model oldModel = Application.model;
        Application.model = model;
        propertyChangeSupport.firePropertyChange(PROP_MODEL, oldModel, Application.model);
    }

    /**
     * Return the application controller
     *
     * @return ApplicationController
     */
    public static ApplicationController getController() {
        return controller;
    }

    /**
     * Return the user's preferences
     * 
     * @return Preferences
     */
    public static Preferences getPreferences() {
        return preferences;
    }

}
