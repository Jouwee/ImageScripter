package com.jouwee.proto;

import com.jouwee.proto.actions.NewProjectAction;
import com.jouwee.proto.actions.OpenInputAction;
import com.jouwee.proto.actions.OpenProjectAction;
import com.jouwee.proto.actions.SaveProjectAction;
import com.jouwee.proto.gui.ViewPanelContainer;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
        setJMenuBar(setupMenubar());
    }
    
    /**
     * Sets up the menu bar
     * 
     * @return JMenuBar
     */
    private JMenuBar setupMenubar() {
        JMenuBar bar = new JMenuBar();
        bar.add(setupProjectMenu());
        bar.add(setupInputMenu());
        return bar;
    }
    
    /**
     * Sets up the project menu
     * 
     * @return JMenu
     */
    private JMenu setupProjectMenu() {
        JMenu menu = new JMenu(LocaleBundle.def().getString("menu.project"));
        menu.add(new NewProjectAction());
        menu.add(new OpenProjectAction());
        menu.add(new SaveProjectAction());
        return menu;
    }
    
    /**
     * Sets up the input menu
     * 
     * @return JMenu
     */
    private JMenu setupInputMenu() {
        JMenu menu = new JMenu(LocaleBundle.def().getString("menu.input"));
        menu.add(new OpenInputAction());
        return menu;
    }
    
}
