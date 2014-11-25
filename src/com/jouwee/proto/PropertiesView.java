package com.jouwee.proto;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

/**
 * View to show and edit properties
 * 
 * @author Jouwee
 */
public class PropertiesView extends View {

    // TODO: Testing code
    public PropertiesView() {
        super();
        setBackground(Color.red);
        setOpaque(true);
        
        setLayout(new BorderLayout());
        add(new JButton("hi"));
        
    }

}
