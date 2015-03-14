package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;

/**
 * Open project action
 * 
 * @author Jouwee
 */
public class OpenProjectAction extends AbstractAction {

    /** File to open */
    private String file;
    
    /**
     * Open a project
     * 
     * @param file
     */
    public OpenProjectAction(String file) {
        super(file);
        this.file = file;
    }
    
    /**
     * Open a project
     */
    public OpenProjectAction() {
        super(LocaleBundle.def().getString("menu.project.open"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (file == null) {
            Application.getController().open();
        } else {
            Application.getController().open(new File(file));
        }
    }
    
}
