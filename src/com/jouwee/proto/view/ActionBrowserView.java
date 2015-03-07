package com.jouwee.proto.view;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.Model;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.gui.ActionTypeBrowser;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * Action browser view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action browser", controller = ActionBrowserController.class)
public class ActionBrowserView extends View<Model, ActionBrowserController> {

    /** Action browser */
    private ActionTypeBrowser browser;
    /** Add button */
    private JButton addButton;
    
    /**
     * Create a new action browser view
     * 
     * @param model 
     */
    public ActionBrowserView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupSelector();
        setupAddButton();
        setupViewAndPlaceComponents();
    }

    /**
     * Sets up the selector for the actions
     */
    private void setupSelector() {
        browser = new ActionTypeBrowser();
    }
    
    /**
     * Sets up the add action
     */
    private void setupAddButton() {
        addButton = new JButton(new AddAction());
    }

    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(browser);
        add(addButton, BorderLayout.SOUTH);
    }

    @Override
    public void updateModel(Model model) {
        setModel(model);
    }
    
    /**
     * Action for adding actions
     */
    private class AddAction extends AbstractAction {

        /**
         * Create the action
         */
        public AddAction() {
            super("Add");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            getController().addAction(browser.getSelectedActionType());
        }
        
    }

}
