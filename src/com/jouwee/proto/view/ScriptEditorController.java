package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.gui.CallbackEditor;
import com.jouwee.proto.mvc.Controller;

/**
 * Script editor controller
 *
 * @author Jouwee
 */
public class ScriptEditorController extends Controller<Action, ScriptEditorView> {

    /**
     * Save the callbacks and run
     */
    public void saveAndRun() {
        for (CallbackEditor editor : getView().getActiveEditors()) {
            editor.getCallback().setBody(editor.getText());
        }
        Application.getModel().getScriptRunner().runInBackground();
    }

}
