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
        add(new JButton(new MoveUpAction()));
        add(new JButton(new MoveDownAction()));
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
    
    /**
     * Move Up action
     */
    private class MoveUpAction extends AbstractAction {

        /**
         * Creates the action
         */
        public MoveUpAction() {
            super("Up");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getController().moveSelectionUp();
        }
        
    }
    
    /**
     * MoveDown action
     */
    private class MoveDownAction extends AbstractAction {

        /**
         * Creates the action
         */
        public MoveDownAction() {
            super("Down");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getController().moveSelectionDown();
        }
        
    }
    
}
