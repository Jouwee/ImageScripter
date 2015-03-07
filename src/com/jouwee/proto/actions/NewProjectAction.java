package com.jouwee.proto.actions;

import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * New project action
 *
 * @author Jouwee
 */
public class NewProjectAction extends AbstractProjectAction {

    /**
     * Creates a new action
     */
    public NewProjectAction() {
        super(LocaleBundle.def().getString("menu.project.new"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application.getController().newPoject();
    }

}
