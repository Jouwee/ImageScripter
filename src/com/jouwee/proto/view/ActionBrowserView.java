package com.jouwee.proto.view;

import com.jouwee.proto.ActionProvider;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.Model;
import com.jouwee.proto.annotations.ViewMeta;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * Action browser view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action browser")
public class ActionBrowserView extends View<Model> {

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
            try {
                getModel().getProject().getActionList().add(ActionProvider.getNewInstance(list.getSelectedValue()));
            } catch(IllegalAccessException | InstantiationException ex) {
                Map<String, Object> errorInfo = new HashMap<>();
                errorInfo.put("actionClass", list.getSelectedValue());
                ExceptionHandler.handle(ex, "Was not able to add an action to the list", errorInfo);
            }
        }
        
    }

}
