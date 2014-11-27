package com.jouwee.proto;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Action list view
 * 
 * @author Jouwee
 */
public class ActionListView extends View {

    /** Swing list to show the actions */
    private JList list;
    
    /**
     * Creates a new action list view
     * 
     * @param model
     */
    public ActionListView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupList();
        setupViewAndPlaceComponents();
    }
    
    /**
     * Sets up the list for the actions
     */
    private void setupList() {
        list = new JList(new ActionJListModel());
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                getModel().getState().set("selectedAction", list.getSelectedValue());
            }
        });
    }
    
    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(list);
    }
    
    /**
     * Returns the action list model
     */
    private class ActionJListModel implements ListModel<Action> {

        @Override
        public int getSize() {
            return getModel().getProject().getActionList().getSize();
        }

        @Override
        public Action getElementAt(int index) {
            return getModel().getProject().getActionList().get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            // TODO: Must support?
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            // TODO: Must support?
        }
        
    }
}
