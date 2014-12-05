package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Open project action
 * 
 * @author Jouwee
 */
public class OpenProjectAction extends AbstractAction {

    /**
     * Creates a new action
     */
    public OpenProjectAction() {
        super(LocaleBundle.def().getString("menu.project.open"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Application.getController().open();
    }
    
}
