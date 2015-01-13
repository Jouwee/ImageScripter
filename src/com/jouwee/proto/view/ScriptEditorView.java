package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.Callback;
import com.jouwee.proto.CallbackHeader;
import com.jouwee.proto.Model;
import com.jouwee.proto.ScriptProvider;
import com.jouwee.proto.annotations.ViewMeta;
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
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * View for editing scripts
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Script editor")
public class ScriptEditorView extends View<Action> implements PropertyChangeListener {

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
     * Sets up the view and place the components. The child components should've been previously set up.
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
            for (CallbackEditor editor : activeEditors) {
                editor.getCallback().setBody(editor.getText());
            }
            Application.getModel().getScriptRunner().run();
        }
    
    }
    
    /**
     * Callback editor
     */
    private class CallbackEditor extends JComponent {
        
        /** Callback header */
        private final CallbackHeader header;
        /** Callback */
        private final Callback callback;
        /** Actual text editor */
        private final JTextArea textEditor;
        
        /**
         * New callback editor
         * 
         * @param header
         * @param callback 
         */
        public CallbackEditor(CallbackHeader header, Callback callback) {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.header = header;
            this.callback = callback;
            textEditor = new JTextArea();
            textEditor.setFont(DEFAULT_FONT);
            textEditor.setText(callback.getBody());
            JTextArea headerLabel = new JTextArea(ScriptProvider.def().getHeader(header));
            headerLabel.setFont(DEFAULT_FONT);
            headerLabel.setEnabled(false);
            add(headerLabel);
            add(textEditor);
            JTextArea footerLabel = new JTextArea("}");
            footerLabel.setFont(DEFAULT_FONT);
            footerLabel.setEnabled(false);
            add(footerLabel);
        }
        
        /**
         * Get active callback
         * 
         * @return Callback
         */
        public Callback getCallback() {
            return callback;
        }
        
        /**
         * Returns the edited text
         * 
         * @return String
         */
        public String getText() {
            return textEditor.getText();
        }
        
    }

}
