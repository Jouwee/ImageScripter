package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;

/**
 * Open input action
 * 
 * @author Jouwee
 */
public class OpenInputAction extends AbstractAction {

    /** File to open */
    private String file;
    
    /**
     * Open a input
     * 
     * @param file
     */
    public OpenInputAction(String file) {
        super(file);
        this.file = file;
    }
        
    /**
     * Open a input
     */
    public OpenInputAction() {
        super(LocaleBundle.def().getString("menu.input.open"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (file == null) {
            Application.getController().selectInput();
        } else {
            Application.getController().openInput(new File[] {new File(file)});
        }
    }
    
}