package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;

/**
 * Save project action
 * 
 * @author Jouwee
 */
public class SaveProjectAction extends AbstractProjectAction {

    /**
     * Creates a new action
     */
    public SaveProjectAction() {
        super(LocaleBundle.def().getString("menu.project.save"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Application.getController().quickSave();
    }
    
}
