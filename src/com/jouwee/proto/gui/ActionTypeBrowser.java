/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto.gui;

import com.jouwee.proto.Action;
import com.jouwee.proto.ActionTypeFilter;
import com.jouwee.proto.Functionality;
import com.jouwee.proto.annotations.ActionMeta;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Action type browser
 *
 * @author Jouwee
 */
public class ActionTypeBrowser extends JComponent {

    /** Functionality selector */
    private JList<Functionality> functionalityList;
    /** Text filter */
    private JTextField textFilter;
    /** Selector */
    private ActionTypeSelector selector;

    /**
     * New action browser
     */
    public ActionTypeBrowser() {
        super();
        initGui();
    }

    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        setupSelector();
        setupFunctionalityList();
        setupTextFilter();
        add(textFilter, BorderLayout.NORTH);
        add(functionalityList, BorderLayout.WEST);
        add(selector);
        updateFilter();
    }

    /**
     * Set up the functionality list
     */
    private void setupFunctionalityList() {
        functionalityList = new JList<>(Functionality.values());
        functionalityList.setSelectedIndex(0);
        functionalityList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateFilter();
            }
        });
    }

    /**
     * Set up the text filter
     */
    private void setupTextFilter() {
        textFilter = new JTextField();
        textFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }
        });
    }

    /**
     * Set up the selector
     */
    private void setupSelector() {
        selector = new ActionTypeSelector();
    }

    /**
     * Updates the filter
     */
    private void updateFilter() {
        selector.setFilter(new Filter());
    }

    /**
     * Returns the currently selected action type
     *
     * @return Class
     */
    public Class<? extends Action> getSelectedActionType() {
        return selector.getSelectedActionType();
    }

    /**
     * Generic filter
     */
    public class Filter implements ActionTypeFilter {

        @Override
        public boolean accept(Class<? extends Action> actionType) {
            ActionMeta meta = actionType.getAnnotation(ActionMeta.class);
            if (functionalityList.getSelectedValue() != Functionality.ALL && 
                !meta.functionality().equals(functionalityList.getSelectedValue())) {
                return false;
            }
            if (!meta.name().toLowerCase().matches(".*" + textFilter.getText().toLowerCase() + ".*")) {
                return false;
            }
            return true;
        }

    }

}
