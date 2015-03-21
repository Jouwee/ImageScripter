/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.Image;
import java.awt.BorderLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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
public class ImageViewer extends JComponent implements PropertyChangeListener,
        MouseWheelListener {
    
    /** Property name - Image */
    public static final String PROP_IMAGE = "PROP_IMAGE";
    /** Scroll pane */
    private JScrollPane scroll;
    /** Image rendering */
    private JLabel imageRendering;
    /** Image currently being shown */
    private Image image;
    /** Zoom factor */
    private float zoomFactor;
    /** Property Change Support */
    private final transient PropertyChangeSupport propertyChangeSupport;
    
    /**
     * New image viewer
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ImageViewer() {
        super();
        propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(this);
        // Default zoom
        zoomFactor = 1.0f;
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
        imageRendering.setHorizontalAlignment(JLabel.CENTER);
        // Zoom handler
        imageRendering.addMouseWheelListener(this);
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
        BufferedImage bi = getZoomedImage(image);
        imageRendering.setIcon(new ImageIcon(bi));
    }
    
    /**
     * Retorna a imagem com zoom aplicado
     * 
     * @param image
     * @return BufferedImage
     */
    private BufferedImage getZoomedImage(Image image) {
        BufferedImage bi = image.getBufferedImage();
        // New image size
        int width = (int) (bi.getWidth() * zoomFactor);
        int height = (int) (bi.getHeight() * zoomFactor);
        // Transforms
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(zoomFactor, zoomFactor);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        // Resize the image
        return bilinearScaleOp.filter(bi, new BufferedImage(width, height, bi.getType()));
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            zoomFactor -= e.getUnitsToScroll() * 0.005;
            // Lower limit
            if (zoomFactor < 0.05) {
                zoomFactor = 0.05f;
            }
            updateImageRendering();
        }
    }
    
}
