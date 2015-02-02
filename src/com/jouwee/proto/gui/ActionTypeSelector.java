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
import java.awt.GridLayout;
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

    /** Ideal number of columns */
    private final int COLS = 3;
    /** All the action types that the user can browse through */
    private final List<Class> actionTypes;
    /** Inner panel, added to the scroll pane */
    private JPanel innerPanel;
    /** Pill group */
    private PillGroup<Class> group;
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
        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(actionTypes.size() / COLS, COLS));
        updatePills();
        JScrollPane scroll = new JScrollPane(innerPanel);
        add(scroll);
    }
    
    /**
     * Update the pills to select from
     */
    private void updatePills() {
        innerPanel.removeAll();
        group = new PillGroup();
        for (Class actionType : actionTypes) {
            if (filter.accept(actionType)) {
                Pill p = new Pill(group, actionType);
                p.setRenderer(new ActionPillRenderer());
                innerPanel.add(p);
            }
        }
        revalidate();
    }
    
    /**
     * Returns the currently selected action type
     * 
     * @return Class
     */
    public Class<? extends Action> getSelectedActionType() {
        return group.getSelected().getValue();
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
