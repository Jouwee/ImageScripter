package com.jouwee.proto.view;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.Model;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.gui.CallbackEditor;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * View for editing scripts
 *
 * @author Jouwee
 */
@ViewMeta(name = "Script editor", controller = ScriptEditorController.class)
public class ScriptEditorView extends View<Action, ScriptEditorController> implements PropertyChangeListener {

    /** Fonte default para o editor */
    private static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 12);
    /** Editor */
    private JPanel editor;
    /** All active editors */
    private final List<CallbackEditor> activeEditors;

    /**
     * Creates a new script editing view
     *
     * @param model
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScriptEditorView(Model model) {
        super(model);
        activeEditors = new ArrayList<>();
        initGui();
        Application.getModel().getState().addPropertyChangeListener("selectedAction", this);
    }

    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupList();
        setupViewAndPlaceComponents();
    }

    /**
     * Sets up the list for the actions
     */
    private void setupList() {
        editor = new JPanel();
        editor.setLayout(new BoxLayout(editor, BoxLayout.Y_AXIS));
        buildList();
    }

    /**
     * Build or rebuild the action list
     */
    private void buildList() {
        editor.removeAll();
        activeEditors.clear();
        if (getModel() != null) {
            for (String key : getModel().getCallbackKeys()) {
                CallbackEditor callbackEditor = new CallbackEditor(getModel().getCallbackHeader(key), getModel().getCallback(key));
                editor.add(callbackEditor);
                activeEditors.add(callbackEditor);
            }
        }
    }

    /**
     * Sets up the view and place the components. The child components should've
     * been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(editor);
        add(new JButton(new ActionSaveAndRun()), BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateModel(Application.getModel());
        buildList();
    }

    @Override
    public void updateModel(Model model) {
        setModel((Action) model.getState().get("selectedAction"));
    }

    /**
     * Returns the active editors
     * 
     * @return CallbackEditor
     */
    public List<CallbackEditor> getActiveEditors() {
        return activeEditors;
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
            getController().saveAndRun();
        }

    }

}
