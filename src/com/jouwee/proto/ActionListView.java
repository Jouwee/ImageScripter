package com.jouwee.proto;

import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Action list view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action list")
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
    private class ActionJListModel implements ListModel<Action>, ListListener {

        /** List data listeners */
        private final List<ListDataListener> listDataListeners;

        /**
         * Creates a new action list model
         */
        @SuppressWarnings("LeakingThisInConstructor")
        public ActionJListModel() {
            listDataListeners = new ArrayList<>();
            getModel().getProject().getActionList().addListListener(this);
        }
        
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
            listDataListeners.add(l);
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            listDataListeners.remove(l);
        }

        @Override
        public void itemAdded(ListEvent e) {
            ListDataEvent ev = new ListDataEvent(e.getSource(), ListDataEvent.INTERVAL_ADDED, e.getIndex(), e.getIndex());
            for (ListDataListener listener : listDataListeners) {
                listener.intervalAdded(ev);
            }
        }
        
    }
}
