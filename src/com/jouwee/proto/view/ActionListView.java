package com.jouwee.proto.view;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.Action;
import com.jouwee.proto.ActionList;
import com.jouwee.proto.Application;
import com.jouwee.proto.Interface;
import com.jouwee.proto.Model;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultListCellRenderer;

/**
 * Action list view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action list", controller = ActionListController.class)
public class ActionListView extends View<ActionList, ActionListController> implements Interface {

    /** Preferred action item size */
    private static final Dimension PREFERRED_ACTION_SIZE = new Dimension(150, 50);
    /** Swing list to show the actions */
    private JList list;
    
    /**
     * Creates a new action list view
     * 
     * @param model
     */
    @SuppressWarnings("LeakingThisInConstructor")
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
        list.setCellRenderer(new ActionCellRenderer());
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Application.getModel().getState().set("selectedAction", list.getSelectedValue());
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

    @Override
    public void updateModel(Model model) {
        setModel(model.getProject().getActionList());
        if (list != null) {
            list.setModel(new ActionJListModel());
        }
    }

    /**
     * List cell renderer for actions
     */
    private class ActionCellRenderer extends SubstanceDefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                // If there is metadata
                if (value.getClass().isAnnotationPresent(ActionMeta.class)) {
                    ActionMeta meta = value.getClass().getAnnotation(ActionMeta.class);
                    label.setText(meta.name());
                }
                label.setPreferredSize(PREFERRED_ACTION_SIZE);
            }
            return component;
        }

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
            getModel().addListListener(this);
        }
        
        @Override
        public int getSize() {
            return getModel().getSize();
        }

        @Override
        public Action getElementAt(int index) {
            return getModel().get(index);
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

        @Override
        public void itemRemoved(ListEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void listCleared(ListEvent e) {
            ActionList source = (ActionList) e.getSource();
            ListDataEvent ev = new ListDataEvent(e.getSource(), ListDataEvent.CONTENTS_CHANGED, 0, source.getSize());
            for (ListDataListener listener : listDataListeners) {
                listener.intervalRemoved(ev);
            }
        }
        
    }
}
