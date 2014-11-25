package com.jouwee.proto;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * View for images
 * 
 * @author Jouwee
 */
public class ImageView extends View {

    /** Image */
    private JLabel image;
    
    /**
     * Creates a new Image View
     * 
     * @param model
     */
    public ImageView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupImageLabel();
        setupViewAndPlaceComponents();
    }
    
    /**
     * Sets up the image label
     */
    private void setupImageLabel() {
        
        BufferedImage outputImage = (BufferedImage) getModel().getState().get("outputImage");
        
        image = new JLabel(new ImageIcon(outputImage.getSubimage(0, 0, outputImage.getWidth(), outputImage.getHeight())));
    }
    
    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(image);
    }
    
}
