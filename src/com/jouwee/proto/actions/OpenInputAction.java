package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Open input action
 * 
 * @author Jouwee
 */
public class OpenInputAction extends AbstractAction {

    /**
     * Creates a new action
     */
    public OpenInputAction() {
        super(LocaleBundle.def().getString("menu.input.open"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Application.getController().selectInput();
    }
    
}