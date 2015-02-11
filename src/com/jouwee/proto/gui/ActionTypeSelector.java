/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.Action;
import com.jouwee.proto.ActionProvider;
import com.jouwee.proto.ActionTypeFilter;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Action type selector
 * 
 * @author Jouwee
 */
public class ActionTypeSelector extends JComponent {

    /** All the action types that the user can browse through */
    private final List<Class> actionTypes;
    /** Pill group */
    private Pill<Class> group;
    /** Filter */
    private ActionTypeFilter filter;
    
    /**
     * New action type selector with all the available action types
     */
    public ActionTypeSelector() {
        this(ActionProvider.getAvailableActions());
    }
    
    /**
     * New action type selector with a set of actions to browse from
     * 
     * @param actionTypes 
     */
    public ActionTypeSelector(List<Class> actionTypes) {
        this.actionTypes = new ArrayList<>(actionTypes);
        this.filter = ActionTypeFilter.EMPTY_FILTER;
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        updatePills();
    }
    
    /**
     * Update the pills to select from
     */
    private void updatePills() {
        group = new Pill();
        group.setRenderer(new ActionPillRenderer());
        PillListModel m = (PillListModel) group.getModel();
        for (Class actionType : actionTypes) {
            if (filter.accept(actionType)) {
                m.add(actionType);
            }
        }
        removeAll();
        JScrollPane scroll = new JScrollPane(group);
        add(scroll);
        revalidate();
    }
    
    /**
     * Returns the currently selected action type
     * 
     * @return Class
     */
    public Class<? extends Action> getSelectedActionType() {
        return group.getSelectedValue();
    }

    /**
     * Returns the current filter
     * 
     * @return ActionTypeFilter
     */
    public ActionTypeFilter getFilter() {
        return filter;
    }

    /**
     * Sets the new filter
     * 
     * @param filter ActionTypeFilter
     */
    public void setFilter(ActionTypeFilter filter) {
        this.filter = filter;
        updatePills();
    }
    
}
