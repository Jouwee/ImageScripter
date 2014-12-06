package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.ActionList;
import com.jouwee.proto.Application;
import com.jouwee.proto.Interface;
import static com.jouwee.proto.Interface.LOWLIGHT_COLOR;
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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
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
public class ActionListView extends View<ActionList> implements Interface {

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
    }

    /**
     * List cell renderer for actions
     */
    private class ActionCellRenderer implements ListCellRenderer<Action> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Action> list, Action value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel l = new ActionListCell();
            if (isSelected) {
                l.setOpaque(true);
                l.setBackground(SELECTION_COLOR);
            }
            
            if (value.getClass().isAnnotationPresent(ActionMeta.class)) {
                ActionMeta meta = value.getClass().getAnnotation(ActionMeta.class);
                l.setText(meta.name());
            }
            
            return l;
        }

    }
    
    /**
     * Action list cell
     */
    private class ActionListCell extends JLabel {

        /**
         * Create a new action list cell
         */
        public ActionListCell() {
            super();
            setPreferredSize(new Dimension(20, 50));
            setBorder(BorderFactory.createLineBorder(LOWLIGHT_COLOR));
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
