package com.jouwee.proto.view;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.Action;
import com.jouwee.proto.Application;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.LocaleBundle;
import com.jouwee.proto.Model;
import com.jouwee.proto.PropertyDictionary;
import com.jouwee.proto.annotations.Property;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.properties.PropertyEditorFactory;
import java.awt.BorderLayout;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View to show and edit properties
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Property Editor", controller = PropertiesController.class)
public class PropertiesView extends View<Action, PropertiesController> implements PropertyChangeListener {

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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String fieldName : PropertyDictionary.def().getPropertyNames(getModel())) {
            try {
                add(setupComponentEditorContainer(fieldName));
            } catch(IntrospectionException | NoSuchFieldException e) {
                ExceptionHandler.handle(e);
            }
        }
        add(Box.createVerticalGlue());
    }
    
    /**
     * Setup the component editor container
     * 
     * @param fieldName
     * @return JComponent
     * @throws NoSuchFieldException
     * @throws IntrospectionException 
     */
    private JComponent setupComponentEditorContainer(String fieldName) throws NoSuchFieldException, IntrospectionException {
        Field field = getModel().getClass().getDeclaredField(fieldName);
        Property property = field.getAnnotation(Property.class);
        JComponent editor = PropertyEditorFactory.getEditor(getModel(), field);
        return setupContainer(property, editor);
    }
    
    /**
     * Sets up the container for the editor
     * 
     * @param propertyMeta
     * @param editor
     * @return JComponent
     */
    private JComponent setupContainer(Property propertyMeta, JComponent editor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(propertyMeta.name()));
        panel.add(editor);
        return panel;
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
