/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.Image;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Image Viewer
 * 
 * @author Jouwee
 */
public class ImageViewer extends JComponent implements PropertyChangeListener {
    
    /** Property name - Image */
    public static final String PROP_IMAGE = "PROP_IMAGE";
    /** Scroll pane */
    private JScrollPane scroll;
    /** Image rendering */
    private JLabel imageRendering;
    /** Image currently being shown */
    private Image image;
    /** Property Change Support */
    private final transient PropertyChangeSupport propertyChangeSupport;
    
    /**
     * New image viewer
     */
    public ImageViewer() {
        super();
        propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(this);
        // Initializes the graphics user interface
        initGui();
    }
    
    /**
     * Initializes the graphics user interface
     */
    private void initGui() {
        setupScrollPane();
        setupImageRendering();
        setLayout(new BorderLayout());
        scroll.setViewportView(imageRendering);
        add(scroll);
    }

    /**
     * Sets up the image rendering
     */
    private void setupImageRendering() {
        imageRendering = new JLabel();
        updateImageRendering();
    }

    /**
     * Sets up the scroll pane
     */
    private void setupScrollPane() {
        scroll = ComponentFactory.scrollPane();
    }
    
    /**
     * Updates the image rendering
     */
    private void updateImageRendering() {
        if (imageRendering == null || image == null) {
            return;
        }
        imageRendering.setIcon(new ImageIcon(image.getBufferedImage()));
    }

    /**
     * Returns the image
     * 
     * @return Image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image
     * 
     * @param image
     */
    public void setImage(Image image) {
        Image oldImage = this.image;
        this.image = image;
        propertyChangeSupport.firePropertyChange(PROP_IMAGE, oldImage, image);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateImageRendering();
    }
    
}
