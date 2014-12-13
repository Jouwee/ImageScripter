package com.jouwee.proto.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

/**
 * Multi split pane
 * 
 * @author Jouwee
 */
public class JMultiSplit extends JComponent {

    // TODO: Remove rightmost divider
    // TODO: Keep other dividers unmoved as you resize one
    
    /** Orientation - vertical */
    public static final int VERTICAL = JSplitPane.VERTICAL_SPLIT;
    /** Orientation - horizontal */
    public static final int HORIZONTAL = JSplitPane.HORIZONTAL_SPLIT;
    /** Root split pane */
    private final JSplitPane rootSplit;
    /** Orientation */
    private final int orientation;
    /** Last split pane */
    private JSplitPane lastSplit;
    
    /**
     * Creates a new split panel
     * 
     * @param orientation 
     */
    public JMultiSplit(int orientation) {
        super();
        setLayout(new BorderLayout());
        rootSplit = new JSplitPane(orientation);
        this.orientation = orientation;
        super.add(rootSplit);
    }

    @Override
    public Component add(Component comp) {
        add(comp, (Object)10);
        return comp;
    }

    @Override
    public void add(Component comp, Object constraints) {
        if (lastSplit == null) {
            rootSplit.setLeftComponent(comp);
            rootSplit.setDividerLocation((int) constraints);
            lastSplit = rootSplit;
        } else {
            JSplitPane currentSplit = new JSplitPane(orientation);
            lastSplit.setRightComponent(currentSplit);
            currentSplit.setDividerLocation((int) constraints);
            currentSplit.setLeftComponent(comp);
            lastSplit = currentSplit;
        }
    }
    
}
