package com.jouwee.proto;

import com.jouwee.proto.annotations.ViewMeta;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * Action browser view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action browser")
public class ActionBrowserView extends View {

    /** Swing list to show the actions */
    private JList<Class> list;
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
        setupList();
        setupAddButton();
        setupViewAndPlaceComponents();
    }

    /**
     * Sets up the list for the actions
     */
    private void setupList() {
        list = new JList(ActionProvider.getAvailableActions().toArray());
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
        add(list);
        add(addButton, BorderLayout.SOUTH);
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
            try {
                getModel().getProject().getActionList().add(ActionProvider.getNewInstance(list.getSelectedValue()));
            } catch(IllegalAccessException | InstantiationException ex) {
                // TODO: Default error handling
            }
        }
        
    }

}
