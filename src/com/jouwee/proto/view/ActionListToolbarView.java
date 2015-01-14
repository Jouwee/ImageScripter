package com.jouwee.proto.view;

import com.jouwee.proto.ActionList;
import com.jouwee.proto.mvc.ToolbarView;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * Action list toolbar
 * 
 * @author Jouwee
 */
public class ActionListToolbarView extends ToolbarView<ActionList, ActionListView, ActionListController> {

    /**
     * Creates the toolbar
     */
    public ActionListToolbarView() {
        super();
        setLayout(new FlowLayout());
        add(new JButton(new RemoveAction()));
    }
    
    /**
     * Remove action
     */
    private class RemoveAction extends AbstractAction {

        /**
         * Creates the action
         */
        public RemoveAction() {
            super("Remove");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getController().removeSelectedAction();
        }
        
    }
    
}
