package com.jouwee.proto;

import com.jouwee.proto.annotations.ViewMeta;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * View to show and edit properties
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Property Editor")
public class PropertiesView extends View implements PropertyChangeListener {

    /**
     * Creates a properties view
     * 
     * @param model 
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public PropertiesView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        rebuildFromScratch();
        getModel().getState().addPropertyChangeListener("selectedAction", this);
    }
    
    /**
     * Rebuild the property view from scratch
     */
    private void rebuildFromScratch() {
        clearOldComponents();
        if (getCurrentAction() == null) {
            setupUndefinedActionPanel();
        } else {
            setupPanelForCurrentAction();
        }
        revalidate();
        repaint();
    }
    
    /**
     * Remove old components from the view
     */
    private void clearOldComponents() {
        removeAll();
    }
    
    /**
     * Sets up the panel for undefined actions
     */
    private void setupUndefinedActionPanel() {
        setLayout(new BorderLayout());
        JLabel emptyLabel = new JLabel(LocaleBundle.def().getString("propertyView.undefinedAction"));
        add(emptyLabel);
    }
    
    /**
     * Sets up the panel for the current action
     */
    private void setupPanelForCurrentAction() {

        // TODO: Temporary setup
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String fieldName : PropertyDictionary.def().getPropertyNames(getCurrentAction())) {
            try {
                Field f = getCurrentAction().getClass().getDeclaredField(fieldName);
            
                final PropertyDescriptor desc = new PropertyDescriptor(fieldName, getCurrentAction().getClass());
                
                if (f.getType().equals(int.class)) {
                    final JTextField fi = new JTextField();
                    fi.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            update();
                        }
                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            update();
                        }
                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            update();
                        }
                        private void update() {
                            try {
                                desc.getWriteMethod().invoke(getCurrentAction(), Integer.parseInt(fi.getText()));
                                getModel().getScriptRunner().run();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    add(fi);
                }
            } catch(Exception e) { e.printStackTrace();}
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        rebuildFromScratch();
    }

}
