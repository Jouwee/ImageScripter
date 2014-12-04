package com.jouwee.proto.actions;

import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * New project action
 * 
 * @author Jouwee
 */
public class NewProjectAction extends AbstractAction {

    /**
     * Creates a new action
     */
    public NewProjectAction() {
        super(LocaleBundle.def().getString("menu.project.new"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
