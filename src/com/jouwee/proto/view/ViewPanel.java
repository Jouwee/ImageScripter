package com.jouwee.proto.view;

import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.Interface;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * The container panel for views
 * 
 * @author Jouwee
 */
public class ViewPanel extends JPanel implements Interface {
    
    /** Property - Current view */
    public static final String PROP_VIEW = "PROP_VIEW";
    /** Property change support */
    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    /** View selector */
    private ViewSelector viewSelector;
    /** Current view for this panel */
    private View view;
    
    /**
     * Creates a new View Panel
     */
    public ViewPanel() {
        super();
        // Initializes the graphics user interface
        initGui();
    }
    
    /**
     * Initializes the graphics user interface
     */
    private void initGui() {
        setupViewSelector();
        JPanel pViewInfo = setupViewInfoPanel();
        setupViewPanel(pViewInfo);
    }

    /**
     * Sets up the view selector
     */
    private void setupViewSelector() {
        viewSelector = new ViewSelector();
        viewSelector.addItemListener(new ViewSeletorListener());
    }

    /**
     * Sets up the view info panel
     * 
     * @return 
     */
    private JPanel setupViewInfoPanel() {
        JPanel pViewInfo = new JPanel();
        pViewInfo.setLayout(new BoxLayout(pViewInfo, BoxLayout.X_AXIS));
        pViewInfo.add(viewSelector);
        return pViewInfo;
    }

    /**
     * Sets up the view panel
     * 
     * @param pViewInfo 
     */
    private void setupViewPanel(JPanel pViewInfo) {
        setLayout(new BorderLayout());
        add(pViewInfo, BorderLayout.SOUTH);
    }
    
    /**
     * Updates the view contained in the panel to represent the current defined view
     * 
     * @param oldView The old view to be removed
     * @param newView The new view to be added
     */
    private void updateView(View oldView, View newView) {
        if(oldView != null) {
            remove(oldView);
        }
        add(newView, BorderLayout.CENTER);
        viewSelector.setSelectedItem(newView.getClass());
        revalidate();        
        repaint();        
    }

    /**
     * Returns the current view
     * 
     * @return View
     */
    public View getView() {
        return view;
    }

    /**
     * Sets the current view
     * 
     * @param view 
     */
    public void setView(View view) {
        View oldView = this.view;
        this.view = view;
        propertyChangeSupport.firePropertyChange(PROP_VIEW, oldView, view);
        // Updates the view contained in the panel
        updateView(oldView, view);
    }
    
    /**
     * View seletor listener
     */
    private class ViewSeletorListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Class viewType = (Class) e.getItem();
            try {
                setView(ViewProvider.getNewInstance(viewType));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
                Map<String, Object> errorInfo = new HashMap<>();
                errorInfo.put("viewType", viewType);
                ExceptionHandler.handle(ex, "Was not able to create a new view", errorInfo);
            }
        }
        
    }
    
}
