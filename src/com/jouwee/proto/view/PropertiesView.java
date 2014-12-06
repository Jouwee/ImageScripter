package com.jouwee.proto.view;

import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.LocaleBundle;
import com.jouwee.proto.Model;
import com.jouwee.proto.PropertyDictionary;
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
public class PropertiesView extends View<Action> implements PropertyChangeListener {

    /**
     * Creates a properties view
     * 
     * @param model 
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public PropertiesView(Model model) {
        super(model);
        initGui();
        Application.getModel().getState().addPropertyChangeListener("selectedAction", this);
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        rebuildFromScratch();
    }
    
    /**
     * Rebuild the property view from scratch
     */
    private void rebuildFromScratch() {
        clearOldComponents();
        if (getModel() == null) {
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
        for (String fieldName : PropertyDictionary.def().getPropertyNames(getModel())) {
            try {
                Field f = getModel().getClass().getDeclaredField(fieldName);
            
                final PropertyDescriptor desc = new PropertyDescriptor(fieldName, getModel().getClass());
                
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
                                desc.getWriteMethod().invoke(getModel(), Integer.parseInt(fi.getText()));
                                Application.getModel().getScriptRunner().run();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateModel(Application.getModel());
        rebuildFromScratch();
    }

    @Override
    public void updateModel(Model model) {
        setModel((Action) model.getState().get("selectedAction"));
    }

}
