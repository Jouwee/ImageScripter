package com.jouwee.proto.view;

import com.jouwee.proto.LocaleBundle;
import com.jouwee.proto.annotations.ViewMeta;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;

/**
 * Component for selecting views
 *
 * @author Jouwee
 */
public class ViewSelector extends JComboBox<Class<? extends View>> {

    /**
     * Create a view selector
     */
    public ViewSelector() {
        super(new ViewSeletorModel());
        setRenderer(new ViewSelectorRenderer());
    }

    /**
     * Rendered for the view types
     */
    private static class ViewSelectorRenderer implements ListCellRenderer<Class> {

        /** Selected color */
        private static final Color SELECTED = new Color(0x4D77B3);
        
        @Override
        public Component getListCellRendererComponent(JList<? extends Class> list, Class value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = new JLabel();
            if (value != null) {
                ViewMeta meta = (ViewMeta) value.getAnnotation(ViewMeta.class);
                label.setText(meta.name());
                
                if (!meta.icon().isEmpty()) {
                   label.setIcon(new ImageIcon(ViewSelector.class.getResource("/com/jouwee/proto/icons/" + meta.icon()))); 
                } else {
                    if (ViewSelector.class.getResource("/com/jouwee/proto/icons/" + value.getSimpleName() + ".PNG") != null) {
                        label.setIcon(new ImageIcon(ViewSelector.class.getResource("/com/jouwee/proto/icons/" + value.getSimpleName() + ".PNG"))); 
                    }
                }
            }
            label.setOpaque(true);
            if (isSelected) {
                label.setBackground(SELECTED);
            }
            return label;
        }
    }

    /**
     * Combobox model for the view selector
     */
    private static class ViewSeletorModel implements ComboBoxModel<Class<? extends View>> {

        /**
         * Current view type
         */
        private Class currentViewType;

        @Override
        public void setSelectedItem(Object anItem) {
            if (!(anItem instanceof Class)) {
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
