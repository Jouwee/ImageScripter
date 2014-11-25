package com.jouwee.proto;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;

/**
 * The container panel for views
 * 
 * @author Jouwee
 */
public class ViewPanel extends JPanel {
    
    /** Property - Current view */
    public static final String PROP_VIEW = "PROP_VIEW";
    /** Current view for this panel */
    private View view;
    /** Property change support */
    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
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
        setLayout(new BorderLayout());
        
        JPanel pViewInfo = new JPanel();
        pViewInfo.setLayout(new BoxLayout(pViewInfo, BoxLayout.X_AXIS));
        
        JComboBox<Class<? extends View>> fViewSelector = new JComboBox<>(new ViewSeletorModel());
        fViewSelector.addItemListener(new ViewSeletorListener());
        
        pViewInfo.add(fViewSelector);
        
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
            try {
                Class viewType = (Class) e.getItem();
                setView(ViewProvider.getNewInstance(viewType));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
                // TODO: Default error handling
                ex.printStackTrace();
            }
        }
        
    }
    
    /**
     * Combobox model for the view selector
     */
    private class ViewSeletorModel implements ComboBoxModel<Class<? extends View>> {

        /** Current view type */
        private Class currentViewType;
        
        @Override
        public void setSelectedItem(Object anItem) {
            if(!(anItem instanceof Class)) {
                throw new IllegalStateException(LocaleBundle.def().getString("exceptions.illegalViewType"));
            }
            currentViewType = (Class) anItem;
        }

        @Override
        public Object getSelectedItem() {
            return currentViewType;
        }

        @Override
        public int getSize() {
            return ViewProvider.getAvailableViews().size();
        }

        @Override
        public Class getElementAt(int index) {
            return ViewProvider.getAvailableViews().get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
        }
        
    }
    
}
