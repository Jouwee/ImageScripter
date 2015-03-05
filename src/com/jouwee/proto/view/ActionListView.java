package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.ActionList;
import com.jouwee.proto.Application;
import com.jouwee.proto.Interface;
import com.jouwee.proto.Model;
import com.jouwee.proto.annotations.ActionMeta;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.gui.ComponentFactory;
import com.jouwee.proto.gui.Pill;
import com.jouwee.proto.gui.PillModel;
import com.jouwee.proto.gui.PillRenderer;
import com.jouwee.proto.gui.PillSelectionEvent;
import com.jouwee.proto.gui.PillSelectionListener;
import com.jouwee.proto.listeners.ListEvent;
import com.jouwee.proto.listeners.ListListener;
import com.jouwee.proto.mvc.View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Action list view
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Action list", controller = ActionListController.class, toolbar = ActionListToolbarView.class)
public class ActionListView extends View<ActionList, ActionListController> implements Interface {

    /** Action list */
    private Pill<Action> actions;
    
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
        setupPills();
        setupViewAndPlaceComponents();
    }
    
    /**
     * Sets up the pills for the actions
     */
    private void setupPills() {
        actions = new Pill<>(new ActionPillModel());
        actions.setRenderer(new ActionCellRendererFactory());
        actions.addPillSelectionListener(new PillSelectionListener() {
            @Override
            public void valueChanged(PillSelectionEvent e) {
                Application.getModel().getState().set("selectedAction", actions.getSelectedValue());
            }
        });
    }
    
    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(ComponentFactory.scrollPane(actions));
    }

    @Override
    public void updateModel(Model model) {
        setModel(model.getProject().getActionList());
        if (actions != null) {
            actions.setModel(new ActionPillModel());
        }
    }

    /**
     * Returns the selected action
     * 
     * @return Action
     */
    public Action getSelectedAction() {
        return actions.getSelectedValue();
    }
    
    /**
     * Sets the selected action
     * 
     * @param action 
     */
    public void setSelectedAction(Action action) {
        actions.setSelectedValue(action);
    }
    
    /**
     * List cell renderer for actions
     */
    private class ActionCellRendererFactory implements PillRenderer<Action> {

        @Override
        public JComponent getComponent(Action value) {
            return new ActionCellRenderer(value);
        }

    }
    
    /**
     * List cell renderer for actions
     */
    private class ActionCellRenderer extends JComponent implements ActionListener, PropertyChangeListener {

        /** Altura */
        private static final int RENDERER_HEIGHT = 60;
        /** If the action is enabled */
        private final JCheckBox enabled;
        /** Action */
        private final Action action;
        
        /**
         * New renderer of the action
         * 
         * @param value 
         */
        @SuppressWarnings("LeakingThisInConstructor")
        private ActionCellRenderer(Action value) {
            action = value;
            action.addPropertyChangeListener(this);
            setLayout(new BorderLayout());
            add(new JLabel(value.getClass().getAnnotation(ActionMeta.class).name()));
            enabled = new JCheckBox();
            enabled.setSelected(action.isEnabled());
            enabled.setOpaque(false);
            enabled.setFocusable(false);
            enabled.addActionListener(this);
            setMinimumSize(new Dimension(0, RENDERER_HEIGHT));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, RENDERER_HEIGHT));
            setPreferredSize(new Dimension(Integer.MAX_VALUE, RENDERER_HEIGHT));
            add(enabled, BorderLayout.WEST);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            action.setEnabled(enabled.isSelected());
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            enabled.setSelected(action.isEnabled());
        }

    }
    
    /**
     * Returns the action pill model
     */
    private class ActionPillModel implements PillModel<Action>, ListListener {

        /** List data listeners */
        private final List<ListDataListener> listDataListeners;

        /**
         * Creates a new action list model
         */
        @SuppressWarnings("LeakingThisInConstructor")
        public ActionPillModel() {
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
            ListDataEvent ev = new ListDataEvent(e.getSource(), ListDataEvent.INTERVAL_REMOVED, e.getIndex(), e.getIndex());
            for (ListDataListener listener : listDataListeners) {
                listener.intervalRemoved(ev);
            }
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
