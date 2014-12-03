package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.Model;
import com.jouwee.proto.Scriptable;
import com.jouwee.proto.annotations.ViewMeta;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * View for editing scripts
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Script editor")
public class ScriptEditorView extends View implements PropertyChangeListener {

    /** Fonte default para o editor */
    private static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 12);
    /** Editor */
    private JTextArea editor;
    
    /**
     * Creates a new script editing view
     * 
     * @param model 
     */
    public ScriptEditorView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupList();
        setupViewAndPlaceComponents();
        getModel().getState().addPropertyChangeListener("selectedAction", this);
    }
    
    /**
     * Sets up the list for the actions
     */
    private void setupList() {
        editor = new JTextArea();
        editor.setFont(DEFAULT_FONT);
    }
    
    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(editor);
        add(new JButton(new ActionSaveAndRun()), BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (getCurrentAction() instanceof Scriptable) {
            Scriptable scriptable = (Scriptable) getCurrentAction();
            editor.setText(scriptable.getCallbackList().get(0).getBody());
        }
    }

    /**
     * Returns the current selected action
     * 
     * @return Action
     */
    private Action getCurrentAction() {
        return (Action) getModel().getState().get("selectedAction");
    }
    
    /**
     * Action for the save and run button
     */
    private class ActionSaveAndRun extends AbstractAction {

        /**
         * Creates the action
         */
        public ActionSaveAndRun() {
            super("Save and run");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getCurrentAction() instanceof Scriptable) {
                Scriptable scriptable = (Scriptable) getCurrentAction();
                scriptable.getCallbackList().get(0).setBody(editor.getText());
                getModel().getScriptRunner().run();
            }
        }
    
    }

}
